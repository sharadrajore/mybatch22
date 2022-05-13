package com.zensar.stockapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("Hiiiiiiiiiiiiiiiiii");
		auth.userDetailsService(userDetailsService);

		/*
		 * auth.inMemoryAuthentication() .withUser("tom") .password("tom@123")
		 * .roles("ADMIN") .and() .withUser("jerry") .password("jerry@123")
		 * .roles("USER");
		 */
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Configure ..........");
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/stocks/create").hasRole("ADMIN")
		.antMatchers("/stocks").permitAll();
		
		
	}

}
