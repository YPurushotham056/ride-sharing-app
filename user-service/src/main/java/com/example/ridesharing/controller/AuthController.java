package com.example.ridesharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ridesharing.model.User;
import com.example.ridesharing.kafka.UserKafkaConsumer;
import com.example.ridesharing.kafka.UserKafkaProducer;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserKafkaProducer userKafkaProducer;
    private final PasswordEncoder passwordEncoder;
    private final UserKafkaConsumer userKafkaConsumer;
    
    public AuthController(UserKafkaProducer userKafkaProducer,UserKafkaConsumer userKafkaConsumer, PasswordEncoder passwordEncoder) {
        this.userKafkaProducer = userKafkaProducer;
        this.passwordEncoder = passwordEncoder;
        this.userKafkaConsumer = userKafkaConsumer;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userKafkaProducer.sendMessage(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered and message sent to Kafka");
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        // Fetch user from Kafka topic storage
        User storedUser = userKafkaConsumer.getUserByEmail(user.getEmail());

        if (storedUser != null && passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
