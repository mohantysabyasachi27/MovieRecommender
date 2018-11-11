package com.asu.MovieRecommender.ws.themoviedb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.Services.CacheService;
import com.asu.MovieRecommender.UserService.UserLoginService;
import com.asu.MovieRecommender.utility.ApiUrl;
import com.asu.MovieRecommender.utility.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;

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
			if (userLoginService.getLoggedUserDetails() != null) {
				String key = userLoginService.getLoggedUserDetails().getUserName();
				String col = "now_playing";
				String cacheMovieList = cacheService.get(key, col) == null ? ""
						: String.valueOf(cacheService.get(key, col));
				if (StringUtils.isNotBlank(cacheMovieList) && StringUtils.isNotBlank(key)) {
					movieList = map.readValue(cacheMovieList.getBytes(), new TypeReference<List<Movie>>() {
					});
					listOfMovies.setStatusCode(Constants.STATUS_OK);
					listOfMovies.setSuccess(true);
					listOfMovies.setResults(movieList);
				}
			}
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

					//cacheService.put(key, col, map.writeValueAsString(movieList));
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

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					ShowtimesList.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				listOfShowtimes = response.getBody();
				listOfShowtimes.setStatusCode(Constants.STATUS_OK);
				listOfShowtimes.setSuccess(true);
				logger.info("Got the list of {}", listOfShowtimes);
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return listOfShowtimes.getShowtimes();
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
		CinemasList cinemasList = null;
		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET, entity,
					CinemasList.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				cinemasList = response.getBody();
				cinemasList.setSite(getNowPlayingMoviesTrailers(movieId));
				cinemasList.setStatusCode(Constants.STATUS_OK);
				cinemasList.setSuccess(true);
				logger.info("Got the list of {}", cinemasList.getCinemas());
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
					List<Showtimes> m = cinema.getMovieList();
					if (m != null)
						m.add(showtimes.get(i));
					else {
						m = new ArrayList<Showtimes>();
						m.add(showtimes.get(i));
					}
					cinema.setMovieList(m);
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
}
