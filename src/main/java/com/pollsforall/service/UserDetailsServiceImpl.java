package com.pollsforall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pollsforall.DAO.UserRepository;
import com.pollsforall.config.CustomUserDetails;
import com.pollsforall.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			System.out.println("********************loadUserByUsername*****************************");
		User user=	userRepository.getUserByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid Credentials");			
		}
		
		return  new CustomUserDetails(user);	
	}

}
