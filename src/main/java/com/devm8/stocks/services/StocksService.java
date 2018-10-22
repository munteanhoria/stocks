package com.devm8.stocks.services;

import com.devm8.stocks.entities.Stock;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface StocksService {

    Optional<Stock> getStockDetails(String stockName) throws IOException;

}
