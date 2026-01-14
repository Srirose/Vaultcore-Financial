package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BalanceResponse;
import com.example.demo.service.BalanceService;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BalanceResponse> getBalance(
            @PathVariable Long userId) {

        Double balance = balanceService.getBalance(userId);

        return ResponseEntity.<BalanceResponse>ok(
                new BalanceResponse(userId, balance)
        );

    }
}

