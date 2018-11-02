package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;


public class TrailersList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -621310587533994986L;
	
	private List<Trailer> results;
	
	public TrailersList(){
		
	}

	public List<Trailer> getResults() {
		return results;
	}

	public void setResults(List<Trailer> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "TrailersList [results=" + results + "]";
	}

	
	
}
