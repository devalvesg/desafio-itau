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

    public TransactionService(ITransactionRepository repository){
        this.repository = repository;
    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Transaction entity = new Transaction(request);
        repository.save(entity);
        return new TransactionResponse(entity);
    }

    @Override
    public StatisticsResponse getStats() throws Exception {
        List<Transaction> transactions = repository.getTransactionDateEqualToOrGreaterThanToday(OffsetDateTime.now().minusMinutes(1));

        if(transactions.isEmpty()){
            throw new Exception("There were no transactions in the last minute");
        }

        double totalValue = transactions.stream().map(Transaction::getValue).reduce(0.0, Double::sum);
        return new StatisticsResponse(transactions.size(), totalValue, totalValue / transactions.size(), transactions.stream().min(Comparator.comparing(Transaction::getValue)).get().getValue(), transactions.stream().max(Comparator.comparing(Transaction::getValue)).get().getValue());
    }
}
