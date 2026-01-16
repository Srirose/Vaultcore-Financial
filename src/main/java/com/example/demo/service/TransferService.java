package com.example.demo.service;
//hello
import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ledger.EntryType;
import com.example.demo.ledger.LedgerEntry;
import com.example.demo.ledger.LedgerRepository;

@Service
public class TransferService {

    private final LedgerRepository ledgerRepository;

    public TransferService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transfer(String fromAcc, String toAcc, Double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        String txId = UUID.randomUUID().toString();

        double senderBalance = ledgerRepository
                .findLastBalance(fromAcc)
                .orElse(0.0);

        if (senderBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // DEBIT
        LedgerEntry debit = new LedgerEntry();
        debit.setTransactionId(txId);
        debit.setAccountNumber(fromAcc);
        debit.setEntryType(EntryType.DEBIT);
        debit.setAmount(amount);
        debit.setBalanceAfter(senderBalance - amount);
        ledgerRepository.save(debit);

        // CREDIT
        double receiverBalance = ledgerRepository
                .findLastBalance(toAcc)
                .orElse(0.0);

        LedgerEntry credit = new LedgerEntry();
        credit.setTransactionId(txId);
        credit.setAccountNumber(toAcc);
        credit.setEntryType(EntryType.CREDIT);
        credit.setAmount(amount);
        credit.setBalanceAfter(receiverBalance + amount);
        ledgerRepository.save(credit);
    }

}
