package com.zensar.stockapplication.entity;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("This is the stock model")
public class StockRequest {
	
	private long stockId;
	private String name;
	private String marketName;
	private double price;

}