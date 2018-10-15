package com.devm8.stocks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public ResponseEntity<Map<String, String>> home() {
        return new ResponseEntity<>(Collections.singletonMap("message", "Hello"), HttpStatus.OK);
    }
}
