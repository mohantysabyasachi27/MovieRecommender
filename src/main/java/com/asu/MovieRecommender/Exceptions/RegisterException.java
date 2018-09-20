package com.asu.MovieRecommender.Exceptions;

public class RegisterException extends Exception {
	private String errorMessage;

	public RegisterException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
