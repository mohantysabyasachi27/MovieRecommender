package com.asu.MovieRecommender.User;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserRatings")
public class UserRatings implements Serializable {

	
	private String userName;
	private Double rating;
	private Date dateAdded;
	private String movieId;
	private String movieName;
	
	
	public UserRatings()
	{
		
	}


	public UserRatings(String userName, Double rating, Date dateAdded, String movieId, String movieName) {
		super();
		this.userName = userName;
		this.rating = rating;
		this.dateAdded = dateAdded;
		this.movieId = movieId;
		this.movieName = movieName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}


	public Date getDateAdded() {
		return dateAdded;
	}


	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}


	public String getMovieId() {
		return movieId;
	}


	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
}
