package com.zensar.stockapplication.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("This is the stock model")
@Entity
public class Stock{
	
	@ApiModelProperty("StockId of integer type")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long stockId;
	private String name;
	private String marketName;
	private double price;
	
	
}
