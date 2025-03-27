package com.example.javaweatherinformationservice.client;

import com.example.javaweatherinformationservice.model.Weather;
import com.example.javaweatherinformationservice.service.WeatherData;
import com.example.javaweatherinformationservice.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/*
    Mock the user to print out to the console
 */
public class WeatherClient {

    WeatherData weatherData = new WeatherData();
    WeatherService weatherService = new WeatherService(weatherData); // Should only create this once for reuse.
    ObjectMapper objectMapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);
    String json;

    public void run() throws Exception {
        System.out.println("Enter city name: \n or press 'a' to view all cities");
        String response = scanner.nextLine();

        if (response.equals("a")) {
            Map<String, Weather> weather = weatherService.getAllWeatherInformation();
            weather.forEach((city, weatherData) -> {
                try{
                    // the following JSON methods format the output in easy-to-read JSON output
                    json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(weatherData);
                    System.out.println(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        }

        Optional<Weather> weather = weatherService.getCityWeatherInformation(response);
        try{
            if(weather.isPresent()){
                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(weather.get());
                System.out.println(json);
            } else {
                System.out.println(response + " not found");
            }
        } catch (JsonProcessingException e) {
            throw new Exception("Json processing error for " + response);
        }
    }
}
