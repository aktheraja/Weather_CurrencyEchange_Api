package com.example.demo.dto.api.weather;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.domain.weather.Weather;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com.example.demo"})
public class WeatherGson {
    private  List<WeatherDetails> list;


    public WeatherGson(){}
    public WeatherGson(List<WeatherDetails>  list){
        this.list=list;
    }
   public Weather create(){
        return new Weather(list.stream().map(q->q.create()).collect(Collectors.toList()));
   }
   class WeatherDetails{

       private  String dt;
       private  Map<String, Object> main;
       private List<Map<String, Object>> weather;
       private  Map<String, Object> clouds;
       private  Map<String, Object> wind;
       private  Map<String, Object> sys;
       private  String dt_txt;
       public Weather.WeatherDetails create(){
           return new Weather.WeatherDetails( dt,main,weather,clouds,wind,sys,dt_txt);
       }
    }
}

