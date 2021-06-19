package com.pollsforall.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pollsforall.model.User;
import com.pollsforall.model.UserRole;


public interface UserService {

	
	//create user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception ;
	
	//get user by email id
	public User getUserByEmailId(String email) throws Exception;
}
