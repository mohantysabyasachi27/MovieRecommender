package com.asu.MovieRecommender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api.properties")
public class TwilioConfig {

	@Value("${twilio.accountSID}")
	private String accountSID;
	@Value("${twilio.authToken}")
	private String authToken;
	@Value("${twilio.phoneNumber}")
	private String phoneNumber;

	
	
	public String getAccountSID() {
		return accountSID;
	}

	public void setAccountSID(String accountSID) {
		this.accountSID = accountSID;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
