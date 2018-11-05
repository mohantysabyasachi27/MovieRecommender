/*
 * @Author Kumar Prabhu Kalyan
 */
package com.asu.MovieRecommender.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.asu.MovieRecommender.User.Response;

import com.google.gson.Gson;


@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication exception)
			throws IOException, ServletException {
		 Response logInResponse= new Response(HttpStatus.OK.toString(), true, "");
			String strLogInResponse = null;
			
		request.getSession(true);
		response.addHeader("status", "200");response.setStatus(200);
		response.addHeader("Error", "");
		response.addHeader("success", "true");
		
		
		strLogInResponse = new Gson().toJson(logInResponse);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(strLogInResponse);
		out.flush();
		out.close();
		
	}

	
}
