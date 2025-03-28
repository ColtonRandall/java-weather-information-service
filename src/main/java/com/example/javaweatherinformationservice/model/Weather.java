package com.example.javaweatherinformationservice.model;

public class Weather {

    private String city;
    private int temperature;
    private char unit;
    private String date;
    private String weatherConditions;

    public Weather(String city, int temperature, char unit, String date, String weatherConditions) {
        this.city = city;
        this.temperature = temperature;
        this.unit = unit;
        this.date = date;
        this.weatherConditions = weatherConditions;
    }

    // Get methods
    public String getCity() {
        return city;
    }

    public int getTemperature() {
        return temperature;
    }

    public char getUnit() {
        return unit;
    }

    public String getDate() {
        return date;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    // Set methods
    public void setCityName(String cityName) {
        this.city = cityName;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setUnit(char unit) {
        this.unit = unit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
    }
}
