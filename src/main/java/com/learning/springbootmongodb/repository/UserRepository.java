package com.learning.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.springbootmongodb.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	
	
	

}
