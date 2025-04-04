package com.example.javaweatherinformationservice.service;

import com.example.javaweatherinformationservice.model.Weather;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    - Needs the @Component annotation so Spring can automatically
    create and manage instances of WeatherData.
    - Also, other classes (i.e. WeatherService), don't have to manually create it.
 */
@Component
public class WeatherData {

    String date = new SimpleDateFormat("dd-MM-yyy").format(new Date());

    public List<Weather> generateWeatherInformation() {
        List<Weather> weatherList = new ArrayList<>();

        // Max 3 saved in memory
        weatherList.add(new Weather("Auckland", getRandomTemperature(), 'C', date, "Cloudy"));
        weatherList.add(new Weather("Melbourne", getRandomTemperature(), 'C', date, "Sunny"));
        weatherList.add(new Weather("Vancouver", getRandomTemperature(), 'C', date, "Raining"));

        return weatherList;
    }

    private int getRandomTemperature() {
        return ((int) (Math.random() * 30));
    }
}
