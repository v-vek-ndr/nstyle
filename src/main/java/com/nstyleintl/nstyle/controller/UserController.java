package com.nstyleintl.nstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.UserDTO;
import com.nstyleintl.nstyle.exception.DuplicateResourceException;
import com.nstyleintl.nstyle.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            String otp = userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully. OTP sent to email. -> "+otp);
        } catch (DuplicateResourceException e) {
			throw e;
		} catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam String email, @RequestParam String otp) {
        boolean verified = userService.verifyUserOTP(email, otp);
        if (verified) {
            return ResponseEntity.ok("User verified successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP.");
        }
    }
}

