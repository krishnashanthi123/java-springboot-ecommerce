package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.LoginRequestDto;
import com.ecommerce.dtoRequest.SellerRequestDto;
import com.ecommerce.dtoResponse.LoginResponseDto;
import com.ecommerce.dtoResponse.MessageResponse;
import com.ecommerce.dtoResponse.SellerResponseDto;
import com.ecommerce.entity.Seller;
import com.ecommerce.entity.SellerInfo;
import com.ecommerce.exception.SellerNotFoundException;
import com.ecommerce.repository.SellerInfoRepository;
import com.ecommerce.repository.SellerRepository;
import com.ecommerce.security.JwtUtil;

@Service
public class SellerService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    // REGISTER SELLER
    public ResponseEntity<?> registerSeller(SellerRequestDto dto) {

        if (sellerRepository
                .findBySellerUsername(dto.getSellerUsername())
                .isPresent()) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(
                            "Username Already Exists"));
        }

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerName(dto.getSellerName());
        sellerInfo.setSellerAddress(dto.getSellerAddress());

        SellerInfo savedInfo =
                sellerInfoRepository.save(sellerInfo);

        Seller seller = new Seller();
        seller.setSellerUsername(dto.getSellerUsername());
        seller.setSellerPassword(dto.getSellerPassword());
        seller.setSellerInfo(savedInfo);

        Seller savedSeller =
                sellerRepository.save(seller);

        SellerResponseDto response =
                new SellerResponseDto();

        response.setSellerId(savedSeller.getSellerId());
        response.setSellerUsername(
                savedSeller.getSellerUsername());

        response.setSellerName(
                savedSeller.getSellerInfo().getSellerName());

        response.setSellerAddress(
                savedSeller.getSellerInfo().getSellerAddress());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // LOGIN
    public ResponseEntity<?> login(LoginRequestDto dto) {

        Seller seller =
                sellerRepository
                        .findBySellerUsername(
                                dto.getUsername())
                        .orElseThrow(() ->
                                new SellerNotFoundException(
                                        "Invalid Username"));

        if (!seller.getSellerPassword()
                .equals(dto.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse(
                            "Invalid Password"));
        }

        String token =
                jwtUtil.generateToken(
                        seller.getSellerUsername());

        LoginResponseDto response =
                new LoginResponseDto();

        response.setSuccess(true);
        response.setMessage(
                "Login Successful");

        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}