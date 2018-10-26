package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrailersList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -621310587533994986L;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	private List<Trailer> results;
	private String name;
	private String site;
	
	
	public TrailersList(){
		
	}

	public List<Trailer> getResults() {
		return results;
	}

	public void setResults(List<Trailer> results) {
		this.results = results;
	}

}
