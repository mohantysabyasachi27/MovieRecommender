package com.asu.MovieRecommender.Services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.Exceptions.ChangePasswordException;
import com.asu.MovieRecommender.User.User;

import io.netty.util.internal.StringUtil;

@Service
public class ChangePasswordService {

	@Autowired
	private UserRepoCrud userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	public boolean oldPasswordMatches(String strOldPassword, User user) throws ChangePasswordException {

		if (!StringUtils.isBlank(strOldPassword) && null != user) {

			if (passwordEncoder.matches(strOldPassword, user.getUserPassword()))
				return true;
			else
				throw new ChangePasswordException("Wrong OldPassword");
		} else {
			throw new ChangePasswordException("User Data invalid");
		}

	}

	private boolean isLogged(String strUserName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// System.out.println(authentication.getPrincipal());
		return authentication.isAuthenticated();
	}

	public void changePassword(String strUserName, String strOldPassword, String strNewPassword)
			throws ChangePasswordException {
		if (StringUtils.isBlank(strOldPassword) || StringUtils.isBlank(strNewPassword)
				|| strOldPassword.equals(strNewPassword))
			throw new ChangePasswordException("The new password cant be the same as old password");
		if (!StringUtils.isBlank(strUserName)) {
			if (isLogged(strUserName)) {
				User user = userRepo.findByUserName(strUserName);
				System.out.println(user.getUserPassword());
				if (oldPasswordMatches(strOldPassword, user) && !StringUtils.isBlank(strNewPassword)) {
					user.setUserPassword(passwordEncoder.encode(strNewPassword));
					userRepo.save(user);
				}

			} else {
				throw new ChangePasswordException("User not Logged in, Please login and try");
			}
		} else {
			throw new ChangePasswordException("Invalid Data");
		}
	}
}
