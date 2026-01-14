package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TransferRequest;
import com.example.demo.service.TransferService;
import com.example.demo.ledger.LedgerEntry;
import com.example.demo.ledger.LedgerRepository;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransferService transferService;
    private final LedgerRepository ledgerRepository;

    public TransactionController(
            TransferService transferService,
            LedgerRepository ledgerRepository) {
        this.transferService = transferService;
        this.ledgerRepository = ledgerRepository;
    }

    // 🔁 MONEY TRANSFER API
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestBody TransferRequest request) {

        // TEMP conversion: ACC1001 → 1001
        Long senderId = Long.parseLong(
                request.getFromAccount().replace("ACC", "")
        );

        Long receiverId = Long.parseLong(
                request.getToAccount().replace("ACC", "")
        );

        transferService.transfer(
                senderId,
                receiverId,
                request.getAmount()
        );

        return ResponseEntity.ok("Transaction successful");
    }

    // 📜 TRANSACTION HISTORY API
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LedgerEntry>> getTransactionHistory(
            @PathVariable Long userId) {

        List<LedgerEntry> history =
                ledgerRepository.findTransactionHistory(userId);

        return ResponseEntity.ok(history);
    }
}
