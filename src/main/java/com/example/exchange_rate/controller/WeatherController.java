package com.example.exchange_rate.controller;

import com.example.exchange_rate.dto.WeatherResponseDTO;
import com.example.exchange_rate.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public WeatherResponseDTO getForecast(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeatherForecast(lat, lon);
    }
}
