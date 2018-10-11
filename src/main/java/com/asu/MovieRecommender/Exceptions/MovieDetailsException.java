package com.asu.MovieRecommender.Exceptions;

public class MovieDetailsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8033520485711888265L;

	private String errorMessage;

	public MovieDetailsException (String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
