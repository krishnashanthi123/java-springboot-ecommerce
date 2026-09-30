package com.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dtoRequest.LoginRequestDto;
import com.ecommerce.dtoRequest.SellerRequestDto;
import com.ecommerce.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerSeller(
            @RequestBody SellerRequestDto dto) {

        return sellerService.registerSeller(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto dto) {

        return sellerService.login(dto);
    }
}