package com.example.demo.Infrastructure;

import com.example.demo.domain.ConfigBuilder;
import com.example.demo.domain.currency.Currency;
import com.example.demo.domain.currency.CurrencyExchangeDomain;
import com.example.demo.domain.weather.Weather;
import com.example.demo.dto.api.currency.CurrencyApi;
import com.example.demo.dto.api.currency.CurrencyExchange;
import com.example.demo.dto.api.weather.*;
import com.google.gson.Gson;
import com.posadskiy.currencyconverter.CurrencyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RootService {
	private final RestTemplate restTemplate;
	private final WeatherApi weatherApi;
	private final CurrencyApi currencyApi;
	private Gson gson;
	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/forecast?q={city}&APPID={key}";
	private static final String WEATHER_DSC_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/{city}";
	private final String API_KEY = "272be4ea1011b9c08c361039904b995d";
	private final String CURRENCY_COUNTRY_URL = "http://countryapi.gear.host/v1/Country/getCountries";
	public static final String CURRENCY_CONVERTER_API_API_KEY = "ca82c2a514c5a04ff56e";


@Autowired
	RootService(Gson gson, RestTemplateBuilder restTemplate, WeatherApi weatherApi, CurrencyApi currencyApi){
		this.gson=gson;
		this.restTemplate = restTemplate.build();
		this.weatherApi = weatherApi;
	this.currencyApi = currencyApi;
}
	private static final Logger logger = LoggerFactory.getLogger(RootService.class);

   public String get_city_currency(WeatherDescription weatherDescription, String home_currency) {
	   String extact = weatherDescription.description.split(",")[1].trim();
	   URI url_3 = new UriTemplate(CURRENCY_COUNTRY_URL).expand();
	   CurrencyExchange currencyExchange = invoke(url_3, CurrencyExchange.class);
	   CurrencyExchangeDomain currencyExchange1 = currencyExchange.create();
	   Optional<CurrencyExchangeDomain.CurrencyDetails> destination = currencyExchange1
			   .Response.stream().filter(p -> p.country.toLowerCase().equals(extact.toLowerCase()))
			   .collect(Collectors.toList()).stream().findFirst();

	   currency_converter(destination.get().CurrencyCode,home_currency);
			   return destination.get().CurrencyCode;
   }

	  public Double currency_converter(String destination, String home_currency){
	   CurrencyConverter converter = new CurrencyConverter(
			 new  ConfigBuilder()
					   .currencyConverterApiApiKey(CURRENCY_CONVERTER_API_API_KEY)
					   .build()
	   );
	   Double exchange_rate = converter.rate(home_currency, destination);
	   return exchange_rate;
	}

	@Cacheable("weather")
	public Weather getWeather(String city, String home_currency,String travel_date) {
		logger.info("Requesting current weather for {}/{}", home_currency, city);
		URI url = new UriTemplate(WEATHER_URL).expand(city, API_KEY);
		URI url_2 = new UriTemplate(WEATHER_DSC_URL).expand(city, API_KEY);
	 WeatherGson weatherGson =invoke(url, WeatherGson.class);
		WeatherDescription weatherDescription= invoke(url_2, WeatherDescription.class);
		String exchange_code = get_city_currency(weatherDescription, home_currency);
		Double exchange_rate = currency_converter(exchange_code, home_currency);
		Weather weather = weatherApi.createWeather(weatherGson,weatherDescription,exchange_rate);
		return weather;
	}

	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate
				.exchange(request, responseType);
		return exchange.getBody();
	}

	public Currency getCurrencyForecast(String city, String home_currency, String amount) {
		URI url_2 = new UriTemplate(WEATHER_DSC_URL).expand(city, API_KEY);
		WeatherDescription weatherDescription= invoke(url_2, WeatherDescription.class);
		String destination = get_city_currency(weatherDescription, home_currency);
		Double exchange_rate = currency_converter(destination, home_currency);
		return currencyApi.createCurrency(city,home_currency,amount,destination,exchange_rate);


	}
}
