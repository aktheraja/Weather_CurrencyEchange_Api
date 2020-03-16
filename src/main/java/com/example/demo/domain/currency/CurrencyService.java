package com.example.demo.domain.currency;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com.example.demo"})
public class CurrencyService {
    CurrencyService(){}
    public Currency createCurrency(String city,String home_currency,String amount,String destination,Double exchange_rate){
        return new Currency(
                city,home_currency,destination,amount,Double.parseDouble(amount)*exchange_rate
        );
    }

}
