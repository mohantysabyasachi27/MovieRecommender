package com.asu.MovieRecommender.Controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.Services.RatingsService;
import com.asu.MovieRecommender.User.Response;
import com.asu.MovieRecommender.User.UserRatings;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingsController {

	@Autowired
	RatingsService ratingsService;

	@RequestMapping(method = RequestMethod.POST, value = "/users/rating")
	public ResponseEntity<?> rateMovie(@RequestBody UserRatings userRating) {
		try {
			System.out.println("Inside controller");
			if (null != userRating) {
				ratingsService.rateMovie(userRating);
				return new ResponseEntity<Response>(new Response(String.valueOf(HttpStatus.SC_OK), true, ""),
						org.springframework.http.HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Response>(
						new Response(String.valueOf(HttpStatus.SC_BAD_REQUEST), false, "Details are blank or null"),
						org.springframework.http.HttpStatus.BAD_REQUEST);
			}
		} catch (MovieDetailsException e) {
			return new ResponseEntity<Response>(
					new Response(String.valueOf(HttpStatus.SC_BAD_REQUEST), false, e.getErrorMessage()),
					org.springframework.http.HttpStatus.BAD_REQUEST);
		}

	}

}
