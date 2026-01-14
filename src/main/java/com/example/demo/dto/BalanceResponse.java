package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceResponse {
    private Long userId;
    private Double balance;
    public  BalanceResponse(Long userID,Double balance) {
    	this.userId = userId;
    	this.balance = balance;
    }
}