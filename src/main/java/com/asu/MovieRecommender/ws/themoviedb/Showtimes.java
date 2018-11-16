package com.asu.MovieRecommender.ws.themoviedb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Maps the showtimes to JSON object.
 * 
 * @author leharbhatt
 *
 */
public class Showtimes {

	public Showtimes(String cinema_id, String start_at, String booking_link, String name) {
		super();
		this.cinema_id = cinema_id;
		this.start_at = start_at;
		this.booking_link = booking_link;
		this.name = name;
	}

	public Showtimes() {
		super();
	}

	private String cinema_id;
	private String name;
	private String start_at;
	private String booking_link;
	
	@JsonIgnore
	public String getCinema_id() {
		return cinema_id;
	}
	
	@JsonProperty
	public void setCinema_id(String cinema_id) {
		this.cinema_id = cinema_id;
	}

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public String getBooking_link() {
		return booking_link;
	}

	public void setBooking_link(String booking_link) {
		this.booking_link = booking_link;
	}

	@Override
	public String toString() {
		return "Showtimes [cinema_id=" + cinema_id + ", start_at=" + start_at + ", booking_link=" + booking_link + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
