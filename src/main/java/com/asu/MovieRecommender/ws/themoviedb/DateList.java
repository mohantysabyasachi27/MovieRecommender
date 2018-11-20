package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

public class DateList /*implements Serializable*/ {
	/**
	 * 
	 */
	//private static final long serialVersionUID = -1726631194599788775L;
	public DateList() {
		super();
	}
	private String date;
	private List<Showtimes> shows;
	public List<Showtimes> getShows() {
		return shows;
	}
	public void setShows(List<Showtimes> shows) {
		this.shows = shows;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
