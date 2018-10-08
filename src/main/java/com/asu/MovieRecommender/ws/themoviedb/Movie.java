package com.asu.MovieRecommender.ws.themoviedb;

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
	
}
