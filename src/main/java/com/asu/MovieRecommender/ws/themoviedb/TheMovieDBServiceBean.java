package com.asu.MovieRecommender.ws.themoviedb;

import java.net.URISyntaxException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
	 * @return JSONObject This method invokes the movieDb api to get 20 now playing
	 *         movies and returns the response to the user.
	 */
	@Override
	public ResponseEntity<MoviesList> getNowPlayingMovies() {

		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.MOVIES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_CINEMA_ID, Constants.PARAM_CINEMA_ID_VALUE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.API_KEY_STRING, Constants.API_KEY_STRING_VALUE);
		
		MoviesList response = null;

		try {
			response = restTemplate.getForObject(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), MoviesList.class);

		} catch (RestClientException e) {

			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<MoviesList>(response, HttpStatus.OK);
	}
}
