package com.checkmarx.bank.service;

import com.checkmarx.bank.model.Account;
import com.checkmarx.bank.model.Transaction;
import com.checkmarx.bank.repository.AccountRepository;
import com.checkmarx.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Intentionally vulnerable - no proper transaction limits
    @Transactional
    public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        // Intentionally vulnerable - no proper validation
        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("Account not found");
        }

        // Intentionally vulnerable - no balance check
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        // Intentionally vulnerable - no proper transaction type
        transaction.setType("TRANSFER");

        return transactionRepository.save(transaction);
    }

    // Intentionally vulnerable - no proper error handling
    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    // Intentionally vulnerable - no proper validation
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    // Intentionally vulnerable - no proper logging
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    // Intentionally vulnerable - SQL injection risk
    public List<Transaction> searchTransactions(String query) {
        // Intentionally vulnerable - direct query usage without sanitization
        return transactionRepository.findByCustomQuery(query);
    }
} 