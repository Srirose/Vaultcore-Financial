package com.example.demo.ledger;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String transactionId;
	    private String accountNumber;

	    @Enumerated(EnumType.STRING)
	    private EntryType entryType;

	    private Double amount;

	    @Column(name = "balance_after")
	    private Double balanceAfter;

	    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
    public String getAccountNumber() { 
        return accountNumber; 
    }
    public void setAccountNumber(String accountNumber) { 
        this.accountNumber = accountNumber; 
    }

    public String getTransactionId() { 
        return transactionId; 
    }

    public void setTransactionId(String transactionId) { 
        this.transactionId = transactionId; 
    }

    public EntryType getEntryType() { 
        return entryType; 
    }

    public void setEntryType(EntryType entryType) { 
        this.entryType = entryType; 
    }

    public Double getAmount() { 
        return amount; 
    }

    public void setAmount(Double amount) { 
        this.amount = amount; 
    }

    public Double getBalanceAfter() { 
        return balanceAfter; 
    }

    public void setBalanceAfter(Double balanceAfter) { 
        this.balanceAfter = balanceAfter; 
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
