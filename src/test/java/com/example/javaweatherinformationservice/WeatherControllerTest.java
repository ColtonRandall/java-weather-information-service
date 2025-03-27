package com.example.javaweatherinformationservice;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherData;
import com.example.javaweatherinformationservice.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherControllerTest {

    WeatherData weatherData = new WeatherData();

    @Test
    public void testGetCityWeatherInformation() {
        WeatherService weatherService = new WeatherService(weatherData);
        Optional<Weather> weather = weatherService.getCityWeatherInformation("Auckland");

        assertTrue(weather.isPresent());
        assertEquals("Auckland", weather.get().getCityName());
    }
}
