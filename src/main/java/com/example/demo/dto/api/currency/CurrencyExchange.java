package com.example.demo.dto.api.currency;

import com.example.demo.domain.currency.CurrencyExchangeDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyExchange {
    private List<CurrencyDetails> Response;
    public CurrencyExchange(List<CurrencyDetails> response) {
        Response = response;
    }
    public List<CurrencyDetails> getResponse() {
        return Response;
    }
    public CurrencyExchangeDomain create(){
        return new CurrencyExchangeDomain(Response.stream().map(p->p.create()).collect(Collectors.toList()));
    }
      class CurrencyDetails{
        public String Name;
        public String CurrencyCode;
         public CurrencyExchangeDomain.CurrencyDetails create(){
             return new CurrencyExchangeDomain.CurrencyDetails(Name,CurrencyCode);
         }
    }

}
