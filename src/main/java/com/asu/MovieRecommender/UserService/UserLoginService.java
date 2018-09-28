package com.asu.MovieRecommender.UserService;

import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.User.User;

public interface UserLoginService {

	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Security
	 */
	
	User getLoggedUser();

	CustomUserDetails getLoggedUserDetails();

	void logout();

	boolean isLoggedIn();

}
