package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TransferRequest;
import com.example.demo.service.TransferService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {

        // TEMP: convert account numbers to userIds
        // (Replace this later with AccountRepository lookup)
        Long senderId = Long.parseLong(request.getFromAccount().replace("ACC", ""));
        Long receiverId = Long.parseLong(request.getToAccount().replace("ACC", ""));

        transferService.transfer(
                senderId,
                receiverId,
                request.getAmount()
        );

        return ResponseEntity.ok("Transaction successful");
    }
}
