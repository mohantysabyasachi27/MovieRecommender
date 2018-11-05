package com.asu.MovieRecommender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {

	public HttpSessionConfig() {
		super(SessionConfig.class);
	}

	public HttpSessionConfig(Class<?>... configurationClasses) {
		super(configurationClasses);
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("MYSESSIONID");
		serializer.setCookiePath("/");
		serializer.setUseHttpOnlyCookie(false);
		serializer.setUseSecureCookie(false);
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return serializer;
	}

}