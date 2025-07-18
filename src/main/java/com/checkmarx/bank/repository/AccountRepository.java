package com.checkmarx.bank.repository;

import com.checkmarx.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Query(value = "SELECT * FROM accounts WHERE " +
            "account_number LIKE %:query% OR " +
            "customer_id LIKE %:query% OR " +
            "account_type LIKE %:query%", nativeQuery = true)
    List<Account> findByCustomQuery(@Param("query") String query);

    List<Account> findByCustomerId(String customerId);

    Account findByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM accounts WHERE balance > :amount", nativeQuery = true)
    List<Account> findByBalanceGreaterThan(@Param("amount") Double amount);

    @Query(value = "SELECT * FROM accounts ORDER BY created_at DESC", nativeQuery = true)
    List<Account> findAllOrderByCreatedAt();
} 