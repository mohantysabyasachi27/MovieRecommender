package com.asu.MovieRecommender.User;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails{

	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Security -- Custom UserDetails object which the auth manager uses.
	 */

	public CustomUserDetails(final User user) {
		super(user);
	}

	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(String firstName, String lastName, String userName, String userPassword,
			String userEmailId, String userContactNo, Date userDOB, String userCity, String userAddress,
			String userPinCode) {
		super(firstName, lastName, userName, userPassword, userEmailId, userContactNo, userDOB, userCity, userAddress,
				userPinCode);
		// TODO Auto-generated constructor stub
	}



	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getRoleId()))
				.collect(Collectors.toList());	
	}

	@Override
	public String getPassword() {
		return super.getUserPassword();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
