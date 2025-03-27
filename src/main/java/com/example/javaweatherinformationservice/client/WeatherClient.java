package com.example.javaweatherinformationservice.client;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherData;
import com.example.javaweatherinformationservice.service.WeatherService;

import java.util.Optional;
import java.util.Scanner;

/*
    Mock the user to print out to the console
 */
public class WeatherClient {

    WeatherData weatherData = new WeatherData();
    WeatherService weatherService = new WeatherService(weatherData); // Should only create this once for reuse.

    public void run(){
        System.out.println("Enter City Name: ");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        Optional<Weather> weather = weatherService.getCityWeatherInformation(city);
        System.out.println(weather); // have to convert this output to JSON
    }
}
