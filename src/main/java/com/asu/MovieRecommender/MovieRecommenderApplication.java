package com.asu.MovieRecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@EnableOAuth2Sso
@SpringBootApplication(scanBasePackages= {"com.asu.MovieRecommender"})
public class MovieRecommenderApplication {
	
	public static Logger logger = LogManager.getLogger(MovieRecommenderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommenderApplication.class, args);
		logger.info("Backend Service is up!!");
	}
}
