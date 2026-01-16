package com.example.demo.ledger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    // 🔹 Get last balance (latest ledger entry)
    @Query("""
        SELECT le.balanceAfter
        FROM LedgerEntry le
        WHERE le.userId = :userId
        ORDER BY le.createdAt DESC
        LIMIT 1
    """)
    Optional<Double> findLastBalance(@Param("userId") Long userId);

    // 🔹 Transaction history
    @Query("""
        SELECT le
        FROM LedgerEntry le
        WHERE le.userId = :userId
        ORDER BY le.createdAt DESC
    """)
    List<LedgerEntry> findTransactionHistory(@Param("userId") Long userId);
}
