package com.asu.MovieRecommender.ws.themoviedb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.utility.ApiUrl;
import com.asu.MovieRecommender.utility.Constants;

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

	/**
	 * This method invokes the movieDb api to get now playing movies and returns the
	 * response to the user.
	 * 
	 * @return JSONObject
	 * @throws MovieDetailsException
	 */
//	@Override
//	public ResponseEntity<MoviesList> getNowPlayingMovies() throws MovieDetailsException {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(Constants.API_KEY_STRING, apiKeyValue);
//		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.MOVIES);
//		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_CINEMA_ID, Constants.PARAM_CINEMA_ID_VALUE);
//
//		ResponseEntity<MoviesList> response = null;
//		MoviesList listOfMovies = null;
//
//		try {
//			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity,
//					MoviesList.class);
//			listOfMovies = response.getBody();
//			listOfMovies.setStatusCode(Constants.STATUS_OK);
//			listOfMovies.setSuccess(true);
//			List<Movie> movieList = listOfMovies.getResults();
//			for (int i = 0; i < movieList.size(); i++) {
//				if (movieList.get(i).getPoster_image_thumbnail() == null) {
//					listOfMovies.getResults().remove(i);
//				}
//
//			}
//		} catch (Exception exception) {
//			throw new MovieDetailsException(exception.getMessage());
//		}
//		return new ResponseEntity<MoviesList>(listOfMovies, HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<MoviesList> getNowPlayingMoviesTheMovieDB() throws MovieDetailsException {
		ResponseEntity<MoviesList> response = null;
		MoviesList listOfMovies = null;
		try {
            ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, Constants.NOWPLAYING);
    		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);
    		HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET,entity,MoviesList.class);
            listOfMovies = response.getBody();
			listOfMovies.setStatusCode(Constants.STATUS_OK);
			listOfMovies.setSuccess(true);
			List<Movie> movieList = listOfMovies.getResults();
			for (int i = 0; i < movieList.size(); i++) {
				if (movieList.get(i).getPoster_image_thumbnail() == null) {
					listOfMovies.getResults().remove(i);
				}
			}
            logger.info("Got the list of ",response.getBody().getResults().toString());
        } catch (Exception exception) {
        	throw new MovieDetailsException(exception.getMessage());
        }
		return new ResponseEntity<MoviesList>(listOfMovies, HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<ShowtimesList> getMovieShowtimes(String movieName) throws MovieDetailsException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL, Constants.SHOWTIMES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_QUERY, movieName);
		apiUrlToGetNowPlayingMovies.addParam(Constants.SEARCH_FIELD, Constants.CINEMA_MOVIE_TITLE);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		ResponseEntity<ShowtimesList> response = null;
		ShowtimesList listOfShowtimes = null;

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity,
					ShowtimesList.class);
			listOfShowtimes = response.getBody();
			listOfShowtimes.setStatusCode(Constants.STATUS_OK);
			listOfShowtimes.setSuccess(true);
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return new ResponseEntity<ShowtimesList>(listOfShowtimes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TrailersJSON> getNowPlayingMoviesTrailers() throws MovieDetailsException {
		List<TrailersList> list = new ArrayList<TrailersList>();
		ResponseEntity<TrailersList> response = null;
		
		MoviesList nowPlayingMovies = getNowPlayingMoviesTheMovieDB().getBody();
		TrailersJSON trailersJSON = new TrailersJSON();

		for (Movie movie : nowPlayingMovies.getResults()) {
			int id = movie.getId();
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.URL_TMDB, Constants.MOVIE, id, Constants.VIDEOS);
			apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_API_KEY, apiKeyValueTheMovieDB);

			try {
				response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity,
						TrailersList.class);
				TrailersList listOfTrailers = null;
				listOfTrailers = response.getBody();
				int size = listOfTrailers.getResults().size();
				if (size >= 1)
					listOfTrailers.getResults().subList(1, size).clear();
				if (listOfTrailers.getResults() != null) {
					list.add(listOfTrailers);
				}

			} catch (Exception exception) {
				throw new MovieDetailsException(exception.getMessage());
			}

		}
		trailersJSON.setList(list);
		return new ResponseEntity<TrailersJSON>(trailersJSON, HttpStatus.OK);
	}
}
