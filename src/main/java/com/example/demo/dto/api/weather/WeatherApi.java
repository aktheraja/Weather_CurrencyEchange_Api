package com.example.demo.dto.api.weather;

import com.example.demo.domain.weather.Weather;
import com.example.demo.domain.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@ComponentScan({"com.example.demo"})
public class WeatherApi {
    private final WeatherService weatherService;


    @Autowired
    public WeatherApi(WeatherService weatherService, WeatherGson weatherGson) {
        this.weatherService = weatherService;
    }

    public Weather createWeather(WeatherGson weatherGson, WeatherDescription weatherDescription,Double exchange_rate) {

        return weatherService.createWeather(weatherGson.create(), weatherDescription.create(),exchange_rate);
    }
}