package com.example.exchange_rate.service;

import com.example.exchange_rate.dto.ExchangeRateRequestDTO;
import com.example.exchange_rate.dto.ExchangeRateResponseDTO;
import com.example.exchange_rate.model.ExchangeRate;
import com.example.exchange_rate.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    public ExchangeRateResponseDTO save(ExchangeRateRequestDTO exchangeRateRequestDTO) {

        ExchangeRate rate = new ExchangeRate();

        rate.setFromCurrency(exchangeRateRequestDTO.getFromCurrency());
        rate.setToCurrency(exchangeRateRequestDTO.getToCurrency());
        rate.setRate(exchangeRateRequestDTO.getRate());

        ExchangeRate save = exchangeRateRepository.save(rate);

        return new ExchangeRateResponseDTO(
                save.getFromCurrency(),
                save.getToCurrency(),
                save.getRate()
        );
    }

    public ExchangeRateResponseDTO getRate(Long id) {

        ExchangeRate rate = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return new ExchangeRateResponseDTO(
                rate.getFromCurrency(),
                rate.getToCurrency(),
                rate.getRate()
        );
    }

    public void delete(Long id) {
        exchangeRateRepository.deleteById(id);
    }

    public ExchangeRateResponseDTO findCurrencyPair(String from, String to) {

        ExchangeRate rate = exchangeRateRepository.findByFromCurrencyAndToCurrency(from, to)
                .orElseThrow(() -> new RuntimeException("Rate not found"));

        return new ExchangeRateResponseDTO(
                rate.getFromCurrency(),
                rate.getToCurrency(),
                rate.getRate()
        );
    }
}
