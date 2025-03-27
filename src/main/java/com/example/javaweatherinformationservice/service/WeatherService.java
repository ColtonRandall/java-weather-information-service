package com.example.javaweatherinformationservice.service;

import com.example.javaweatherinformationservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

// Gets data from in-memory storage, or fetches from API
@Service
public class WeatherService {

    private final int MAX_SIZE = 3;
    String date = new SimpleDateFormat("dd-MM-yyy").format(new Date());

    /*
        - LinkedHashMap being used because of it's Least Recently Used (LRU) feature.
        - It can kick out the oldest entry if a new one is added, given we want the
        max number of cities to be stored in-memory to be 3.
        - I needed to add the following to make the list work:
            - 0.75f for load factor - i.e. when 3 items are added, increase in size.
            - `false` for accessOrder - Keeps items in the order they were added.
     */
    private Map<String, Weather> weatherMap = new LinkedHashMap<>(3, 0.75f, true){

        // Override the default LinkedHashMap method to keep the size at 3
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Weather> eldestCity){
            return size() > MAX_SIZE;
        }
    };


    @Autowired
    public WeatherService(WeatherData weatherData) {
        /*
            - Create the initial ArrayList with default 3 cities from WeatherData (weatherList).
            - Add them to the weatherMap.
         */
        for(Weather weather : weatherData.generateWeatherInformation()){
            weatherMap.put(weather.getCityName(), weather);
        }
    }


    /*
        - Base method for retrieving weather based on city name.
        - Using Optional here to avoid null exceptions if city doesn't exist.
     */
    public Optional<Weather> getCityWeatherInformation(String cityName) {
        for (Weather weather : weatherMap.values()) {
            if(Objects.equals(cityName, weather.getCityName())){
                // If city exists, grab it
                return Optional.of(weather);
            }
        }
        Weather fetchNewWeather = fetchWeatherFromMockApi(cityName);
        return Optional.of(fetchNewWeather);
    }

    public Map<String, Weather> getAllWeatherInformation() {
        return weatherMap;
    }

    public Weather fetchWeatherFromMockApi(String cityName) {
        System.out.println("Fetching weather information for city: " + cityName + "\n");
        Weather fetchedNewWeather = new Weather(cityName, getRandomTemperature(), 'C', date, "Unknown weather " +
                "conditions");
        weatherMap.put(fetchedNewWeather.getCityName(), fetchedNewWeather);
        return fetchedNewWeather;
    };

    // ----
    private int getRandomTemperature() {
        return ((int) (Math.random() * 30));
    }

}
