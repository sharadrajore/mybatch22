package com.zensar.stockapplication.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

// /info /health /beans
@Endpoint(id = "custom",enableByDefault = true)
@Component
public class CustomEndpoint {
	
	@ReadOperation
	public String myOwnCustomEndpoint() {
		return "My Own Endpoint";
	}

}
