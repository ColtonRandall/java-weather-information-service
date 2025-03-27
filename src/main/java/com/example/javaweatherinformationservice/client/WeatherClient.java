package com.example.javaweatherinformationservice.client;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherData;
import com.example.javaweatherinformationservice.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.Scanner;

/*
    Mock the user to print out to the console
 */
public class WeatherClient {

    WeatherData weatherData = new WeatherData();
    WeatherService weatherService = new WeatherService(weatherData); // Should only create this once for reuse.
    ObjectMapper objectMapper = new ObjectMapper();

    public void run() throws Exception {
        System.out.println("Enter City Name: ");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        Optional<Weather> weather = weatherService.getCityWeatherInformation(city);
        try{
            if(weather.isPresent()){
                String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(weather.get());
                System.out.println(json);
            } else {
                System.out.println(city + " not found");
            }
        } catch (JsonProcessingException e) {
            throw new Exception("Json processing error for " + city);
        }
    }
}
