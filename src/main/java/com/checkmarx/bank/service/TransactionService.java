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

    @Transactional
    public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("Account not found");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType("TRANSFER");

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public List<Transaction> searchTransactions(String query) {
        return transactionRepository.findByCustomQuery(query);
    }
} 