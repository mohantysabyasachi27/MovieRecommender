package com.asu.MovieRecommender.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
	
	public SessionConfig() {
		super(MovieRecommenderConfig.class);
	}
	
}

