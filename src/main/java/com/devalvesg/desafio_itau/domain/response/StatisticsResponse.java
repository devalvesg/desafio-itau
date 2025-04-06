package com.devalvesg.desafio_itau.domain.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {

    private int count;
    private double sum;
    private double average;
    private double minValue;
    private double maxValue;
}
