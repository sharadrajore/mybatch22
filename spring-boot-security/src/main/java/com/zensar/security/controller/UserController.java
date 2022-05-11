package com.zensar.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.security.entity.AuthenticateRequest;
import com.zensar.security.util.JwtUtils;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;
	

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticateRequest request) {
		System.out.println("Hi");
		try {
			System.out.println("authenticate");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			System.out.println("Exception");
			throw new BadCredentialsException(request.getUsername());
		}

		String jwtToken = jwtUtils.generateToken(request.getUsername());
		System.out.println(jwtToken);

		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	

	
	// localhost:8080/all

	@GetMapping("/all")
	public String hello() {
		return "Hello  !!!";
	}

	@GetMapping("/user")
	public String user() {
		return "Hello User !!!!";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Hello Admin !!!!!";
	}

}
