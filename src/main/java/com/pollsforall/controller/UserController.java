package com.pollsforall.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pollsforall.model.Role;
import com.pollsforall.model.User;
import com.pollsforall.model.UserRole;
import com.pollsforall.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create user
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public  ResponseEntity<User> createUser(@RequestBody User user)throws ResponseStatusException {
	
		try {
			Set<UserRole> userRoles=new HashSet<>();
			
			Role defaultRole=new Role();
			defaultRole.setRoleName("User");
			
			UserRole userRole=new UserRole();
			userRole.setRole(defaultRole);
			userRole.setUser(user);
			
			userRoles.add(userRole);
			User u=userService.createUser(user, userRoles);
			ResponseEntity<User> response=new ResponseEntity<User>(u, HttpStatus.CREATED);
			return response;
			
			
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
 	}
	
	@GetMapping("/test")
	@CrossOrigin(origins = "*")
	public  String privateApi()throws ResponseStatusException {
	return "You are now accessing private api results";
	}
	@GetMapping("/{email}")
	@CrossOrigin(origins = "*")
	public  ResponseEntity<User> getUserByEmailId(@PathVariable String email)throws ResponseStatusException {
		try {
			User u=userService.getUserByEmailId(email);
			ResponseEntity<User> response=new ResponseEntity<User>(u, HttpStatus.OK);
			return response;
			
			
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
}
