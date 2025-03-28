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
    String border = "🌧️🌥️☀️🌧️🌥️☀️🌧️🌥️☀️🌧️🌥️☀️🌧️🌥️☀️🌧️🌥️☀️";
    boolean retrievingWeatherData = true;
    String json;

    public void Welcome() {
        System.out.println("\n" + border + "\n");
        System.out.println("Welcome to the Java Weather Application");
        System.out.println("\n" + border + "\n");
        System.out.println("What would you like to do?\n");
    }

    public void run() {
        Welcome();
        while (retrievingWeatherData) {
            System.out.println("1. Get Weather by City\n2. Get all weather data\n3. Exit");
            String userInput = scanner.nextLine();

            // Get specific city
            switch (userInput) {
                case "1" -> {
                    System.out.println("Enter City Name: ");
                    String city = scanner.nextLine();

                    while (city == null || city.isEmpty()) {
                        System.out.println("⚠️ Error - City name cannot be empty. Please enter a valid city name: ");
                        city = scanner.nextLine();
                    }

                /*
                    if the (optional) weather is present (i.e. Auckland),call the lambda expression
                    on the weather object 'w' and convert the data to JSON.
                 */
                    Optional<Weather> weather = weatherService.getCityWeatherInformation(city);
                    weather.ifPresent(w -> {
                        try {
                            // writerWithDefaultPrettyPrinter() will format it in JSON (easier to read).
                            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(w);
                            System.out.println(json + "\n"); // space for formatting

                        } catch (JsonProcessingException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                }

                // Get all weather in List
                case "2" -> {
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

                case "3" -> {
                    System.out.println("Exiting program... Goodbye! 👋");
                    retrievingWeatherData = false;
                    scanner.close();
                    System.exit(0);  // Exit the program w/ 'success' code
                }
            }
        }
    }
}
