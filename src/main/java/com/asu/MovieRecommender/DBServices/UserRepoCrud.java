package com.asu.MovieRecommender.DBServices;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.asu.MovieRecommender.User.User;

public interface UserRepoCrud extends MongoRepository<User, String>
{
   public User findByUserName( String userName);
   public User findByUserEmailId(String userEmailId );
   public User findByUserContactNo(String userContactNo);
}
