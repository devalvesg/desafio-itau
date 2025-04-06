package com.devalvesg.desafio_itau.domain.contract.repository;

import com.devalvesg.desafio_itau.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t FROM Transaction t WHERE t.OccuredAt >= :date", nativeQuery = true)
    List<Transaction> getTransactionDateEqualToOrGreaterThanToday(OffsetDateTime date);
}
