package com.example.demo.dto.api.currency;

import com.example.demo.domain.currency.Currency;
import com.example.demo.domain.currency.CurrencyService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@ComponentScan({"com.example.demo"})
public class CurrencyApi {
    private final CurrencyService currencyService;

    public CurrencyApi(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    public Currency createCurrency(String city,String home_currency,String amount,String destination,Double exchange_rate ){
     return    currencyService.createCurrency(city,home_currency,amount,destination,exchange_rate);

    }
}
