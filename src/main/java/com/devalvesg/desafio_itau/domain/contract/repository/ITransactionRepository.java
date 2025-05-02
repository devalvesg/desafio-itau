package com.devalvesg.desafio_itau.domain.contract.repository;

import com.devalvesg.desafio_itau.domain.entity.Transaction;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t FROM transactions t WHERE (t.occurredAt >= :beginDate AND t.occurredAt <= :endDate) AND t.deleted = false")
    List<Transaction> getTransactionDateEqualToOrGreaterThanToday(@Param("beginDate") LocalDateTime beginDate, @Param("endDate") LocalDateTime endDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE transactions SET deleted = true")
    void deleteAllTransactions();
}
