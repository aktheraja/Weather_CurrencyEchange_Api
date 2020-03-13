package com.example.demo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class WeatherService {

	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

	private static final String FORECAST_URL =
			"http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private final RestTemplate restTemplate;

	private final String API_KEY = "272be4ea1011b9c08c361039904b995d";

	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Cacheable("weather")
	public Weather getWeather(String country, String city) {
		logger.info("Requesting current weather for {}/{}", country, city);
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, API_KEY);
		return invoke(url, Weather.class);
	}

//	@Cacheable("forecast")
//	public WeatherForecast getWeatherForecast(String country, String city) {
//		logger.info("Requesting weather forecast for {}/{}", country, city);
//		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
//		return invoke(url, WeatherForecast.class);
//	}

	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate
				.exchange(request, responseType);
		System.out.println(exchange);
		return exchange.getBody();
	}

}