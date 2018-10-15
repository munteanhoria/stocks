package com.devm8.stocks.entities;

public class Stock {

    private Double currentPrice;

    private Double currentDifference;

    private String stockName;

    public Stock(){

    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getCurrentDifference() {
        return currentDifference;
    }

    public void setCurrentDifference(Double currentDifference) {
        this.currentDifference = currentDifference;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
