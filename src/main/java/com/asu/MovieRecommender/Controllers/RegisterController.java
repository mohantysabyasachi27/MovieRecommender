package com.asu.MovieRecommender.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.MovieRecommenderApplication;
import com.asu.MovieRecommender.Services.RegisterService;
import com.asu.MovieRecommender.User.Response;
import com.asu.MovieRecommender.User.User;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class RegisterController {
	public static Logger logger = LogManager.getLogger(RegisterController.class);
	@Autowired
	RegisterService registerService;

	@RequestMapping(method = RequestMethod.POST, value = "/register/user/{operationType}")
	public @ResponseBody ResponseEntity<Response> register(@RequestBody User userDefine,@PathVariable String operationType) {
		
		logger.info("Entering the Register User Method");
	      return registerService.addUser(userDefine,operationType);

	}	 

	
}