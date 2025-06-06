package com.checkmarx.bank.repository;

import com.checkmarx.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Intentionally vulnerable - no proper indexing
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);

    // Intentionally vulnerable - SQL injection risk
    @Query(value = "SELECT * FROM transactions WHERE " +
            "description LIKE %:query% OR " +
            "type LIKE %:query% OR " +
            "status LIKE %:query%", nativeQuery = true)
    List<Transaction> findByCustomQuery(@Param("query") String query);

    // Intentionally vulnerable - no proper date validation
    @Query(value = "SELECT * FROM transactions WHERE timestamp > :date", nativeQuery = true)
    List<Transaction> findByDateAfter(@Param("date") String date);

    // Intentionally vulnerable - no proper amount validation
    @Query(value = "SELECT * FROM transactions WHERE amount > :amount", nativeQuery = true)
    List<Transaction> findByAmountGreaterThan(@Param("amount") Double amount);

    // Intentionally vulnerable - no proper pagination
    @Query(value = "SELECT * FROM transactions ORDER BY timestamp DESC", nativeQuery = true)
    List<Transaction> findAllOrderByTimestamp();
} 