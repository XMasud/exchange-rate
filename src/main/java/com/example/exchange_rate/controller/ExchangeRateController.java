package com.example.exchange_rate.controller;

import com.example.exchange_rate.dto.ExchangeRateRequestDTO;
import com.example.exchange_rate.dto.ExchangeRateResponseDTO;
import com.example.exchange_rate.model.ExchangeRate;
import com.example.exchange_rate.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
@Tag(name = "Exchange Rate API")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getAll(){
        return ResponseEntity.ok(exchangeRateService.findAll());
    }

    @PostMapping
    public ResponseEntity<ExchangeRateResponseDTO> save(@RequestBody ExchangeRateRequestDTO exchangeRateRequestDTO){
        return ResponseEntity.ok(exchangeRateService.save(exchangeRateRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRateResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(exchangeRateService.getRate(id));
    }

    @GetMapping("/{from}/{to}")
    public ResponseEntity<ExchangeRateResponseDTO> getByPair(@PathVariable String from, @PathVariable String to){
        return ResponseEntity.ok(exchangeRateService.findCurrencyPair(from, to));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exchangeRateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
