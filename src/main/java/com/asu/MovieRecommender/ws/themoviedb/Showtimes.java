package com.asu.MovieRecommender.ws.themoviedb;

/**
 * Maps the showtimes to JSON object.
 * @author leharbhatt
 *
 */
public class Showtimes {

	private String cinema_id;
	private String cinema_name;
	private int movie_id;
	private String start_at;
	private boolean is_3d;
	private boolean is_imax;
	private String booking_link;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(String cinema_id) {
		this.cinema_id = cinema_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public boolean isIs_3d() {
		return is_3d;
	}

	public void setIs_3d(boolean is_3d) {
		this.is_3d = is_3d;
	}

	public boolean isIs_imax() {
		return is_imax;
	}

	public void setIs_imax(boolean is_imax) {
		this.is_imax = is_imax;
	}

	public String getBooking_link() {
		return booking_link;
	}

	public void setBooking_link(String booking_link) {
		this.booking_link = booking_link;
	}
}
