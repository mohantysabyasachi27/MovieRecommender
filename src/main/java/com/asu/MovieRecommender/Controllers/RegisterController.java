package com.asu.MovieRecommender.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Exceptions.RegisterException;
import com.asu.MovieRecommender.Services.RegisterService;
import com.asu.MovieRecommender.User.User;

/**
 * @author kumar
 *This class serves a a controller to the Registration/SignUp Page requests.
 */
@RestController
public class RegisterController {

	@Autowired
	RegisterService registerService;

	@RequestMapping(method = RequestMethod.POST, value = "/register/user/{operationType}")
	public @ResponseBody ResponseEntity<String> register(@RequestBody User userDefine,@PathVariable String operationType) {
	      return registerService.addUserAfterValidation(userDefine,operationType);

	}

	
}