package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesList implements Serializable {
	private static final long serialVersionUID = -7788619177798333712L;
	
	private List<Movie> movies;
	//private static HashMap

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
}
