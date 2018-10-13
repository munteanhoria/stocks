package com.devm8.stocks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;

@Controller("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, String>> home() {
        return new ResponseEntity<>(Collections.singletonMap("message", "Hello"), HttpStatus.OK);
    }
}
