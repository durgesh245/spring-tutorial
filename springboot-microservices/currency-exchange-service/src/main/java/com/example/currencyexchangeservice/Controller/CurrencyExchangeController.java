package com.example.currencyexchangeservice.Controller;

import com.example.currencyexchangeservice.model.CurrencyExchange;
import com.example.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to){
        //return new CurrencyExchange(1000l, from, to, BigDecimal.valueOf(65), environment.getProperty("local.server.port"));

       CurrencyExchange currencyExchange =  currencyExchangeRepository.findByFromAndTo(from,to);

       if(currencyExchange == null){
           throw new RuntimeException("Unable to find the data from "+from+" to "+to);
       }
        currencyExchange.setEnvironment(environment.getProperty("server.port"));
       return currencyExchange;
    }
}
