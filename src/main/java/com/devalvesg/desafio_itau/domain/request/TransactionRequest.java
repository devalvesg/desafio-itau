package com.devalvesg.desafio_itau.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class TransactionRequest {

    @NotNull(message = "Transaction value is required") @Min(value = 0, message = "Transaction value must be equal to or greater than 0")
    private double value;
    @NotNull(message = "Transaction date is required") @Past(message = "Transaction date must be equal to or greater than today")
    private OffsetDateTime occurredAt;
}
