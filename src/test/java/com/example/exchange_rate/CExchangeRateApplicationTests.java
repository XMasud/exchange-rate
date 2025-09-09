package com.example.exchange_rate;

import com.example.exchange_rate.dto.ExchangeRateRequestDTO;
import com.example.exchange_rate.dto.ExchangeRateResponseDTO;
import com.example.exchange_rate.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CExchangeRateApplicationTests {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    void testCreateAndFind() {

        ExchangeRateRequestDTO exchangeRateRequestDTO = new ExchangeRateRequestDTO("GBP", "BD", BigDecimal.valueOf(105.20));

        ExchangeRateResponseDTO save = exchangeRateService.save(exchangeRateRequestDTO);
        assertNotNull(save.getRate());

        ExchangeRateResponseDTO rateResponseDTO = exchangeRateService.findCurrencyPair("GBP", "BD");

        assertTrue(save.getRate().compareTo(rateResponseDTO.getRate()) == 0);
    }

}
