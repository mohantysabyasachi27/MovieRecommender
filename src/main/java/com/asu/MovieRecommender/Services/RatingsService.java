package com.asu.MovieRecommender.Services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.Exceptions.MovieDetailsException;
import com.asu.MovieRecommender.User.UserRatings;

@Service
public class RatingsService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void rateMovie(UserRatings userRating) throws MovieDetailsException
	{
		String userName = userRating.getUserName();
		String movieId = userRating.getMovieId();
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(movieId))
		{
			Date dateAdded = new Date();
			Query query = new Query();
			Criteria criteria = new Criteria("userName").is(userName).and("movieId").is(movieId);
			query.addCriteria(criteria);
			List<UserRatings> userRatings = mongoTemplate.find(query, UserRatings.class, "UserRatings");
		    
			if(userRatings.size()>0)
				throw new MovieDetailsException("Movie has already been rated");
			else
			{
				userRating.setDateAdded(dateAdded);
				mongoTemplate.save(userRating);
			}
	}else {
		throw new MovieDetailsException("UserName or MovieId is blank");
	}
}
}
