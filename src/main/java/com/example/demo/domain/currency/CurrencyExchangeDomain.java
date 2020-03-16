package com.example.demo.domain.currency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyExchangeDomain {
    public List<CurrencyDetails> Response;

    public CurrencyExchangeDomain(List<CurrencyDetails> response) {
        Response = response;
    }

    public static class CurrencyDetails{
        public String country;
        public String CurrencyCode;

        public CurrencyDetails(String country, String currencyCode) {
            this.country = country;
            CurrencyCode = currencyCode;
        }
    }
}
