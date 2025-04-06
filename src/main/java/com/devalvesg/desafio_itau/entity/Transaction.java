package com.devalvesg.desafio_itau.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity(name = "transactions")
@Data
public class Transaction implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double value;
    private OffsetDateTime occurredAt;
}
