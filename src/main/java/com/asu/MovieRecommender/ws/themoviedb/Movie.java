package com.asu.MovieRecommender.ws.themoviedb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author leharbhatt
 * JSONObject mapper to map themoviedb results to Java Object
 */


@JsonIgnoreProperties(ignoreUnknown = true, value = {"movieMap","nowPlayingMoviesId"})
public class Movie {
	private int id;
	private String slug;
	private String title;
	private String poster_image_thumbnail;
	private ShowtimesList showtimes = new ShowtimesList();
	
	private static List<Integer> nowPlayingMoviesId = new ArrayList<Integer>();
	private static Map<Integer,Movie> movieMap = new HashMap<Integer,Movie>();
	
	

	public ShowtimesList getAllTheatresShowtime() {
		return showtimes;
	}

	public void setAllTheatresShowtime(ShowtimesList showtimes) {
		this.showtimes = showtimes;
	}

	public static Map<Integer, Movie> getMovieMap() {
		return movieMap;
	}

	public static void setMovieMap(Map<Integer, Movie> movieMap) {
		Movie.movieMap = movieMap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		nowPlayingMoviesId.add(id);
		movieMap.put(id, this);
	}

	public String getPoster_image_thumbnail() {
		return poster_image_thumbnail;
	}

	public void setPoster_image_thumbnail(String poster_image_thumbnail) {
		this.poster_image_thumbnail = poster_image_thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public static List<Integer> getNowPlayingMovies() {
		return nowPlayingMoviesId;
	}
}
