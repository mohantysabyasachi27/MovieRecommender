package com.asu.MovieRecommender.DBServices;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.asu.MovieRecommender.User.User;

/**
 * @author kumar
 * This serves as a Mongo Repository interface for CRUD operations.
 *
 */
public interface UserRepoCrud extends MongoRepository<User, String> {
	
	public User findByUserName(String userName);

	public User findByUserEmailId(String userEmailId);

	public User findByUserContactNo(String userContactNo);
}
