/*
 *@author Kumar Prabhu Kalyan 
 */
package com.asu.MovieRecommender.User;

public class LogInReponse {

	private String statusCode ;
	private boolean success;
	private String errorReason;
	
	public LogInReponse(String statusCode, boolean success, String errorReason) {
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
	
}
