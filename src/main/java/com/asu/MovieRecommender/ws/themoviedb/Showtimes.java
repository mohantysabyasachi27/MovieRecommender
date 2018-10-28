package com.asu.MovieRecommender.ws.themoviedb;

/**
 * Maps the showtimes to JSON object.
 * @author leharbhatt
 *
 */
public class Showtimes {

	private String cinema_id;
	private String start_at;
	private String booking_link;

	public String getCinema_id() {
		return cinema_id;
	}

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
	
}
