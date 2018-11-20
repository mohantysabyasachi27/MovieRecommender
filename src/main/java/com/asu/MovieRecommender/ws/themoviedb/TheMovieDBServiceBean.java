package com.asu.MovieRecommender.ws.themoviedb;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.Services.CacheService;
import com.asu.MovieRecommender.UserService.UserLoginService;
import com.asu.MovieRecommender.utility.ApiUrl;
import com.asu.MovieRecommender.utility.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author leharbhatt
 *
 */
@Service
public class TheMovieDBServiceBean implements TheMovieDBService {

	@Value("${movie.api.key}")
	private String apiKeyValue;

	@Value("${moviedb.api.key}")
	private String apiKeyValueTheMovieDB;
	public static Logger logger = LogManager.getLogger(TheMovieDBServiceBean.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private UserLoginService userLoginService;

	private static CinemasList cinemasList = null;

	/**
	 * This method invokes the movieDb api to get now playing movies and returns the
	 * response to the user.
	 * 
	 * @return JSONObject
	 * @throws MovieDetailsException
	 */

	@Override
	public ResponseEntity<MoviesList> getNowPlayingMoviesTheMovieDB(String type) throws MovieDetailsException {
		ResponseEntity<MoviesList> response = null;
		MoviesList listOfMovies = new MoviesList();
		List<Movie> movieList = null;
		ObjectMapper map = new ObjectMapper();

		try {
			/*
			 * String key = userLoginService.getLoggedUserDetails().getUserName(); String
			 * col = "now_playing"; String cacheMovieList = cacheService.get(key, col) ==
			 * null ? "" : String.valueOf(cacheService.get(key, col)); if
			 * (StringUtils.isNotBlank(cacheMovieList) && StringUtils.isNotBlank(key)) {
			 * movieList = map.readValue(cacheMovieList.getBytes(), new
			 * TypeReference<List<Movie>>() { });
			 * listOfMovies.setStatusCode(Constants.STATUS_OK);
			 * listOfMovies.setSuccess(true); listOfMovies.setResults(movieList); }
			 */

			if (CollectionUtils.isEmpty(movieList)) {
				ApiUrl apiUrlToGetNowPlayingMovies = null;
				if (type.equalsIgnoreCase("nowplaying"))
					apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, Constants.NOWPLAYING);
				else if (type.equalsIgnoreCase("popular"))
					apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, Constants.POPULAR);
				else if (type.equalsIgnoreCase("toprated"))
					apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, Constants.TOPRATED);
				apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.add("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
				response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
						MoviesList.class);
				if (response != null && response.getStatusCode() == HttpStatus.OK) {
					listOfMovies = response.getBody();
					listOfMovies.setStatusCode(Constants.STATUS_OK);
					listOfMovies.setSuccess(true);
					movieList = listOfMovies.getResults();
					for (int i = 0; i < movieList.size() && !CollectionUtils.isEmpty(movieList); i++) {
						if (movieList.get(i).getPoster_image_thumbnail() == null) {
							listOfMovies.getResults().remove(i);
						}
					}

					// cacheService.put(key, col, map.writeValueAsString(movieList));
					logger.debug("Got the list of {}", movieList);
				}
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());
		}
		return new ResponseEntity<MoviesList>(listOfMovies, HttpStatus.OK);
	}

	/**
	 * A method which returns a list of showtimes for a specific movie
	 */
	@Override
	public List<Showtimes> getMovieShowtimes(String movieName, String movieId) throws MovieDetailsException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.SHOWTIMES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_QUERY, movieName);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_FIELD, Constants.CINEMA_MOVIE_TITLE);

		ResponseEntity<ShowtimesList> response = null;
		ShowtimesList listOfShowtimes = null;
		Map<String, List<Showtimes>> map = null;

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					ShowtimesList.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				listOfShowtimes = response.getBody();
				listOfShowtimes.setStatusCode(Constants.STATUS_OK);
				listOfShowtimes.setSuccess(true);
				List<Showtimes> shows = listOfShowtimes.getShowtimes();

				map = Cinema.getDateShowtime();
				for (int i = 0; i < shows.size(); i++) {
					if (null != shows.get(i)) {
						String date = shows.get(i).getStart_at();
						Calendar cal = javax.xml.bind.DatatypeConverter.parseDateTime(date);
						String num = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
						if (map.containsKey(num)) {
							List<Showtimes> list = map.get(num);
							list.add(shows.get(i));
							map.put(num, list);
						} else {
							List<Showtimes> list = new ArrayList<Showtimes>();
							list.add(shows.get(i));
							map.put(num, list);
						}
					}
				}
				List<DateList> datelist = new ArrayList<DateList>();
				for (String str : map.keySet()) {
					DateList dl = new DateList();
					dl.setDate(str);
					dl.setShows(map.get(str));
					datelist.add(dl);
				}

				cinemasList.setDateList(datelist);
				logger.info("Got the list of {}", listOfShowtimes);
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return listOfShowtimes.getShowtimes();
	}

	public Map<String, List<ShowDetails>> fetchMovieShowtimes(String movieName, String movieId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.SHOWTIMES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_QUERY, movieName);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_FIELD, Constants.CINEMA_MOVIE_TITLE);

		ResponseEntity<ShowtimesList> response = null;
		ShowtimesList showList = null;
		List<Showtimes> listShowtimes = null;

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					ShowtimesList.class);
			logger.debug(response.toString());
			if (null != response && response.getStatusCode() == HttpStatus.OK && null != response.getBody()) {

				showList = response.getBody();
				listShowtimes = showList.getShowtimes();
				int iLength = listShowtimes.size();

				if (iLength > 0) {
					int i = 0;
					String startTime = null;
					Showtimes showTime = null;
					Map<String, List<ShowDetails>> showsMapByDate = new HashMap<>();
					String date = null;
					String time = null;
					String cinemaId = null;
					String bookingLink = null;
					List<ShowDetails> showDetails = null;
					for (i = 0; i < iLength; i++) {
						showTime = null;
						startTime = null;
						cinemaId = null;
						bookingLink = null;
						showDetails = null;
						showTime = listShowtimes.get(i);

						startTime = showTime.getStart_at();
						cinemaId = showTime.getCinema_id();
						bookingLink = showTime.getBooking_link();
						if (StringUtils.isNotBlank(startTime)) {
							date = startTime.substring(0, 10);
							time = startTime.substring(11, 19);
						}
						if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(time)) {

							if (showsMapByDate.containsKey(date)) {
								showDetails = showsMapByDate.get(date);
								showDetails.add(new ShowDetails(cinemaId, bookingLink, time));
								showsMapByDate.put(date, showDetails);
							} else {
								showDetails = new ArrayList<>();
								showDetails.add(new ShowDetails(cinemaId, bookingLink, time));
								showsMapByDate.put(date, showDetails);
							}
						}

					}

					return showsMapByDate;

				} else {
					logger.debug("Check the response body");
					throw new MovieDetailsException("The response body has no content or is null");
				}

			} else {
				logger.debug("The error in API call or response is null");
				throw new MovieDetailsException("Error in API call");

			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * A method that returns a trailer link for a specific movie
	 */
	@Override
	public String getNowPlayingMoviesTrailers(String movieId) throws MovieDetailsException {

		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, movieId, Constants.VIDEOS);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);

		try {
			ResponseEntity<TrailersList> response1 = restTemplate.exchange(
					apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity, TrailersList.class);
			if (response1 != null && response1.getStatusCode() == HttpStatus.OK) {
				TrailersList listOfTrailers = null;
				listOfTrailers = response1.getBody();
				int size = listOfTrailers.getResults().size();
				if (size >= 1)
					listOfTrailers.getResults().subList(1, size).clear();
				if (listOfTrailers.getResults() != null && size >= 1) {
					return listOfTrailers.getResults().get(0).getSite();
				}
				logger.info("Got the list of {}", listOfTrailers.getResults());
			}

		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());
		}
		return null;

	}

	/**
	 * A method that maps the cinema to the showtimes returned by the method
	 * getMovieShowtimes()
	 */
	@Override
	public ResponseEntity<CinemasList> getCinemas(String movieName, String movieId) throws MovieDetailsException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.CINEMAS);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		HashMap<String, Cinema> cinemaMap = new HashMap<String, Cinema>();
		ResponseEntity<CinemasList> response = null;
		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					CinemasList.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				cinemasList = response.getBody();
				cinemasList.setSite(getNowPlayingMoviesTrailers(movieId));
				cinemasList.setStatusCode(Constants.STATUS_OK);
				cinemasList.setSuccess(true);
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());
		}

		List<Cinema> cinemasResponse = cinemasList.getCinemas();
		for (int i = 0; i < cinemasResponse.size(); i++) {
			cinemaMap.put(cinemasResponse.get(i).getId(), cinemasResponse.get(i));
		}
		List<Showtimes> showtimes = getMovieShowtimes(movieName, movieId);
		if (showtimes != null) {
			for (int i = 0; i < showtimes.size(); i++) {
				String id = showtimes.get(i).getCinema_id();
				Cinema cinema;
				if (id != null) {
					cinema = cinemaMap.get(id);
					showtimes.get(i).setName(cinema.getName());
				}
			}
		}
		for (int i = 0; i < cinemasList.getCinemas().size()
				&& !CollectionUtils.isEmpty(cinemasList.getCinemas()); i++) {
			if (cinemasList.getCinemas().get(i).getMovieList() == null)
				cinemasList.getCinemas().remove(i);
		}
		return new ResponseEntity<CinemasList>(cinemasList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MoviesList> getRecommendedMovies(String movieId) throws MovieDetailsException {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<MoviesList> response = null;
		MoviesList listOfMovies = new MoviesList();
		List<Movie> movieList = null;
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, movieId,
				Constants.RECOMMENDATIONS);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					MoviesList.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				listOfMovies = response.getBody();
				listOfMovies.setStatusCode(Constants.STATUS_OK);
				listOfMovies.setSuccess(true);
				movieList = listOfMovies.getResults();
				for (int i = 0; i < movieList.size() && !CollectionUtils.isEmpty(movieList); i++) {
					if (movieList.get(i).getPoster_image_thumbnail() == null) {
						listOfMovies.getResults().remove(i);
					}
				}

				// cacheService.put(key, col, map.writeValueAsString(movieList));
				logger.debug("Got the list of {}", movieList);
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());
		}
		return new ResponseEntity<MoviesList>(listOfMovies, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CinemasList> getCinemasNew(String movieName, String movieId) throws MovieDetailsException {
		String city = Constants.TEMPE;
		CinemasList cinemaList = new CinemasList();
		Map<String, Map<String, List<ShowDetails>>> showtimesByTheatreAndDate = new HashMap<>();
		try {
			putTheatreForCity(city);
			cinemaList.setSite(getCachedTrailerUrl(movieId));

			Map<String, List<ShowDetails>> map = fetchMovieShowtimes(movieName, movieId);

			for (Map.Entry<String, List<ShowDetails>> entry : map.entrySet()) {

				if (!showtimesByTheatreAndDate.containsKey(entry.getKey())) {
					Map<String, List<ShowDetails>> tempMap = new HashMap<>();

					for (ShowDetails show : entry.getValue()) {
						String theatreName = cacheService.get(CacheService.THEATRE_KEY + city, show.getCinemaId());
						if (tempMap.containsKey(theatreName)) {
							tempMap.get(theatreName).add(show);
						} else {
							List<ShowDetails> list = new ArrayList<>();
							list.add(show);
							tempMap.put(theatreName, list);
						}

					}

					showtimesByTheatreAndDate.put(entry.getKey(), tempMap);

				} else {
					Map<String, List<ShowDetails>> tempMap = showtimesByTheatreAndDate.get(entry.getKey());

					for (ShowDetails show : entry.getValue()) {
						String theatreName = cacheService.get(CacheService.THEATRE_KEY + city, show.getCinemaId());
						if (tempMap.containsKey(theatreName)) {
							tempMap.get(theatreName).add(show);
						} else {
							List<ShowDetails> list = new ArrayList<>();
							list.add(show);
							tempMap.put(theatreName, list);
						}

					}
				}
			}

		} catch (MovieDetailsException e) {
			logger.error("Error while putting theatre names to the cache for city: {}.", city, e);
			throw new MovieDetailsException("Error while putting theatre names to the cache for city:" + city);
		} catch (RestClientException e) {
			throw new MovieDetailsException("Error while putting theatre names to the cache for city:" + city);
		} catch (URISyntaxException e) {
			throw new MovieDetailsException("Error while putting theatre names to the cache for city:" + city);
		} catch(Exception e) {
			throw new MovieDetailsException("Error while putting theatre names to the cache for city:" + city);
		}
		
		cinemaList.setShowtimesByTheatreAndDate(showtimesByTheatreAndDate);

		return new ResponseEntity<CinemasList>(cinemaList, HttpStatus.OK);
	}

	public void putTheatreForCity(String city) throws MovieDetailsException {
		if (cacheService.get(CacheService.THEATRE_KEY + city).isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.set(Constants.API_KEY_STRING, apiKeyValue);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
			ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.CINEMAS);
			apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, city);
			ResponseEntity<CinemasList> response = null;
			try {
				response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
						CinemasList.class);
				if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null
						&& !CollectionUtils.isEmpty(response.getBody().getCinemas())) {
					for (Cinema c : response.getBody().getCinemas()) {
						cacheService.put(CacheService.THEATRE_KEY + city, c.getId(), c.getName());
						System.out.println(c.getId() + "::" + c.getName());
					}
				}
			} catch (Exception exception) {
				throw new MovieDetailsException(exception.getMessage());
			}
		}
	}
	
	
	private String getCachedTrailerUrl(String movieId) throws RestClientException, URISyntaxException {
		String trailerUrl = cacheService.get(CacheService.TRAILER_KEY, movieId);
		if (StringUtils.isBlank(trailerUrl)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, movieId,
					Constants.VIDEOS);
			apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);

			ResponseEntity<TrailersList> response1 = restTemplate.exchange(
					apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity, TrailersList.class);
			if (response1 != null && response1.getStatusCode() == HttpStatus.OK) {
				TrailersList listOfTrailers = response1.getBody();
				if (listOfTrailers != null && !CollectionUtils.isEmpty(listOfTrailers.getResults()))
					for (Trailer t : listOfTrailers.getResults()) {
						if (StringUtils.isNotBlank(t.getSite())) {
							cacheService.put(CacheService.TRAILER_KEY, movieId, t.getSite());
							return t.getSite();
						}
					}
			}
		}
		return trailerUrl;
	}
}
