package com.zensar.stockapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;

@RestController
public class StockController {

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

	// @GetMapping("/stocks")
	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Stock> getAllStocks() {
		return stocks;
	}

	// http://localhost:5000/stocks/2200 //@PathVariable
	// @GetMapping("/stocks/{stockId}")
	@RequestMapping(value = "/stocks/{stockId}", method = RequestMethod.GET)
	public Stock getStock(@PathVariable("stockId") long id) {

		for (Stock stock : stocks) {
			if (stock.getStockId() == id) {
				return stock;
			}
		}

		return null;

	}

	

	// http://localhost:5000/stocks - > POST
	// @PostMapping("/stocks")
	@RequestMapping(value = "/stocks", method = RequestMethod.POST)
	public Stock createStock(@RequestBody Stock stock) {
		stocks.add(stock);
		return stock;
	}

	// http://localhost:5000/stocks/2 -> delete
	@DeleteMapping("/stocks/{stockId}")
	public String deleteStock(@PathVariable("stockId") long stockId) {

		for (Stock stock : stocks) {
			if (stock.getStockId() == stockId) {
				stocks.remove(stock);
				return "Stock deleted with stock id " + stockId;
			}
		}
		return "Sorry,stock id is not available";
	}

	// http://localhost:5000/stocks/2 ->put,patch
	@PutMapping("/stocks/{stockId}")
	public Stock updateStock(@PathVariable int stockId,@RequestBody Stock stock) {
		Stock availableStock = getStock(stockId);
		availableStock.setMarketName(stock.getMarketName());
		availableStock.setName(stock.getName());
		availableStock.setPrice(stock.getPrice());

		return availableStock;
	}

}
