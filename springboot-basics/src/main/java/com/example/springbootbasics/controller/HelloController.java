package com.example.springbootbasics.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Component
//@Controller

//We have to use RestController where we expect to return a responseBody by default. It's include the property of Component and Controller Automatically.
@RestController
public class HelloController {

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String HelloWorld(){

        return "This is my Hello World Rest Controller with Name=>";
    }
}
