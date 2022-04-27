package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.entity.StockRequest;
import com.zensar.stockapplication.entity.StockResponse;

public interface StockService {
	
	List<StockResponse> getAllStocks(int pageNumber,int pageSize);
	
	StockResponse getStock(long id);
	
	StockResponse createStock(StockRequest stock, String token);

	String deleteStock(long stockId);
	
	StockResponse updateStock( int stockId,  StockRequest stock);
	
	String deleteAllStocks();
}
