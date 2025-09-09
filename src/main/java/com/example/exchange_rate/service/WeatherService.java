package com.example.exchange_rate.service;

import com.example.exchange_rate.dto.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.host}")
    private String apiHost;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponseDTO getWeatherForecast(double lat, double lon){
        String url = String.format("%s?lat=%.1f&lon=%.1f&units=imperial&lang=en", apiUrl, lat, lon);

        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key",apiKey);
        headers.set("x-rapidapi-host",apiHost);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<WeatherResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherResponseDTO.class);

        return response.getBody();
    }
}
