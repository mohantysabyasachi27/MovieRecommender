package com.asu.MovieRecommender.ws.themoviedb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author leharbhatt JSONObject mapper to map internationMovieAPI results to
 *         Java Object
 */

@JsonIgnoreProperties(ignoreUnknown = true, value = { "movieMap", "nowPlayingMoviesId" })
public class Movie {
	private int id;
	private String slug;
	private String title;
	private String poster_image_thumbnail;

	public Movie() {
	}

	public Movie(int id, String slug, String title, String poster_image_thumbnail) {
		super();
		this.id = id;
		this.slug = slug;
		this.title = title;
		this.poster_image_thumbnail = poster_image_thumbnail;
	}

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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", slug=" + slug + ", title=" + title + ", poster_image_thumbnail="
				+ poster_image_thumbnail + "]";
	}

}
