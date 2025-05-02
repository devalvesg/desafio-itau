package com.devalvesg.desafio_itau.domain.entity;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Getter
@Setter
public class Transaction implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean deleted = false;
    private double amount;
    private LocalDateTime occurredAt;

    public Transaction(TransactionRequest request) {
        setAmount(request.getAmount());
        setOccurredAt(request.getOccurredAt());
    }

    public Transaction() {

    }
}
