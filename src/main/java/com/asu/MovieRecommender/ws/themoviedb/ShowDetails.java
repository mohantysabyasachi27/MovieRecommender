package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;

public class ShowDetails implements Serializable {

	private static final long serialVersionUID = -6437940778566869139L;
	private String cinemaId;
	public ShowDetails(String cinemaId, String bookingLink, String showTime) {
		super();
		this.cinemaId = cinemaId;
		this.bookingLink = bookingLink;
		this.showTime = showTime;
	}
	
	public ShowDetails() {
		
	}
	
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getBookingLink() {
		return bookingLink;
	}
	public void setBookingLink(String bookingLink) {
		this.bookingLink = bookingLink;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	private String bookingLink;
	private String showTime;
	
	@Override
	public String toString() {
		return "ShowDetails [cinemaId=" + cinemaId + ", bookingLink=" + bookingLink + ", showTime=" + showTime + "]";
	}
	

}
