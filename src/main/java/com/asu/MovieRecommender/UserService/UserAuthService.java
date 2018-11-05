package com.asu.MovieRecommender.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.User.User;

@Service
public class UserAuthService implements UserDetailsService {

	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Security
	 */
	
	@Autowired
	private UserRepoCrud userRepoCrud;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepoCrud.findByUserName(username);
		if (null != user) {
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			Authentication authentication= new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities()) ; 
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return customUserDetails;
		}
		throw new UsernameNotFoundException("User is not found in the system");
	}

}
