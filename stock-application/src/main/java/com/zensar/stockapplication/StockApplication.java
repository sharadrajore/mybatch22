package com.zensar.stockapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ImportResource("Beans.xml")
public class StockApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		
		System.out.println(1==1);
		SpringApplication.run(StockApplication.class, args);  
		
		
		System.out.println(1==1);
	}
	
	
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}




	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}




