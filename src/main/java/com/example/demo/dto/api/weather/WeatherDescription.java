package com.example.demo.dto.api.weather;


import com.example.demo.domain.weather.WeatherDescriptionDomain;
import org.springframework.stereotype.Component;

@Component
public class WeatherDescription {
    public String extract;
    public String title;
    public String description;

    public WeatherDescriptionDomain create(){
        return new WeatherDescriptionDomain(extract,title);
    }
}
