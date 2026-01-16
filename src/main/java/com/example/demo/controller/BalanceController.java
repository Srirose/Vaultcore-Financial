package com.example.demo.controller;

import com.example.demo.dto.BalanceResponse;
import com.example.demo.ledger.LedgerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final LedgerRepository ledgerRepository;

    public BalanceController(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountNumber) {

        Double balance = ledgerRepository
                .findLastBalance(accountNumber)
                .orElse(0.0);

        return ResponseEntity.ok(new BalanceResponse(accountNumber, balance));
    }
}
