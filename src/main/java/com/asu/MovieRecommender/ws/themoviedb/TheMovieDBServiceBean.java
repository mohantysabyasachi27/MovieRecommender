package com.asu.MovieRecommender.ws.themoviedb;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asu.MovieRecommender.MovieRecommenderApplication;
import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.utility.ApiUrl;
import com.asu.MovieRecommender.utility.Constants;

/**
 * @author leharbhatt
 *
 */
@Service
public class TheMovieDBServiceBean implements TheMovieDBService {

	public static Logger logger = LogManager.getLogger(TheMovieDBServiceBean.class);
	
	@Autowired
	private RestTemplate restTemplate;

	/*
	 * public TheMovieDBServiceBean(RestTemplateBuilder restTemplateBuilder) {
	 * restTemplate = restTemplateBuilder.build();
	 * 
	 * }
	 */

	/**
	 * This method invokes the movieDb api to get now playing movies and returns the
	 * response to the user.
	 * 
	 * @return JSONObject
	 * @throws MovieDetailsException
	 */
	@Override
	public ResponseEntity<MoviesList> getNowPlayingMovies() throws MovieDetailsException {

		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.MOVIES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_CINEMA_ID, Constants.PARAM_CINEMA_ID_VALUE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.API_KEY_STRING, Constants.API_KEY_STRING_VALUE);
		ResponseEntity<MoviesList> response = null;

		
		try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrlString(), HttpMethod.GET,entity,MoviesList.class);
            logger.info("Got the list of ",response.getBody());
        } catch (Exception ex) {
        	throw new MovieDetailsException(ex.getMessage());
        }
		return response;
	}

	
	@Override
	public ResponseEntity<ShowtimesList> getMovieShowtimes(String movieId) throws MovieDetailsException {
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.SHOWTIMES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.API_KEY_STRING, Constants.API_KEY_STRING_VALUE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.MOVIE_ID, movieId);

		ShowtimesList response = null;
		try {
			response = restTemplate.getForObject(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), ShowtimesList.class);
			response.setStatusCode(Constants.STATUS_OK);
			response.setSuccess(true);
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return new ResponseEntity<ShowtimesList>(response, HttpStatus.OK);
	}
}
