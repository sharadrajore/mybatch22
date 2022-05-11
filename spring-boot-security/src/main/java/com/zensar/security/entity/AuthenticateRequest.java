package com.zensar.security.entity;

import lombok.Data;

@Data
public class AuthenticateRequest {
	
	private String username;
	private String password;

}
