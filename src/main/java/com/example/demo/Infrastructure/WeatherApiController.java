package com.example.demo.Infrastructure;

import com.example.demo.domain.currency.Currency;
import com.example.demo.domain.weather.Weather;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherApiController {

	private final RootService weatherService;

	public WeatherApiController(RootService weatherService) {
		this.weatherService = weatherService;
	}

	@RequestMapping("/weather/{city}/{home_currency}/{travel_date}")
	public Weather getWeather(@PathVariable String city,
													   @PathVariable String home_currency,
	@PathVariable String travel_date) {
	return this.weatherService.getWeather(city,home_currency,travel_date);
	}

	@RequestMapping("/currency/{city}/{home_currency}/{amount}")
	public Currency getWeatherForecast(@PathVariable String city,
									   @PathVariable String home_currency, @PathVariable String amount ) {
		return this.weatherService.getCurrencyForecast(city,home_currency,amount);
	}

}
