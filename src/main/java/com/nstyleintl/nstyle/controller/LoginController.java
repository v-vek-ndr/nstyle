package com.nstyleintl.nstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.LoginRequest;
import com.nstyleintl.nstyle.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse response = new LoginResponse(true, "Login successful", null, null, null);
		return ResponseEntity.ok(response);
	}

}
