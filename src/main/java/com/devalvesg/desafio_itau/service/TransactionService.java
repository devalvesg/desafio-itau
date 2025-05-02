package com.devalvesg.desafio_itau.service;

import com.devalvesg.desafio_itau.domain.contract.repository.ITransactionRepository;
import com.devalvesg.desafio_itau.domain.contract.service.ITransactionService;
import com.devalvesg.desafio_itau.domain.entity.Transaction;
import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.infrastructure.api.exceptions.BusinessException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(cacheNames = "statistics", allEntries = true)
    @Override
    public void createTransaction(TransactionRequest request) {
        Transaction entity = new Transaction(request);
        repository.save(entity);
    }

    @CacheEvict(cacheNames = "statistics", allEntries = true)
    @Override
    public void deleteTransactions() {
        repository.deleteAllTransactions();
    }

    @Cacheable(
            value = "statistics",
            cacheNames = "statistics",
            key = "#root.method.name"
    )
    @Override
    public StatisticsResponse getStats(LocalDateTime beginDate, LocalDateTime endDate) throws BusinessException {
        List<Transaction> transactions = repository.getTransactionDateEqualToOrGreaterThanToday(beginDate, endDate);

        if(beginDate.isAfter(endDate)) {
            throw new BusinessException("Begin date cannot be greater than end date");
        }

        if (transactions.isEmpty()) {
            return new StatisticsResponse(0, 0.0, 0.0, 0.0, 0.0);
        }

        double totalValue = transactions.stream().map(Transaction::getAmount).reduce(0.0, Double::sum);
        return new StatisticsResponse(transactions.size(), totalValue, totalValue / transactions.size(), transactions.stream().min(Comparator.comparing(Transaction::getAmount)).get().getAmount(), transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get().getAmount());
    }
}
