package com.pollsforall.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pollsforall.DAO.RoleRepository;
import com.pollsforall.DAO.UserRepository;
import com.pollsforall.model.User;
import com.pollsforall.model.UserRole;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User u=userRepository.getUserByEmail(user.getEmail());
		if(u!=null) {
			throw new Exception("A user with this email id already exist!!");
		} 
		else {
			for (UserRole userRole:userRoles) {
				System.out.println(userRole.getRole().getRoleName());
				roleRepository.save(userRole.getRole());
			}
			
			
			user.setUserRoles(userRoles);
			u=userRepository.save(user);
		}
	return u;
}
	
	@Override
	public User getUserByEmailId(String email) throws Exception{
		User u=userRepository.getUserByEmail(email);
		if(u==null) {
			throw new Exception("No user with such email-id!!");
		}
		return u;
	}
	
}
