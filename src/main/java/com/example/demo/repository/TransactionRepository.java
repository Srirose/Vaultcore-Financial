package com.example.demo.repository;
//hello


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
