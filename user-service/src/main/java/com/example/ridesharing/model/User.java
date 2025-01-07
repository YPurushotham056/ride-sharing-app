package com.example.ridesharing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role;
    private String gender;
    private String mobileNumber;

    // Getters and Setters
    public String getEmail() { 
    	return email; 
    	}
    public void setEmail(String email) { 
    	this.email = email; 
    	}
    
    public String getUsername() {
    	return username; 
    	}
    public void setUsername(String username) {
    	this.username = username; 
    	}
    
    public String getPassword() {
    	return password; 
    	}
    public void setPassword(String password) { 
    	this.password = password; 
    	}
    
    public String getRole() {
    	return role; 
    	}
    public void setRole(String role) {
    	this.role = role; 
    	}
    
    public String getGender() {
    	return gender; 
    	}
    public void setGender(String gender) { 
    	this.gender = gender; 
    	}
    
    public String getMobileNumber() {
    	return mobileNumber; 
    	}
    public void setMobileNumber(String mobileNumber) {
    	this.mobileNumber = mobileNumber; 
    	}
}
