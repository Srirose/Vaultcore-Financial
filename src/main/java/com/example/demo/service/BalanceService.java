package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.ledger.LedgerRepository;

@Service
public class BalanceService {

    private final LedgerRepository ledgerRepository;

    public BalanceService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public Double getBalance(Long userId) {

        return ledgerRepository
                .findLastBalance(userId)
                .orElse(0.0);
    }
}
