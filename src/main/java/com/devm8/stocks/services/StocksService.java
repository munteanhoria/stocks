package com.devm8.stocks.services;

import com.devm8.stocks.entities.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StocksService {

    List<Stock> getAllStocks();

    Stock getStockDetails(String stockName);

}
