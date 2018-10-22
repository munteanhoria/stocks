package com.devm8.stocks.services.stocks;

import com.devm8.stocks.drivers.StocksDriver;
import com.devm8.stocks.entities.Stock;
import com.devm8.stocks.mappers.StockMapper;
import com.devm8.stocks.services.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class StockServiceImpl implements StocksService {

    private StocksDriver stocksDriver;

    @Autowired
    public StockServiceImpl(StocksDriver stocksDriver) {

        this.stocksDriver = stocksDriver;
    }

    @Override
    public Optional<Stock> getStockDetails(String stockName) throws IOException {
        String stockDetailsJSON = stocksDriver.getStockDetails(stockName);
        System.out.println(stockDetailsJSON);
        return StockMapper.getStockFromJson(stockDetailsJSON);
    }
}

