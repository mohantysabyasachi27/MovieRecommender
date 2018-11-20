package com.asu.MovieRecommender.ws.themoviedb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cinema implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3510186771989467423L;


	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", showtimes=" + showtimes + "]";
	}
	private String id;
	private String name;
	private List<Showtimes> showtimes;
	private static Map<String, List<Showtimes>> dateShowtime;
	
	
	public Cinema(String id, String name, List<Showtimes> showtimes, List<DateList> dateList) {
		super();
		this.id = id;
		this.name = name;
		this.showtimes = showtimes;
	}
	
	public Cinema() {
		super();
	}


	@JsonIgnore
	public String getId() {
		return id;
	}
	
	@JsonProperty
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Showtimes> getMovieList() {
		return showtimes;
	}
	public void setMovieList(List<Showtimes> showtimes) {
		this.showtimes = showtimes;
	}
	public static Map<String, List<Showtimes>> getDateShowtime() {
		if(dateShowtime == null)
			return new HashMap<String, List<Showtimes>>();
		else
			return dateShowtime;
	}
	public static void setDateShowtime(Map<String, List<Showtimes>> dateShowtime) {
		Cinema.dateShowtime = dateShowtime;
	}
	
}
