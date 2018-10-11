package com.asu.MovieRecommender.ws.themoviedb;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
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

	private final RestTemplate restTemplate;

	public TheMovieDBServiceBean(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();

	}

	/**
	 * This method invokes the movieDb api to get now playing movies and returns
	 * the response to the user.
	 * 
	 * @return JSONObject
	 * @throws MovieDetailsException
	 */
	@Override
	public ResponseEntity<MoviesList> getNowPlayingMovies() throws MovieDetailsException {

		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.MOVIES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_CINEMA_ID, Constants.PARAM_CINEMA_ID_VALUE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.API_KEY_STRING, Constants.API_KEY_STRING_VALUE);

		MoviesList response = null;

		try {
			response = restTemplate.getForObject(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), MoviesList.class);
			response.setStatusCode(Constants.STATUS_OK);
			response.setSuccess(true);
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return new ResponseEntity<MoviesList>(response, HttpStatus.OK);
	}
}
