package com.example.demo.dto;

public class BalanceResponse {

    private Long userId;
    private Double balance;

    public BalanceResponse(Long userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }
}
