package com.asu.MovieRecommender.Exceptions;

/**
 * 
 * @author kumar
 * Custom exception for Registration APIs.
 */
public class RegisterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public RegisterException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
