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
        while (true) {
            System.out.println("1. Get Weather by City\n2. Get all weather data\n3. Exit");
            String userInput = scanner.nextLine();

            // Get specific city
            if (userInput.equals("1")) {
                System.out.println("Enter City Name: ");
                String city = scanner.nextLine();
                Optional<Weather> weather = weatherService.getCityWeatherInformation(city);

                /*
                    if the (optional) weather is present (i.e. Auckland),call the lambda expression
                    on the weather object 'w' and convert the data to JSON.
                 */
                weather.ifPresent(w -> {
                    try {
                        // writerWithDefaultPrettyPrinter() will format it in JSON (easier to read).
                        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(w);
                        System.out.println(json + "\n"); // space for formatting

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            }

            // Get all weather in List
            if (userInput.equals("2")) {
                Map<String, Weather> weather = weatherService.getAllWeatherInformation();

                weather.forEach((city, weatherData) -> {
                            try {
                                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(weatherData);
                                System.out.println(json + "\n"); // space at the end for formatting
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }

            if (userInput.equals("3")) {
                System.out.println("Exiting program... Goodbye! 👋");
                scanner.close();
                System.exit(0);  // Exit the program w/ 'success' code
            }
        }
    }
}
