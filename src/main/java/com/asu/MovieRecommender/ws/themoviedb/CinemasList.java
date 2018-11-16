package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemasList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -898623802841147377L;
	private String statusCode;
	private boolean success;
	private String errorMessage;
	private String site;
	private List<Cinema> cinemas;
	private List<DateList> dateList;

	public CinemasList(String statusCode, boolean success, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.success = success;
		this.errorMessage = errorMessage;
	}

	public CinemasList() {
		super();
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "CinemasList [statusCode=" + statusCode + ", success=" + success
				+ ", errorMessage=" + errorMessage + "]";
	}
	
	@JsonIgnore
	public List<Cinema> getCinemas() {
		return cinemas;
	}
	@JsonProperty
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public List<DateList> getDateList() {
		return dateList;
	}
	public void setDateList(List<DateList> dateList) {
		this.dateList = dateList;
	}

}
