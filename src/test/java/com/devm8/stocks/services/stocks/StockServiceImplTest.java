package com.devm8.stocks.services.stocks;

import com.devm8.stocks.drivers.StocksDriver;
import com.devm8.stocks.entities.Stock;
import com.devm8.stocks.services.StocksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class StockServiceImplTest {

    private static final String DRIVER_RESPONSE = "{    \"Global Quote\": {        \"01. symbol\": \"MSFT\",        \"02. open\": \"108.9300\",        \"03. high\": \"110.8400\",        \"04. low\": \"108.2200\",        \"05. price\": \"108.6600\",        \"06. volume\": \"31434498\",        \"07. latest trading day\": \"2018-10-19\",        \"08. previous close\": \"108.5000\",        \"09. change\": \"0.1600\",        \"10. change percent\": \"0.1475%\"    }}";

    @Mock
    private StocksDriver stocksDriver;

    private StocksService stocksService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        stocksService = new StockServiceImpl(stocksDriver);
    }

    @Test
    public void getStockDetails() throws Exception {

        when(stocksDriver.getStockDetails("MSFT")).thenReturn(DRIVER_RESPONSE);

        Optional<Stock> msft = stocksService.getStockDetails("MSFT");

        Assert.assertTrue(msft.isPresent());
        Assert.assertEquals((Double) 108.6600, msft.get().getCurrentPrice());
        Assert.assertEquals("MSFT", msft.get().getStockName());
    }

}