package com.example.javaweatherinformationservice.controller;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void getCityWeather(@PathVariable String cityName) {
        // TODO logic to come
        System.out.println("City Weather information from API");
        // TODO uncomment below code when service has logic
//        return weatherService.getCityWeatherInformation(cityName);
    }

    public void getAllWeather(){
        System.out.println("All Weather information from API");
        // TODO uncomment below code when service has logic
//        return weatherService.getAllWeatherInformation();
    }


}
