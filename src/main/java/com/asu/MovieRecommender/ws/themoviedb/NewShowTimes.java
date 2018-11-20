package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewShowTimes implements Serializable {

	private static final long serialVersionUID = -7226730519472623144L;
	
	private String cinema_id;
	private String name;
	List<BookingDetails> bookingDetails = new ArrayList<>();

	public String getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(String cinema_id) {
		this.cinema_id = cinema_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		return "NewShowTimes [cinema_id=" + cinema_id + ", name=" + name + ", bookingDetails=" + bookingDetails + "]";
	}

}
