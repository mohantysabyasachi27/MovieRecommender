package com.asu.MovieRecommender.ws.themoviedb;

import java.util.List;

public class TrailersJSON {
	
	private String statusCode ;
	private boolean success;
	private String errorReason;
	private List<TrailersList> list;
	
	public TrailersJSON() {}
	
	public TrailersJSON(String statusCode, boolean success, String errorReason) {
		super();
		this.statusCode = statusCode;
		this.success = success;
		this.errorReason = errorReason;
	}
	
	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public List<TrailersList> getList() {
		return list;
	}

	public void setList(List<TrailersList> list) {
		this.list = list;
	}
}
