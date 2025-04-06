package com.devalvesg.desafio_itau.domain.response;

import com.devalvesg.desafio_itau.domain.entity.Transaction;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Setter
public class TransactionResponse {

    private Long id;
    private boolean deleted;
    private double value;
    private OffsetDateTime occurredAt;

    public TransactionResponse(Transaction entity){
        setId(entity.getId());
        setValue(entity.getValue());
        setDeleted(entity.isDeleted());
        setOccurredAt(entity.getOccurredAt());
    }
}
