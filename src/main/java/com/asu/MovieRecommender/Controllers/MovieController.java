package com.asu.MovieRecommender.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

public class MovieController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//ToDo - Write an API to get the now playing movies
	
	
	
}
