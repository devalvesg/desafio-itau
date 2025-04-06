package com.devalvesg.desafio_itau.domain.contract.service;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.domain.response.TransactionResponse;

public interface ITransactionService {

    TransactionResponse createTransaction (TransactionRequest request);
    StatisticsResponse getStats() throws Exception;
}
