package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	// private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StockDto> getAllStocks(int pageNumber, int pageSize) {
		Page<Stock> pageStocks = stockRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("stockId").descending()));
		List<Stock> content = pageStocks.getContent();

		List<StockDto> stockResponses = new ArrayList<>();

		for (Stock stock : content) {
			StockDto mapToResponse = modelMapper.map(stock, StockDto.class);
			stockResponses.add(mapToResponse);
		}
		return stockResponses;
	}

	public List<StockDto> getStockByItsName(String stockName) {

		List<Stock> findStockByName = stockRepository.findStockByItsName(stockName);

		List<StockDto> stocks = new ArrayList<StockDto>();

		for (Stock st : findStockByName) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}

		return stocks;

	}

	public List<StockDto> getStockByItsNameAndPrice(String stockName, double price) {

		List<Stock> findStockByNameAndPrice = stockRepository.findStockByItsNameAndPrice(stockName, price);

		List<StockDto> stocks = new ArrayList<StockDto>();

		for (Stock st : findStockByNameAndPrice) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}

		return stocks;

	}

	@Override
	public StockDto getStock(long id) throws InvalidStockIdException {

		Optional<Stock> optionalStock = stockRepository.findById(id);

		Stock stock = null;
		if (optionalStock.isPresent()) {
			stock = optionalStock.get();
			return modelMapper.map(stock, StockDto.class);
		} else {
			System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiii");
			throw new InvalidStockIdException("Stock is not available with stock id" + id);
		}

		/*
		 * StockResponse stockResponse = new StockResponse();
		 * stockResponse.setStockId(stock.getStockId());
		 * stockResponse.setName(stock.getName());
		 * stockResponse.setPrice(stock.getPrice());
		 * stockResponse.setMarketName(stock.getMarketName());
		 */

		/*
		 * Optional<Stock> stock1 = stocks.stream().filter(stock -> stock.getStockId()
		 * == id).findAny();
		 * 
		 * if (stock1.isPresent()) { return stock1.get(); } else { return
		 * stock1.orElseGet(() -> { return new Stock(); }); }
		 */

	}

	@Override
	public StockDto createStock(StockDto stockRequest, String token) {

		// Stock newStock = mapToStock(stock);

		Stock newStock = modelMapper.map(stockRequest, Stock.class);

		// return stockRepository.save(stock);

		if (token.equals("sr43993")) {
			Stock save = stockRepository.save(newStock);
			return modelMapper.map(save, StockDto.class);

		} else {
			return null;
		}

	}

	@Override
	public String deleteStock(long stockId) {

		stockRepository.deleteById(stockId);
		return "Stock deleted with stock id " + stockId;

		/*
		 * for (Stock stock : stocks) { if (stock.getStockId() == stockId) {
		 * stocks.remove(stock); return "Stock deleted with stock id " + stockId; } }
		 * return "Sorry,stock id is not available";
		 */
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stockDto) throws InvalidStockIdException {

		StockDto stockResponse = getStock(stockId);

		// Stock stock2 = mapToEntity(stock);

		Stock stock2 = modelMapper.map(stockResponse, Stock.class);

		if (stock2 != null) {
			stock2.setPrice(stockDto.getPrice());
			stock2.setName(stockDto.getName());
			stock2.setMarketName(stockDto.getMarketName());
			stock2.setStockId(stockId);
			Stock stock3 = stockRepository.save(stock2);
			return modelMapper.map(stock3, StockDto.class);

		}

		return null;
	}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		// stocks.removeAll(stocks);
		return "All stocks are deleted successfullyyyy";
	}

	/*
	 * private Stock mapToEntity(StockDto stockDto) {
	 * 
	 * Stock newStock = new Stock();
	 * 
	 * newStock.setMarketName(stockDto.getMarketName());
	 * newStock.setName(stockDto.getName()); newStock.setPrice(stockDto.getPrice());
	 * 
	 * return newStock;
	 * 
	 * }
	 */

	/*
	 * private StockDto mapToDto(Stock stock) {
	 * 
	 * StockDto stockResponse = new StockDto();
	 * 
	 * stockResponse.setStockId(stock.getStockId());
	 * 
	 * stockResponse.setPrice(stock.getPrice());
	 * 
	 * stockResponse.setName(stock.getName());
	 * 
	 * stockResponse.setMarketName(stock.getMarketName());
	 * 
	 * return stockResponse;
	 * 
	 * }
	 */

}
