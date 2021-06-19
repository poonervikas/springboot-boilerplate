package com.pollsforall.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private String authority;
	
	
	public Authority(String authority) {
		super();
		this.authority = authority;
	}


	@Override
	public String getAuthority() {
		System.out.println("get Authorities");
		System.out.println(this.authority);
		return this.authority;
	}

}
