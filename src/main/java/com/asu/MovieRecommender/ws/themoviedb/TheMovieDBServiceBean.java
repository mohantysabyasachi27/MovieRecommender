package com.asu.MovieRecommender.ws.themoviedb;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

	@Value("${movie.api.key}")
	private String apiKeyValue;

	private final RestTemplate restTemplate;

	public TheMovieDBServiceBean(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();

	}

	/**
	 * This method invokes the movieDb api to get now playing movies and returns the
	 * response to the user.
	 * 
	 * @return JSONObject
	 * @throws MovieDetailsException
	 */
	@Override
	public ResponseEntity<MoviesList> getNowPlayingMovies() throws MovieDetailsException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.MOVIES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.PARAM_CINEMA_ID, Constants.PARAM_CINEMA_ID_VALUE);

		ResponseEntity<MoviesList> response = null;
		MoviesList listOfMovies = null;

		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrl().toURI(), HttpMethod.GET, entity,
					MoviesList.class);
			//if(response != null || response.getBody())
			listOfMovies = response.getBody();
			listOfMovies.setStatusCode(Constants.STATUS_OK);
			listOfMovies.setSuccess(true);
			List<Movie> movieList = listOfMovies.getMovies();
			for(int i=0; i<movieList.size();i++) {
				if(movieList.get(i).getPoster_image_thumbnail() == null) {
					listOfMovies.getMovies().remove(i);
				}
					
			}
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());
		}
		return new ResponseEntity<MoviesList>(listOfMovies, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ShowtimesList> getMovieShowtimes(String movieId) throws MovieDetailsException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.API_KEY_STRING, apiKeyValue);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ApiUrl apiUrlToGetNowPlayingMovies = new ApiUrl(Constants.SHOWTIMES);
		apiUrlToGetNowPlayingMovies.addParam(Constants.CITY_ID, Constants.TEMPE);
		apiUrlToGetNowPlayingMovies.addParam(Constants.MOVIE_ID, movieId);

		ResponseEntity<ShowtimesList> response = null;
		ShowtimesList listOfShowtimes = null;
		
		try {
			response = restTemplate.exchange(apiUrlToGetNowPlayingMovies.buildUrl().toURI(),HttpMethod.GET, entity,
					ShowtimesList.class);
			listOfShowtimes = response.getBody();
			listOfShowtimes.setStatusCode(Constants.STATUS_OK);
			listOfShowtimes.setSuccess(true);
		} catch (Exception exception) {
			throw new MovieDetailsException(exception.getMessage());

		}
		return new ResponseEntity<ShowtimesList>(listOfShowtimes, HttpStatus.OK);
	}
}
