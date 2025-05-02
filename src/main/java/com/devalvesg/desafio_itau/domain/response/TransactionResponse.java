package com.devalvesg.desafio_itau.domain.response;

import com.devalvesg.desafio_itau.domain.entity.Transaction;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
public class TransactionResponse {

    private Long id;
    private boolean deleted;
    private double value;
    private LocalDateTime occurredAt;

    public TransactionResponse(Transaction entity){
        setId(entity.getId());
        setValue(entity.getAmount());
        setDeleted(entity.isDeleted());
        setOccurredAt(entity.getOccurredAt());
    }
}
