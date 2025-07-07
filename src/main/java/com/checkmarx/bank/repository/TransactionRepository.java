package com.checkmarx.bank.repository;

import com.checkmarx.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);

    @Query(value = "SELECT * FROM transactions WHERE " +
            "description LIKE %:query% OR " +
            "type LIKE %:query% OR " +
            "status LIKE %:query%", nativeQuery = true)
    List<Transaction> findByCustomQuery(@Param("query") String query);

    @Query(value = "SELECT * FROM transactions WHERE timestamp > :date", nativeQuery = true)
    List<Transaction> findByDateAfter(@Param("date") String date);

    @Query(value = "SELECT * FROM transactions WHERE amount > :amount", nativeQuery = true)
    List<Transaction> findByAmountGreaterThan(@Param("amount") Double amount);

    @Query(value = "SELECT * FROM transactions ORDER BY timestamp DESC", nativeQuery = true)
    List<Transaction> findAllOrderByTimestamp();
} 