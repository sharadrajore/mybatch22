package com.zensar.stockapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{

}
