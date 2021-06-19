package com.pollsforall.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pollsforall.config.CustomUserDetails;
import com.pollsforall.config.JwtUtil;
import com.pollsforall.model.JwtRequest;
import com.pollsforall.model.JwtResponse;
import com.pollsforall.model.User;
import com.pollsforall.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	private void authenticate(String username,String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(value="/getToken",method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
  		try {
			authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
  			
  			
		} catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("User not found");
		}
  		
  		UserDetails userDetails= userDetailsService.loadUserByUsername(jwtRequest.getEmail());
  		
  		String token =jwtUtil.generateToken(userDetails);
  		return ResponseEntity.ok(new JwtResponse(token));
  		
  		
	}
	
	@GetMapping("/test")
	@CrossOrigin(origins = "*")
	public String test() {
		return "HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLO";
	}
	
//	 @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
//	    public String currentUserName(Principal principal) {
//	        return principal.getName();
//	    }
//	
//	@RequestMapping(value = "/currentUser", method = RequestMethod.GET)
//	 public String currentUserNameSimple(HttpServletRequest request) { 
//		 Principal principal = request.getUserPrincipal();
//		 return principal.getName();
//	 }
//
	 @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
	    public CustomUserDetails currentUserName(Authentication authentication) {
		 CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		 System.out.println("------My authorities-------");
		 System.out.println(customUserDetails.getAuthorities());
	        //return authentication.getName();
		 return customUserDetails;
	    }
	 
	 	
}
