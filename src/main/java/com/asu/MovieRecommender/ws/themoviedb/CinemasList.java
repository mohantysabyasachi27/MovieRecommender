package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

public class CinemasList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -898623802841147377L;
	
	private List<Cinema> cinemas;
	private String statusCode ;
	private boolean success;
	private String errorReason;

	public List<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
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
