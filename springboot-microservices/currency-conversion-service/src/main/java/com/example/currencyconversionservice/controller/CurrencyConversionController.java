package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.model.CurrencyConversion;
import com.example.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable  String from, @PathVariable String to,
                                                          @PathVariable long qty){

        HashMap<String, String> urlVariables = new HashMap<>();
        urlVariables.put("from", from);
        urlVariables.put("to", to);
        //Calling the currency exchange microservices
        System.out.println("Calling the external currency exchange service");
        ResponseEntity<CurrencyConversion> responseEntity=
        new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,urlVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        System.out.println("Returned Response is ==>"+currencyConversion.toString());


        return new CurrencyConversion(currencyConversion.getId(), from, to,
                currencyConversion.getConversionMultiples(),BigDecimal.valueOf(qty),
                currencyConversion.getConversionMultiples().multiply(BigDecimal.valueOf(qty)) ,
                currencyConversion.getEnvironment()+" Rest-Template");
    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable  String from, @PathVariable String to,
                                                          @PathVariable long qty){
        //Calling microservices using openfeign service
        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to,
                currencyConversion.getConversionMultiples(),BigDecimal.valueOf(qty),
                currencyConversion.getConversionMultiples().multiply(BigDecimal.valueOf(qty)) ,
                currencyConversion.getEnvironment()+" openfeign");
    }
}
