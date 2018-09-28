package com.asu.MovieRecommender.Exceptions;

public class UserNotFoundException extends Exception {
	/**
	 * @author Sabyasachi
	 * @since 25th Sept
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public UserNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

