package com.asu.MovieRecommender.ws.themoviedb;

import org.json.simple.JSONObject;

/**
 * 
 * @author leharbhatt
 *
 */

public interface TheMovieDBService {
	public JSONObject getNowPlayingMovies();
}
