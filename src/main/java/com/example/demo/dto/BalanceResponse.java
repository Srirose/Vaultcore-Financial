package com.example.demo.dto;
//hello
public class BalanceResponse {

    private String accountNumber;
    private Double balance;

    public BalanceResponse(String accountNumber, Double balance) {
        this.accountNumber= accountNumber;
        this.balance = balance;
    }

    public String getaccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }
}
