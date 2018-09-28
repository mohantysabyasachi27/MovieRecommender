package com.asu.MovieRecommender.UserService;

import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.User.User;

public interface UserLoginService {

	User getLoggedUser();

	CustomUserDetails getLoggedUserDetails();

	void logout();

	boolean isLoggedIn();

}
