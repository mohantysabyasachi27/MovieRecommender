package com.asu.MovieRecommender.ws.themoviedb;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author leharbhatt
 *
 */

public interface TheMovieDBService {
	public ResponseEntity<MoviesList> getNowPlayingMovies();
}
