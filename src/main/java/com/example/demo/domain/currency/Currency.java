package com.example.demo.domain.currency;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com.example.demo"})
public class Currency {
    public String city;
    public String home_currency;
    public String destination_currency;
    public String home_amount;
    public Double destination_amount;

    Currency(){}
    public Currency(String city, String home_currency, String destination_currency, String home_amount, Double destination_amount) {
        this.city = city;
        this.home_currency = home_currency;
        this.destination_currency = destination_currency;
        this.home_amount = home_amount;
        this.destination_amount = destination_amount;
    }


}
