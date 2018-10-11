
/*
 * @author Kumar Prabhu Kalyan
 */
package com.asu.MovieRecommender.Services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.Exceptions.FetchProfileException;
import com.asu.MovieRecommender.User.User;

@Service
public class ProfileFetchService {

	@Autowired
	UserRepoCrud userRepo;

	public User fetchProfile(String strUserName) throws FetchProfileException {

		if (!StringUtils.isBlank(strUserName)) {
			User userDetails = userRepo.findByUserName(strUserName);
			if (userDetails != null) {

				if (isLogged(strUserName)) {
					userDetails.setUserPassword(null);
					return userDetails;
				} else {
					throw new FetchProfileException("User not logged in");
				}
			} else {
				throw new FetchProfileException("User not found");
			}

		} else {
			throw new FetchProfileException("UserName is blank");
		}
	}

	private boolean isLogged(String strUserName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getPrincipal());
		return authentication.isAuthenticated();
	}
}
