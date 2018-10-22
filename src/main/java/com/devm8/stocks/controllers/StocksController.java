package com.devm8.stocks.controllers;

import com.devm8.stocks.entities.Stock;
import com.devm8.stocks.services.MailService;
import com.devm8.stocks.services.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StocksController {

    @Autowired
    private StocksService stocksService;

    @Autowired
    private MailService mailService;


    public ResponseEntity<?> getAllSupportedStocks() {
        return ResponseEntity.notFound().build();
    }

}
