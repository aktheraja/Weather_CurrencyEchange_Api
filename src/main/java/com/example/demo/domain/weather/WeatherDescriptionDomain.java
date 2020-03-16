package com.example.demo.domain.weather;


import org.springframework.stereotype.Component;

@Component
public class WeatherDescriptionDomain {
    public String city;
    public String description;
    WeatherDescriptionDomain(){}
    public WeatherDescriptionDomain(String description, String city) {
        this.description = description;
        this.city = city;
    }
}
