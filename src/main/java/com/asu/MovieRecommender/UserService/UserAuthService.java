package com.asu.MovieRecommender.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.User.CustomUserDetails;
import com.asu.MovieRecommender.User.User;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserRepoCrud userRepoCrud;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepoCrud.findByUserName(username);
		if (null != user) {
			return new CustomUserDetails(user);
		}
		throw new UsernameNotFoundException("User is not found in the system");
	}

}
