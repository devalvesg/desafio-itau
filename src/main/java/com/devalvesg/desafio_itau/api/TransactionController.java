package com.devalvesg.desafio_itau.api;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import com.devalvesg.desafio_itau.domain.response.StatisticsResponse;
import com.devalvesg.desafio_itau.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping()
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionRequest request) {
        transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<StatisticsResponse> getStatistics(@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime beginDate, @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDate) {
        StatisticsResponse response = transactionService.getStats(beginDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
