package com.asu.MovieRecommender.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.config.BasicConfiguration;
import com.asu.MovieRecommender.ws.themoviedb.TheMovieDBService;

/**
 * 
 * @author leharbhatt
 *
 */

@RestController
public class MovieController {
	
	@Value("${movie.api.key}")
	private String val;
	
	private BasicConfiguration config;
	
	@Autowired
    public void setApp(BasicConfiguration config) {
        this.config = config;
    }

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private TheMovieDBService theMovieDBService;
	
	/**
	 * This api return a response for 20 now playing movies to the User
	 * @return JSONObject
	 */
	@RequestMapping(value="/api/getMovies",method = RequestMethod.GET, produces="application/json")
	public JSONObject getListOfMovies() {
		JSONObject listOfMovies = theMovieDBService.getNowPlayingMovies();
		return listOfMovies;
	}
	
	/* 
	 * Get properties from application.properties
	 * */
	
	@RequestMapping("/")
    public void welcome(Map<String, Object> model) {

        String appProperties = config.toString();
        System.out.println("config val = " + config.getKey());
     
        System.out.println(appProperties);
    }
	
	
}
