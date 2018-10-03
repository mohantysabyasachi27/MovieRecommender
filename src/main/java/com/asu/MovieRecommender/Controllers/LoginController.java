package com.asu.MovieRecommender.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;
import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.UserService.UserLoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Login Service")
public class LoginController {
	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping("/login/{id}")
	public String loginGoogle() throws Exception {
		return "welcome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String postLoginGoogle() throws Exception {
		return "welcome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/oauth2")
	public String LoginGoogle() throws Exception {
		return "welcome to google login";
	}

	@ApiOperation(value = "Service to get the user details", nickname = "User Details", response = String.class, produces = "application/json")
	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String getGeofences(HttpServletRequest request, ModelMap model) {
		CustomUserDetails loggedinUser = userLoginService.getLoggedUserDetails();
		if (null == loggedinUser) {
			return "You are logged out of the system";
		}
		model.addAttribute(MovieRecommenderConstants.FIRST_NAME, loggedinUser.getFirstName());
		model.addAttribute(MovieRecommenderConstants.LAST_NAME, loggedinUser.getLastName());
		model.addAttribute(MovieRecommenderConstants.USER_NAME, loggedinUser.getUsername());
		return "Welcome....You have successfully logged in";
	}

	@RequestMapping("/logout")
	public Boolean logout() throws Exception {
		userLoginService.logout();
		return Boolean.TRUE;
	}

}
