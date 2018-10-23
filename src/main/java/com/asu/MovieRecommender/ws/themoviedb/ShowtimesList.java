package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author leharbhatt
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowtimesList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6477884765293216591L;
	private List<Showtimes> showtimes;
	private String statusCode;
	private boolean success;
	private String errorReason;
	
	public ShowtimesList(){
		
	}
	
	public ShowtimesList(String statusCode, boolean success, String errorReason) {
		super();
		this.setStatusCode(statusCode);
		this.setSuccess(success);
		this.setErrorReason(errorReason);
	}

	public List<Showtimes> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(List<Showtimes> showtimes) {
		this.showtimes = showtimes;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
}
