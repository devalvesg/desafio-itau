package com.devalvesg.desafio_itau.service;

import com.devalvesg.desafio_itau.domain.contract.repository.ITransactionRepository;
import com.devalvesg.desafio_itau.domain.contract.service.ITransactionService;
import com.devalvesg.desafio_itau.domain.entity.Transaction;
import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.domain.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTransaction(TransactionRequest request) {
        Transaction entity = new Transaction(request);
        repository.save(entity);
        //resolver b.o de não salvar a data proximo ao horario
    }

    @Override
    public void deleteTransactions() {
        repository.deleteAllTransactions();
    }

    @Override
    public StatisticsResponse getStats(OffsetDateTime beginDate, OffsetDateTime endDate) {
        List<Transaction> transactions = repository.getTransactionDateEqualToOrGreaterThanToday(beginDate, endDate);

        if (transactions.isEmpty()) {
            return new StatisticsResponse(0, 0.0, 0.0, 0.0, 0.0);
        }

        double totalValue = transactions.stream().map(Transaction::getAmount).reduce(0.0, Double::sum);
        return new StatisticsResponse(transactions.size(), totalValue, totalValue / transactions.size(), transactions.stream().min(Comparator.comparing(Transaction::getAmount)).get().getAmount(), transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get().getAmount());
    }
}
