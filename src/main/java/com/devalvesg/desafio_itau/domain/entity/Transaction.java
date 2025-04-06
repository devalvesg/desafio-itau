package com.devalvesg.desafio_itau.domain.entity;

import com.devalvesg.desafio_itau.domain.request.TransactionRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity(name = "transactions")
@Data
public class Transaction implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted = false;
    private double value;
    private OffsetDateTime occurredAt;

    public Transaction(TransactionRequest request) {
        setValue(request.getValue());
        setOccurredAt(request.getOccurredAt());
    }
}
