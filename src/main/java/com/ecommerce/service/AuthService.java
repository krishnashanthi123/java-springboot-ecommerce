package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.LoginRequestDto;
import com.ecommerce.dtoRequest.SignupRequestDto;
import com.ecommerce.dtoResponse.LoginResponseDto;
import com.ecommerce.dtoResponse.SignupResponseDto;
import com.ecommerce.entity.Seller;
import com.ecommerce.repository.SellerRepository;
import com.ecommerce.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= SIGNUP =================
    public ResponseEntity<?> signup(SignupRequestDto dto) {

        Optional<Seller> existingUser =
                sellerRepository.findByEmail(dto.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Email Already Exists");
        }

        Seller seller = new Seller();
        seller.setSellerUsername(dto.getSellerUsername());
        seller.setEmail(dto.getEmail());

        // IMPORTANT: encode password
        seller.setSellerPassword(passwordEncoder.encode(dto.getPassword()));

        seller.setRole(dto.getRole());

        Seller savedSeller = sellerRepository.save(seller);

        SignupResponseDto response = new SignupResponseDto();
        response.setSellerId(savedSeller.getSellerId());
        response.setSellerUsername(savedSeller.getSellerUsername());
        response.setEmail(savedSeller.getEmail());
        response.setRole(savedSeller.getRole());
        response.setMessage("User Registered Successfully");

        return ResponseEntity.ok(response);
    }

    // ================= LOGIN =================
    public ResponseEntity<?> login(LoginRequestDto requestDto) {

        Optional<Seller> optionalSeller =
                sellerRepository.findBySellerUsername(requestDto.getUsername());

        if (optionalSeller.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Username");
        }

        Seller seller = optionalSeller.get();

        // password check
        if (!passwordEncoder.matches(
                requestDto.getPassword(),
                seller.getSellerPassword())) {

            return ResponseEntity.badRequest().body("Invalid Password");
        }

        // generate JWT
        String token = jwtUtil.generateToken(seller.getSellerUsername());

        // response
        LoginResponseDto response = new LoginResponseDto();
        response.setSellerId(seller.getSellerId());
        response.setSellerUsername(seller.getSellerUsername());
        response.setEmail(seller.getEmail());
        response.setRole(seller.getRole());
        response.setToken(token);
        response.setMessage("Login Successful");

        return ResponseEntity.ok(response);
    }
}