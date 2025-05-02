package com.devalvesg.desafio_itau.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionRequest {

    @NotNull(message = "Transaction value is required") @Min(value = 0, message = "Transaction value must be equal to or greater than 0") @JsonProperty("valor")
    private double amount;
    @NotNull(message = "Transaction date is required") @FutureOrPresent(message = "Transaction date must be equal to or greater than today") @JsonProperty("dataHora")
    private LocalDateTime occurredAt;
}
