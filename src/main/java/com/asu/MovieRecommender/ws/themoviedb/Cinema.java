package com.asu.MovieRecommender.ws.themoviedb;

import java.util.List;

public class Cinema {
	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", website=" + website + ", telephone=" + telephone
				+ ", showtimes=" + showtimes + "]";
	}
	private String id;
	private String name;
	
	
	public Cinema(String id, String name, String website, String telephone, List<Showtimes> showtimes) {
		super();
		this.id = id;
		this.name = name;
		this.website = website;
		this.telephone = telephone;
		this.showtimes = showtimes;
	}
	
	
	
	public Cinema() {
		super();
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Showtimes> getMovieList() {
		return showtimes;
	}
	public void setMovieList(List<Showtimes> showtimes) {
		this.showtimes = showtimes;
	}
	private String website;
	private String telephone;
	private List<Showtimes> showtimes;
}
