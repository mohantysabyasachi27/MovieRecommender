
package com.asu.MovieRecommender.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;
import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.UserService.UserLoginService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class LoginController {
	@Autowired
	private UserLoginService userLoginService;
	
	public static Logger logger = LogManager.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET,value="/login/creds")
	public Principal loginGoogle(Principal principal) throws Exception {	
     return principal;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<Boolean> postLoginGoogle() throws Exception {
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/oauth2")
	public String LoginGoogle() throws Exception {
		return "welcome to google login";
	}

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
		logger.info("Logging out : {}", userLoginService.getLoggedUserDetails().getUsername());
		userLoginService.logout();
		return Boolean.TRUE;
	}

}
