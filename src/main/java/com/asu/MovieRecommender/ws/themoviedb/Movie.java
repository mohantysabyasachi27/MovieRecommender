package com.asu.MovieRecommender.ws.themoviedb;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author leharbhatt
 * JSONObject mapper to map internationMovieAPI results to Java Object
 */


public class Movie {
	private int id;
	private String title;
	@JsonProperty("poster_path")
	private String poster_image_thumbnail;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPoster_image_thumbnail() {
		return poster_image_thumbnail;
	}

	public void setPoster_image_thumbnail(String poster_image_thumbnail) {
		this.poster_image_thumbnail = "http://image.tmdb.org/t/p/w154"+poster_image_thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
