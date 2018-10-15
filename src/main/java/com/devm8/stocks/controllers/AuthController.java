package com.devm8.stocks.controllers;

import com.devm8.stocks.entities.User;
import com.devm8.stocks.entities.request.LoginRequest;
import com.devm8.stocks.entities.request.SignUpRequest;
import com.devm8.stocks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
