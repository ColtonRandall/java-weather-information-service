package com.example.javaweatherinformationservice.service;

import com.example.javaweatherinformationservice.model.Weather;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// Gets data from in-memory storage, or fetches from API
@Service
public class WeatherService {

    /*
        - LinkedHashMap being used because of it's Least Recently Used (LRU) feature.
        - It can kick out the oldest entry if a new one is added, given we want the
        max number of cities to be stored in-memory to be 3.
        - I needed to add the following to make the list work:
            - 0.75f for load factor - i.e. when 3 items are added, increase in size.
            - `false` for accessOrder - Keeps items in the order they were added.
     */
    private Map<String, Weather> weatherMap = new LinkedHashMap<>(3, 0.75f, true);
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public WeatherService() {
        weatherMap.put("Auckland", new Weather("Auckland", getRandomTemperature(), 'C', date, "Cloudy"));
        weatherMap.put("Melbourne", new Weather("Melbourne", getRandomTemperature(), 'C', date, "Sunny"));
        weatherMap.put("Vancouver", new Weather("Vancouver", getRandomTemperature(), 'C', date, "Raining"));
    }


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

    // ----
    private int getRandomTemperature(){
        return ((int) (Math.random() * 30));
    }
}
