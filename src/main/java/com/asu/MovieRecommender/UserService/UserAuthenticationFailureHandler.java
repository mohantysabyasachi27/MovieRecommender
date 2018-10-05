/*
 * @Author Kumar Prabhu Kalyan
 */
package com.asu.MovieRecommender.UserService;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.asu.MovieRecommender.Exceptions.UserNotFoundException;
import com.asu.MovieRecommender.User.Response;
import com.google.gson.Gson;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;

@Component
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
         Response logInResponse= new Response(HttpStatus.OK.toString(), true, "");
		String strLogInResponse = null;
		
		if(exception.getClass().isAssignableFrom(BadCredentialsException.class))
		{
			
			logInResponse.setStatusCode(HttpStatus.FORBIDDEN.toString());
			logInResponse.setErrorReason(exception.getMessage());
			logInResponse.setSuccess(false);
			response.addHeader("status", String.valueOf(HttpStatus.BAD_REQUEST));
			response.setStatus(403);}else {
				logInResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
				logInResponse.setErrorReason("User does not exist");
				logInResponse.setSuccess(false);
				response.addHeader("status", String.valueOf(HttpStatus.FORBIDDEN));
				response.setStatus(400);
				
			}
		strLogInResponse = new Gson().toJson(logInResponse);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(strLogInResponse);
		out.flush();
		out.close();
		

		// new ResponseEntity<LogInReponse>(new
		// LogInReponse(HttpStatus.FORBIDDEN.toString() , false,
		// exception.getMessage()),HttpStatus.OK);

	}

}
