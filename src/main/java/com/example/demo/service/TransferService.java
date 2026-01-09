package com.example.demo.service;

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
    public void transfer(Long senderId, Long receiverId, Double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        String transactionId = UUID.randomUUID().toString();

        // 🔐 Sender balance
        double senderBalance = ledgerRepository
                .findLastBalance(senderId)
                .orElse(0.0);

        if (senderBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // ➖ DEBIT
        LedgerEntry debit = new LedgerEntry();
        debit.setTransactionId(transactionId);
        debit.setUserId(senderId);
        debit.setEntryType(EntryType.DEBIT);
        debit.setAmount(amount);
        debit.setBalanceAfter(senderBalance - amount);

        ledgerRepository.save(debit);

        // ➕ CREDIT
        double receiverBalance = ledgerRepository
                .findLastBalance(receiverId)
                .orElse(0.0);

        LedgerEntry credit = new LedgerEntry();
        credit.setTransactionId(transactionId);
        credit.setUserId(receiverId);
        credit.setEntryType(EntryType.CREDIT);
        credit.setAmount(amount);
        credit.setBalanceAfter(receiverBalance + amount);

        ledgerRepository.save(credit);
    }
}
