package com.asu.MovieRecommender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("movie.api")
public class BasicConfiguration {
	private static String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		BasicConfiguration.key = key;
	}
}
