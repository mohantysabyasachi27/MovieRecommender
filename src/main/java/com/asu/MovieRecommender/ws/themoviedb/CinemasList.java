package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

public class CinemasList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -898623802841147377L;

	private List<Cinema> cinemas;
	private String statusCode;
	private boolean success;
	private String errorMessage;

	public CinemasList(String statusCode, boolean success, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.success = success;
		this.errorMessage = errorMessage;
	}

	public CinemasList() {
		super();
	}

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "CinemasList [cinemas=" + cinemas + ", statusCode=" + statusCode + ", success=" + success
				+ ", errorMessage=" + errorMessage + "]";
	}
	
	

}
