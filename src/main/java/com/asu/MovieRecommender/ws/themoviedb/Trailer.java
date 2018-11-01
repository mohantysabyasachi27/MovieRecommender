package com.asu.MovieRecommender.ws.themoviedb;

public class Trailer {
	private String key;
	private String name;
	private String site;
	
	public Trailer() {
		super();
	}
	public Trailer(String key, String name, String site) {
		super();
		this.key = key;
		this.name = name;
		this.site = site;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
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
		if("youtube".equalsIgnoreCase(this.site)) {
			this.site = "https://www.youtube.com/watch?v="+this.key;
		}
	}
	@Override
	public String toString() {
		return "Trailer [key=" + key + ", name=" + name + ", site=" + site + "]";
	}
	
	
}
