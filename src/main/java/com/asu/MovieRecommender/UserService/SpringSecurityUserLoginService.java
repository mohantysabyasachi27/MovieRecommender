package com.asu.MovieRecommender.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.User.User;

public class SpringSecurityUserLoginService implements UserLoginService {
	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Sessions -- This class is to check the Loggedin user details from sessions context
	 */

	@Autowired
	private UserLoginService userLoginService;
	
	
	@Autowired
	private UserRepoCrud userRepoCrud;

	public SpringSecurityUserLoginService() {

	}

	@Override
	public void logout() {
		//SecurityContextHolder.getContext().setAuthentication(null);
		System.out.println(userLoginService.getLoggedUserDetails().getUserName());
		SecurityContextHolder.clearContext();
	}

	@Override
	public boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return isAuthenticated(authentication);
	}

	@Override
	public CustomUserDetails getLoggedUserDetails() {
		CustomUserDetails loggedUserDetails = null;
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		System.out.println(sessionId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (isAuthenticated(authentication)) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof CustomUserDetails) {
				loggedUserDetails = ((CustomUserDetails) principal);
			}
		}
		return loggedUserDetails;
	}

	private boolean isAuthenticated(Authentication authentication) {
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}

	@Override
	public User getLoggedUser() {
		User loggedUser = null;
		CustomUserDetails userDetails = getLoggedUserDetails();
		if (userDetails != null) {
			loggedUser = userRepoCrud.findByUserName(userDetails.getUserName());
		}
		return loggedUser;
	}

}
