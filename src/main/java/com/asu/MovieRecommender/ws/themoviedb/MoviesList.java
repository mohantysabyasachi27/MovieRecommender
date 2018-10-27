package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Maps the movie list to the JSON object
 * 
 * @author leharbhatt
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesList implements Serializable {
	private static final long serialVersionUID = -7788619177798333712L;
	
	private List<Movie> results;
	private String statusCode ;
	private boolean success;
	private String errorReason;

	public MoviesList() {
	}

	public MoviesList(String statusCode, boolean success, String errorReason) {
		super();
		this.statusCode = statusCode;
		this.success = success;
		this.errorReason = errorReason;
	}
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean getSuccess() {
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

	public List<Movie> getResults() {
		return results;
	}

	public void setResults(List<Movie> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "MoviesList [results=" + results + ", statusCode=" + statusCode + ", success=" + success + ", errorReason="
				+ errorReason + "]";
	}
}
