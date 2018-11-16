package com.asu.MovieRecommender.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.MovieRecommenderApplication;
import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.UserService.UserLoginService;
import com.asu.MovieRecommender.ws.themoviedb.CinemasList;
import com.asu.MovieRecommender.ws.themoviedb.MoviesList;
import com.asu.MovieRecommender.ws.themoviedb.TheMovieDBService;

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

	@Autowired
	private UserLoginService userLoginService;
	
	
	/**
	 * This api return now playing movies to the User in the nearby areas
	 * 
	 * @return JSONObject
	 */
	@PostMapping
	@RequestMapping(value = "/api/getMovies", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<MoviesList> getListOfMovies(@RequestParam("type") String type) {
		ResponseEntity<MoviesList> listOfMovies = null;
		try {
			/*if(!userLoginService.isLoggedIn()) {
				return new ResponseEntity<MoviesList>(
						new MoviesList(HttpStatus.FORBIDDEN.toString(), false, String.valueOf("User is not Logged in!!")), HttpStatus.OK);
			}*/
			listOfMovies = theMovieDBService.getNowPlayingMoviesTheMovieDB(type);
		} catch (MovieDetailsException exception) {
			logger.error(exception.getErrorMessage(), exception);
			return new ResponseEntity<MoviesList>(
					new MoviesList(HttpStatus.FORBIDDEN.toString(), false, exception.getErrorMessage()), HttpStatus.OK);
		}
		return listOfMovies;
	}
	
	
	@PostMapping
	@RequestMapping(value = "/api/getShowtimes", produces = "application/json")
	public ResponseEntity<CinemasList> getMovieShowtime(@RequestParam("movieName") String movieName, @RequestParam("movieId") String movieId) {
		ResponseEntity<CinemasList> listOfShowtimes = null;
		try {
			/*if(!userLoginService.isLoggedIn()) {
				return new ResponseEntity<CinemasList>(
						new CinemasList(HttpStatus.FORBIDDEN.toString(), false, String.valueOf("User is not Logged in!!")), HttpStatus.OK);
			}*/
			
			listOfShowtimes = theMovieDBService.getCinemas(movieName, movieId);
		} catch (MovieDetailsException exception) {
			logger.error(exception.getErrorMessage(), exception);
			return new ResponseEntity<CinemasList>(
					new CinemasList(HttpStatus.FORBIDDEN.toString(), false, exception.getErrorMessage()), HttpStatus.OK);
		}
		return listOfShowtimes;
	}
}
