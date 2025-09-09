package com.example.exchange_rate.dto;

import com.example.exchange_rate.model.WeatherData;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponseDTO {

    private String country_code;
    private String timezone;
    private String city_name;

    private List<WeatherData> data;
}
