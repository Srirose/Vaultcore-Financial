package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ledger.LedgerRepository;

@Service
public class BalanceService {

    private final LedgerRepository ledgerRepository;

    public BalanceService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Transactional(readOnly = true)
    public Double getBalance(Long userId) {
        return ledgerRepository
                .findLastBalance(userId)
                .orElse(0.0); // New users start at zero
    }
}