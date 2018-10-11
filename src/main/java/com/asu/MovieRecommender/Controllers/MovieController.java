package com.asu.MovieRecommender.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.config.BasicConfiguration;
import com.asu.MovieRecommender.ws.themoviedb.MoviesList;
import com.asu.MovieRecommender.ws.themoviedb.TheMovieDBService;

/**
 * 
 * @author leharbhatt
 *
 */

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class MovieController {
	
	@Value("${movie.api.key}")
	private String val;
	
	private BasicConfiguration config;
	
	@Autowired
    public void setApp(BasicConfiguration config) {
        this.config = config;
    }

	@Autowired
	private TheMovieDBService theMovieDBService;
	
	/**
	 * This api return now playing movies to the User in the nearby areas
	 * @return JSONObject
	 */
	@RequestMapping(value="/api/getMovies",method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<MoviesList> getListOfMovies() {
		ResponseEntity<MoviesList> listOfMovies = null;
		try {
			listOfMovies = theMovieDBService.getNowPlayingMovies();
		} catch (MovieDetailsException exception) {
			return new ResponseEntity<MoviesList>(new MoviesList(HttpStatus.FORBIDDEN.toString(), false, exception.getMessage()), HttpStatus.OK);
		}
		return listOfMovies;
	}
	
	/* 
	 * Get properties from application.properties
	 * */
	
	@RequestMapping("/test")
    public void welcome(Map<String, Object> model) {
        String appProperties = config.toString();
        System.out.println("config val = " + config.getKey());
        System.out.println(appProperties);
    }
	
	
}
