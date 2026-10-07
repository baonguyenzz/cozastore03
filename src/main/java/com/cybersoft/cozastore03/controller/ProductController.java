package com.cybersoft.cozastore03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.cozastore03.repository.UserRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {

        return new ResponseEntity<>("Get all product success", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertProduct() {
        return new ResponseEntity<>("Insert product success", HttpStatus.OK);
    }

}
