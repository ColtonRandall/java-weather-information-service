package com.example.javaweatherinformationservice;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherData;
import com.example.javaweatherinformationservice.service.WeatherService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherControllerTest {


    WeatherData weatherData = new WeatherData();

    @Test
    public void testGetCityWeatherInformation() {
        // Arrange
        WeatherService weatherService = new WeatherService(weatherData);

        // Act
        Optional<Weather> weather = weatherService.getCityWeatherInformation("Auckland");

        // Assert
        assertTrue(weather.isPresent());
        assertEquals("Auckland", weather.get().getCityName());
    }

    @Test
    public void testWeatherMapMaxSize() {
        // Arrange
        WeatherService weatherService = new WeatherService(weatherData);

        // Assert
        assertEquals(3, weatherService.getWeatherMap().size()); // Add three default cities
        weatherService.fetchWeatherFromMockApi("Rome"); // Add a new city (should remove the eldest entry)
        assertEquals(3, weatherService.getWeatherMap().size()); // Ensure the weatherMap size is still 3
    }
}
