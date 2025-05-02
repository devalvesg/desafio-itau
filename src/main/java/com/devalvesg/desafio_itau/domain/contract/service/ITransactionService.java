package com.devalvesg.desafio_itau.domain.contract.service;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.infrastructure.api.exceptions.BusinessException;

import java.time.LocalDateTime;

public interface ITransactionService {

    void createTransaction (TransactionRequest request);
    void deleteTransactions ();
    StatisticsResponse getStats(LocalDateTime beginDate, LocalDateTime endDate) throws BusinessException;
}
