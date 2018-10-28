package com.asu.MovieRecommender.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.MovieRecommenderApplication;
import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.ws.themoviedb.CinemasList;
import com.asu.MovieRecommender.ws.themoviedb.MoviesList;
import com.asu.MovieRecommender.ws.themoviedb.ShowtimesList;
import com.asu.MovieRecommender.ws.themoviedb.TheMovieDBService;
import com.asu.MovieRecommender.ws.themoviedb.TrailersJSON;

/**
 * 
 * @author leharbhatt
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

	public static Logger logger = LogManager.getLogger(MovieRecommenderApplication.class);

	@Autowired
	private TheMovieDBService theMovieDBService;

	/**
	 * This api return now playing movies to the User in the nearby areas
	 * 
	 * @return JSONObject
	 */
	@RequestMapping(value = "/api/getMovies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MoviesList> getListOfMovies() {
		ResponseEntity<MoviesList> listOfMovies = null;
		try {
			listOfMovies = theMovieDBService.getNowPlayingMoviesTheMovieDB();
		} catch (MovieDetailsException exception) {
			logger.error(exception.getErrorMessage(), exception);
			return new ResponseEntity<MoviesList>(
					new MoviesList(HttpStatus.FORBIDDEN.toString(), false, exception.getErrorMessage()), HttpStatus.OK);
		}
		return listOfMovies;
	}
	
	@PostMapping
	@RequestMapping(value = "/api/getShowtimes", produces = "application/json")
	public ResponseEntity<CinemasList> getMovieShowtime(@RequestParam("movieName") String movieName) {
		ResponseEntity<CinemasList> listOfShowtimes = null;
		try {
			listOfShowtimes = theMovieDBService.getCinemas(movieName);
		} catch (MovieDetailsException exception) {
			logger.error(exception.getErrorMessage(), exception);
			/*return new ResponseEntity<CinemasList>(
					new CinemasList(HttpStatus.FORBIDDEN.toString(), false, exception.getErrorMessage()), HttpStatus.OK);
		*/}
		return listOfShowtimes;
	}
	
	@RequestMapping(value = "/api/getTrailers", produces = "application/json")
	public ResponseEntity<TrailersJSON> getMovieTrailers() {
		ResponseEntity<TrailersJSON> listOfTrailers = null;
		try {
			listOfTrailers = theMovieDBService.getNowPlayingMoviesTrailers();
		} catch (MovieDetailsException exception) {
			logger.error(exception.getErrorMessage(), exception);
			
		}
		return listOfTrailers;
	}
}
