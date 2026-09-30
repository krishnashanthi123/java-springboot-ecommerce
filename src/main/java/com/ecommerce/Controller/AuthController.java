package com.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtoRequest.LoginRequestDto;
import com.ecommerce.dtoRequest.SignupRequestDto;
import com.ecommerce.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto) {
	    return ResponseEntity.ok(authService.signup(requestDto));
	
		
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto) {
	    return ResponseEntity.ok(authService.login(requestDto));
	}

}
