package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherEntry implements Serializable {

	private Instant timestamp;

	private double temperature;
	private double feel_like;
	private double Temp_min;
	private double Temp_max;
	private double Pressure;
	private double Humidity;


	private Integer weatherId;

	private String weatherIcon;
	Map<String ,Double> map = new HashMap<>();
	List<Map<String,Double>> listMap = new ArrayList<>();

	/**
	 * Return the temperature in Kelvin (K).
	 */
	public void setmain(Map<String ,Double> map1) {
		this.map = map1;
	}

	public void setTemperature(double temperature) {
		map.put("temp",temperature);
		this.temperature = temperature;
	}

	public void setFeel_like(double feel_like) {

		this.feel_like = feel_like;
	}
	public void setTemp_max(double Temp_max) {
		map.put("temp_max",Temp_max);
		this.Temp_max = Temp_max;
	}
	public void setTemp_min(double Temp_min) {
		map.put("temp_min",Temp_min);
		this.Temp_min = Temp_min;
	}
	public void setPressure(double Pressure) {
		map.put("pressure",Pressure);
		this.Pressure = Pressure;
	}
	public void setHumidity(double Humidity) {

		map.put("Humidity",Humidity);
		this.Humidity = Humidity;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}


	public double getTemperature() {
		return this.temperature;
	}

	public double getTemp_min() {

		return this.Temp_min ;
	}
	public double getFeel_like() {
		return this.feel_like ;
	}
	public double getTemp_max() {
		return this.Temp_max ;
	}
	public double getPressure() {
		return this.Pressure ;
	}
	public double getHumidity() {
		return this.Humidity ;
	}
	public String getWeatherIcon() {
		return this.weatherIcon;
	}
	public Integer getWeatherId() {
		return this.weatherId;
	}
	public StringBuilder getweather(){

		StringBuilder build = new StringBuilder();
		return build.append("main :" +getmain());

	}

public Map<String,Double> getmain(){
	setmain(map);
	return map;
}



	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherId((Integer) weather.get("id"));
		setWeatherIcon((String) weather.get("icon"));
	}
	@JsonProperty("timestamp")
	public Instant getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long unixTime) {
		this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {

		setTemperature(Double.parseDouble(main.get("temp").toString()));
		setFeel_like(Double.parseDouble(main.get("feels_like").toString()));
		setTemp_min(Double.parseDouble(main.get("temp_min").toString()));
		setTemp_max(Double.parseDouble(main.get("temp_max").toString()));
		setPressure(Double.parseDouble(main.get("pressure").toString()));
		setHumidity(Double.parseDouble(main.get("humidity").toString()));

	}

}
