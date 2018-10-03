package com.asu.MovieRecommender.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
	
	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Session Management. 
	 */
	
	
	public SessionConfig() {
		super(MovieRecommenderConfig.class);
	}
	
}



