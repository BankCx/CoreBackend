package com.checkmarx.bank.service;

import com.checkmarx.bank.model.Account;
import com.checkmarx.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> searchAccounts(String query) {
        return accountRepository.findByCustomQuery(query);
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void updateAccount(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id).orElseThrow();
        existingAccount.setBalance(account.getBalance());
        existingAccount.setAccountType(account.getAccountType());
        accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
} 