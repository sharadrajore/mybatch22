package com.zensar.stockapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ImportResource("Beans.xml")
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
		
	}

}
