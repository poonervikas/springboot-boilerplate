package com.pollsforall.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pollsforall.model.Authority;
import com.pollsforall.model.User;


public class CustomUserDetails implements UserDetails{
	
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user=user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			Set<Authority> setOfAuthorities=new HashSet<>();
			
		this.user.getUserRoles().forEach(userRole->{
			setOfAuthorities.add(new Authority(userRole.getRole().getRoleName()));
		});
		return setOfAuthorities;
	}

	@JsonProperty(access = Access.WRITE_ONLY) 
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@JsonProperty(access = Access.WRITE_ONLY) 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY) 
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY) 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY) 
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	

}
