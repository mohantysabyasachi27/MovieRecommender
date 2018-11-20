package com.asu.MovieRecommender.Services;

import java.util.Map;

public interface CacheService {
	
	
	public final static String THEATRE_KEY = "theatre_";
	public final static String TRAILER_KEY = "trailer";

	void put(String key, String col, String value);

	String get(String key, String col);
	
	Map get(String key);

//	void evict(String key, List<String> col);

}
