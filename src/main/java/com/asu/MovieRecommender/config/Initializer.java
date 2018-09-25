package com.asu.MovieRecommender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer { 

	@Autowired
	private AuthenticationManager authenticationManager;	
	
	public Initializer() {
		super(MovieRecommenderConfig.class); 
	}

}

