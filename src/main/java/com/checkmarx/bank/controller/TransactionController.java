package com.checkmarx.bank.controller;

import com.checkmarx.bank.model.Transaction;
import com.checkmarx.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.transfer(fromAccountId, toAccountId, amount));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAccountTransactions(accountId));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> searchTransactions(@RequestParam Map<String, String> params) {
        String query = params.getOrDefault("query", "");
        return ResponseEntity.ok(transactionService.searchTransactions(query));
    }
} 