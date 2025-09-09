package com.example.exchange_rate.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRateResponseDTO {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;

    public ExchangeRateResponseDTO(String fromCurrency, String toCurrency, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }
}
