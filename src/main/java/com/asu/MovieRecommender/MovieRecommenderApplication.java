package com.asu.MovieRecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.asu.MovieRecommender"})
public class MovieRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommenderApplication.class, args);
	}
}
