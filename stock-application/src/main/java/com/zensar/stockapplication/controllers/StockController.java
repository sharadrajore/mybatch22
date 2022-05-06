package com.zensar.stockapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.service.StockService;

@RestController
//@Controller
//@CrossOrigin("*")
// http://localhost:5000/stocks
@RequestMapping(value = "/stocks")
//@Api(value = "This is stock controller")

public class StockController {

	@Autowired
	private StockService stockService;

	// CRUD C -> Created new stock ,R -> Read All stocks/we read perticular stock,U
	// -> updated the stock , D -> deleted the stock

	/*
	 * public StockController() { stocks.add(new Stock(1L, "RIL", "bse", 2610));
	 * stocks.add(new Stock(2L, "Zensar", "bse", 342)); }
	 */

	// http://localhost:5000/stocks/ -> GET

	// Read all stocks

	// http://localhost:5000/stocks?pageNumber=1&pageSize=5&sortBy=marketName
	// @ApiIgnore
	@GetMapping() // Handler method
	//@ApiOperation(value = "Getting all stock info")
	public List<StockDto> getAllStocks(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

		return stockService.getAllStocks(pageNumber, pageSize);
	}

	// Read a specific stock

	// http://localhost:5000/stocks/2200 //@PathVariable
	// @GetMapping("/stocks/{stockId}")
	/*
	 * @RequestMapping(value = "/{stockId}", method = RequestMethod.GET) public
	 * Stock getStock(@PathVariable("stockId") long id) {
	 * 
	 * for (Stock stock : stocks) { if (stock.getStockId() == id) { return stock; }
	 * }
	 * 
	 * return null;
	 * 
	 * }
	 */
	
	// http://localhost:5000/stocks/1 -> GET

	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET)
	//@ApiOperation("Getting stock based on stock id")
	//@ApiResponse(message = "Got the stock of given stock id", code = 200)
	//@ApiParam("stock id has to be greater than 1")
	public StockDto getStock( @PathVariable("stockId") long id) throws InvalidStockIdException {
		return stockService.getStock(id);
	}
	
	// http://localhost:5000/stocks/name/RIL -> GET
	@RequestMapping(value = "/name/{stockName}", method = RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName){
		return stockService.getStockByItsName(stockName);
	}
	
	
	@RequestMapping(value = "/name/{stockName}/price/{stockPrice}", method = RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName,@PathVariable("stockPrice") double stockPrice){
		return stockService.getStockByItsNameAndPrice(stockName,stockPrice);
	}

	// Create a new stock
	// http://localhost:5000/stocks - > POST
	// @PostMapping("/stocks")
	// @RequestMapping(method = RequestMethod.POST) 415
	@PostMapping()
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock,
			@RequestHeader("auth-token") String token) {

		StockDto createStock = stockService.createStock(stock, token);

		if (createStock == null) {
			return new ResponseEntity<StockDto>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<StockDto>(createStock, HttpStatus.CREATED);

	}

	// Delete a specific stock
	// http://localhost:5000/stocks/2 -> delete
	@DeleteMapping("/{stockId}")
	public String deleteStock(@PathVariable("stockId") long stockId) {
		return stockService.deleteStock(stockId);
	}

	// Update an existing stock
	// http://localhost:5000/stocks/2 ->put,patch
	@PutMapping(value = "/{stockId}")
	public StockDto updateStock(@PathVariable int stockId, @RequestBody StockDto stock) throws InvalidStockIdException {
		return stockService.updateStock(stockId, stock);
	}

	// Delete all stocks

	// http://localhost:5000/stocks - delete
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks() {
		String returnResult = stockService.deleteAllStocks();

		return new ResponseEntity<String>(returnResult, HttpStatus.OK);
	}
	
	/*@ExceptionHandler(InvalidStockIdException.class)
	public String handleExcletion() {
		return "Invalid Stock id";
	}*/

}
