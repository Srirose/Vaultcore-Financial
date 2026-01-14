package com.example.demo.ledger;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

	 @Query("""
		        SELECT le.balanceAfter
		        FROM LedgerEntry le
		        WHERE le.userId = :userId
		        ORDER BY le.createdAt DESC
		        LIMIT 1
		    """)
		    Optional<Double> findLastBalance(Long userId);

		    // ✅ Transaction history
		    @Query("""
		        SELECT le
		        FROM LedgerEntry le
		        WHERE le.userId = :userId
		        ORDER BY le.createdAt DESC
		    """)
		    List<LedgerEntry> findTransactionHistory(Long userId);
}
