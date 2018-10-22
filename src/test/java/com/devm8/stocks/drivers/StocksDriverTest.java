package com.devm8.stocks.drivers;

import org.junit.Test;

import static org.junit.Assert.*;

public class StocksDriverTest {



    @Test
    public void getStockDetails() throws Exception {
        StocksDriver stocksDriver = new StocksDriver("MZBOT5SHNJCLD4EX");

        String msft = stocksDriver.getStockDetails("MSFT");

        System.out.println(msft);
    }

}