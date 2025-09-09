package com.example.exchange_rate;

import com.example.exchange_rate.controller.WeatherController;
import com.example.exchange_rate.dto.WeatherResponseDTO;
import com.example.exchange_rate.model.WeatherData;
import com.example.exchange_rate.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void testGetForecast() throws Exception {

        WeatherResponseDTO mockResponse = new WeatherResponseDTO();
        WeatherData data = new WeatherData();
        data.setTemp(88.5);
        data.setWind_cdir("NE");

        mockResponse.setData(Collections.singletonList(data));
        mockResponse.setCity_name("Four Oaks");
        mockResponse.setCountry_code("US");
        mockResponse.setTimezone("America/New_York");

        when(weatherService.getWeatherForecast(35.5, -78.5)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/weather/forecast")
                        .param("lat", "35.5")
                        .param("lon", "-78.5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].temp").value(88.5))
                .andExpect(jsonPath("$.data[0].wind_cdir").value("NE"))
                .andExpect(jsonPath("$.city_name").value("Four Oaks"))
                .andExpect(jsonPath("$.country_code").value("US"))
                .andExpect(jsonPath("$.timezone").value("America/New_York"));
    }
}
