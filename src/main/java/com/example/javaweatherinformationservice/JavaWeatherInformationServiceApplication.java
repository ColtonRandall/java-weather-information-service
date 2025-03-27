package com.example.javaweatherinformationservice;

import com.example.javaweatherinformationservice.client.WeatherClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaWeatherInformationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWeatherInformationServiceApplication.class, args);

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.run();
    }

}
