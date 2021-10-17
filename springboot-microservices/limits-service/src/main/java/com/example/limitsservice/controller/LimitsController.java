package com.example.limitsservice.controller;

import com.example.limitsservice.bean.Limits;
import com.example.limitsservice.configuration.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    LimitsConfiguration limitsConfiguration;

    @GetMapping("/limits")
    public Limits getLimits(){
        return new Limits(limitsConfiguration.getMinimum(),limitsConfiguration.getMaximum());
    }
}
