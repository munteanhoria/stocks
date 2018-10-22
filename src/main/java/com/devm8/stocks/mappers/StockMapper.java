package com.devm8.stocks.mappers;

import com.devm8.stocks.entities.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StockMapper {

    public static Optional<Stock> getStockFromJson(String stockJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> hashMap = objectMapper.readValue(stockJson, HashMap.class);

        Map<String, String> values = (Map<String, String>) hashMap.get("Global Quote");
        if (values != null) {
            Stock stock = new Stock();
            stock.setStockName(values.get("01. symbol"));
            stock.setCurrentPrice(Double.valueOf(values.get("05. price")));
            return Optional.of(stock);
        } else return Optional.empty();
    }
}
