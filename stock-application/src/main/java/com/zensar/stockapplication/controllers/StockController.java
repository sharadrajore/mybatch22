package com.zensar.stockapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;

@RestController
//@CrossOrigin("*")
// http://localhost:5000/stocks
@RequestMapping("/stocks")
public class StockController {
	
	
	// CRUD C -> Created new stock ,R -> Read All stocks/we read perticular stock,U -> updated the stock , D -> deleted the stock

	static List<Stock> stocks = new ArrayList<Stock>();

	static {
		stocks.add(new Stock(1L, "RIL", "bse", 2610));
		stocks.add(new Stock(2L, "Zensar", "bse", 342));

	}

	/*
	 * public StockController() { stocks.add(new Stock(1L, "RIL", "bse", 2610));
	 * stocks.add(new Stock(2L, "Zensar", "bse", 342)); }
	 */

	// http://localhost:5000/stocks/
	
	// Read all stocks

	// @GetMapping("/stocks")
	@RequestMapping(method = RequestMethod.GET)
	public List<Stock> getAllStocks() {
		return stocks;
	}
	

	// Read a specific stock

	// http://localhost:5000/stocks/2200 //@PathVariable
	// @GetMapping("/stocks/{stockId}")
	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET)
	public Stock getStock(@PathVariable("stockId") long id) {

		for (Stock stock : stocks) {
			if (stock.getStockId() == id) {
				return stock;
			}
		}

		return null;

	}

	
	// Create a new stock
	// http://localhost:5000/stocks - > POST
	// @PostMapping("/stocks")
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock,@RequestHeader("auth-token")String token) {
		
		if(token.equals("sr43993")) {
			stocks.add(stock);
		}else {
			return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
	}  
	
	
	
    // Delete a specific stock
	// http://localhost:5000/stocks/2 -> delete
	@DeleteMapping("/{stockId}")
	public String deleteStock(@PathVariable("stockId") long stockId) {

		for (Stock stock : stocks) {
			if (stock.getStockId() == stockId) {
				stocks.remove(stock);
				return "Stock deleted with stock id " + stockId;
			}
		}
		return "Sorry,stock id is not available";
	}

	// Update an existing stock
	// http://localhost:5000/stocks/2 ->put,patch
	@PutMapping("/{stockId}")
	public Stock updateStock(@PathVariable int stockId,@RequestBody Stock stock) {
		Stock availableStock = getStock(stockId);
		availableStock.setMarketName(stock.getMarketName());
		availableStock.setName(stock.getName());
		availableStock.setPrice(stock.getPrice());

		return availableStock;
	}
	
	
	// Delete all stocks
	
	// http://localhost:5000/stocks  - delete
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks(){
		return new ResponseEntity<String>("All stocks deleted successfullyyyy",HttpStatus.OK);
	}
	
	
	

}
