package com.example.demo.domain.weather;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@ComponentScan({"com.example.demo"})
public class WeatherService {
    public Weather weather;
    @Autowired
    WeatherService(Weather weather){
        this.weather = weather;
    }

    WeatherService(){}


    public LocalDate LocalDateConverter(String s){
        s=s.split(" ")[0];
        String []st =s.split("-");
        LocalDate start= LocalDate.of(Integer.parseInt(st[0]),Integer.parseInt(st[1]),Integer.parseInt(st[2]));
        return start;
    }
    public LocalDate LocalDateConverterPlusDays(String s){
        s=s.split(" ")[0];
        String []st =s.split("-");
        LocalDate end= LocalDate.of(Integer.parseInt(st[0]),Integer.parseInt(st[1]),Integer.parseInt(st[2])+5);
        return end;
    }

    public Weather  createWeather(Weather weather, WeatherDescriptionDomain weatherDescription, Double exchange_rate){


        Optional<Weather.WeatherDetails> first = weather.getWeather().stream().filter(p -> LocalDateConverter(p.dt_txt)
                .isBefore(LocalDateConverterPlusDays(p.dt_txt))).collect(Collectors.toList()).stream().findFirst();

        weather.setWeather( Collections.singletonList(first.get()));
        weather.setCity(weatherDescription.city);
        weather.setDescription(weatherDescription.description);
        weather.setExchange_rate(exchange_rate);
        return  weather;
    }
}
