package com.example.javaweatherinformationservice.service;

import org.springframework.stereotype.Service;

// Gets data from in-memory storage, or fetches from API
@Service
public class WeatherService {



    // base method for retrieving weather based on city name
    public void getCityWeatherInformation(String cityName) {
        // TODO logic to come
        System.out.println(cityName);
    }

    public void getAllWeatherInformation() {
        // TODO logic to come
        System.out.println("Weather information");
    }

    public void fetchWeatherFromMockApi(){
        // TODO logic to come
        System.out.println("Weather information from API");
    }
}
