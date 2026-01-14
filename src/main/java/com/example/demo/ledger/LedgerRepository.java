package com.example.demo.ledger;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    // 🔐 Get last balance
    @Query("""
        SELECT l.balanceAfter 
        FROM LedgerEntry l
        WHERE l.userId = :userId
        ORDER BY l.createdAt DESC
        LIMIT 1
    """)
    Optional<Double> findLastBalance(@Param("userId") Long userId);

    // 📜 Ledger history
    List<LedgerEntry> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 🔍 Audit by transaction
    List<LedgerEntry> findByTransactionId(String transactionId);
}
