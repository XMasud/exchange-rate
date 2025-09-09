package com.example.exchange_rate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherData {
    private double temp;
    private String wind_cdir;
}
