/*
 * @author Kumar Prabhu Kalyan
 */
package com.asu.MovieRecommender.Controllers;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Exceptions.ChangePasswordException;
import com.asu.MovieRecommender.Exceptions.FetchProfileException;
import com.asu.MovieRecommender.Services.ChangePasswordService;
import com.asu.MovieRecommender.Services.ProfileFetchService;
import com.asu.MovieRecommender.User.ChangePassword;
import com.asu.MovieRecommender.User.Response;
import com.asu.MovieRecommender.User.User;

import io.netty.channel.ChannelPromiseAggregator;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

	@Autowired
	ProfileFetchService profileFetch;
	@Autowired
	ChangePasswordService changePasswordService;

	@RequestMapping(method = RequestMethod.GET, value = "/profile/fetch/{userName}")
	public @ResponseBody ResponseEntity<?> fetchProfile(@PathVariable String userName) {
		User userDetails = null;
		try {
			userDetails = profileFetch.fetchProfile(userName);
			return new ResponseEntity<User>(userDetails, HttpStatus.OK);
		} catch (FetchProfileException ex) {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.FORBIDDEN.toString(), false, ex.getErrorMessage()), HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/profile/changePassword")
	public @ResponseBody ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
		Response response = new Response(null, false, null);
		ResponseEntity<Response> responseBody = new ResponseEntity<Response>(response, HttpStatus.OK);

		if (null != changePassword) {

			try {
				changePasswordService.changePassword(changePassword.getUserName(), changePassword.getOldPassword(),
						changePassword.getNewPassword());
				response.setStatusCode(HttpStatus.OK.toString());
				response.setSuccess(true);
			} catch (ChangePasswordException e) {
				response.setErrorReason(e.getErrorMessage());
				response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			}
		}
		return responseBody;
	}
}
