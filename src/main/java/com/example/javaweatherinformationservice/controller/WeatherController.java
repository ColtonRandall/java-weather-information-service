package com.example.javaweatherinformationservice.controller;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

// Using Spring Boots built in support for REST APIs
// Expose data via API endpoints
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Simple GET methods for city & all weather
    // @PathVariable needs to be in here for the URL suffix
    @GetMapping("/{city}")
    public Optional<Weather> getCityWeather(@PathVariable String city) {
        return weatherService.getCityWeatherInformation(city);
    }

    @GetMapping("/all")
    public Map<String, Weather> getAllWeather(){
        return weatherService.getAllWeatherInformation();
    }


}
