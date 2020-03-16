package com.example.demo.domain.weather;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
@Component
@ComponentScan({"com.example.demo"})
public  class Weather {
    public String city;
    public String description;
    public Double exchange_rate;
    public  List<WeatherDetails> weather;

    public Weather(){}

    public void setExchange_rate(Double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Weather(List<WeatherDetails> weather) {
        this.weather = weather;
    }

    public void setWeather(List<WeatherDetails> weather) {
        this.weather = weather;
    }

    public List<WeatherDetails> getWeather() {
        return weather;
    }
    public static class WeatherDetails{

        public  String dt;
        public  Map<String, Object> main;
        public  List<Map<String, Object>> weather;
        public  Map<String, Object> clouds;
        public   Map<String, Object> wind;
        public   Map<String, Object> sys;
        public   String dt_txt;

        public WeatherDetails(String dt, Map<String, Object> main, List<Map<String, Object>> weather, Map<String, Object> clouds, Map<String, Object> wind, Map<String, Object> sys, String dt_txt) {
            this.dt = dt;
            this.main = main;
            this.weather = weather;
            this.clouds = clouds;
            this.wind = wind;
            this.sys = sys;
            this.dt_txt = dt_txt;
        }
    }
}
