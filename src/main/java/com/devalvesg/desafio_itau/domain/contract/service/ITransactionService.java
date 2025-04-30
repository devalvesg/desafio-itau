package com.devalvesg.desafio_itau.domain.contract.service;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.domain.response.TransactionResponse;

import java.time.OffsetDateTime;

public interface ITransactionService {

    void createTransaction (TransactionRequest request);
    void deleteTransactions ();
    StatisticsResponse getStats(OffsetDateTime beginDate, OffsetDateTime endDate);
}
