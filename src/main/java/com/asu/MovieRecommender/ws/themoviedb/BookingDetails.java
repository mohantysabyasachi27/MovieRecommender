package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;

public class BookingDetails implements Serializable {

	private static final long serialVersionUID = 2091270600063494150L;
	private String start_at;
	private String booking_link;

	public BookingDetails(String start_at, String booking_link) {
		super();
		this.start_at = start_at;
		this.booking_link = booking_link;
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
		return "BookingDetails [start_at=" + start_at + ", booking_link=" + booking_link + "]";
	}

}
