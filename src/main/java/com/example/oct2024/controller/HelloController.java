package com.example.oct2024.controller;

import com.example.oct2024.model.Message;
import com.example.oct2024.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Creates an object of the Controller during server start up - IOC (Inversion Of Control)
@RequestMapping("/v1/hello") // request mapping the controller to the URI
@Slf4j
public class HelloController {

    @Autowired
    CalculatorService calculatorService;

    // http://127.0.0.1:9000/oct2024/greet
    // first token - v1/hello
    // third token - greet
    @GetMapping("/greet") // greet URI is mapped to the sayHello() method
    public String sayHello(){
        return "WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    // http://127.0.0.1:9000/oct2024/v1/hello/greet/VAMSHI
    @GetMapping("/greet/{placeHolder}")
    public String sayHello(@PathVariable("placeHolder") String inputName){
        System.out.println("INPUT RECEIVED: "+inputName);
        return "HI "+ inputName + " , WELCOME TO SPRINGBOOT APPLICATION";
    }

    @GetMapping("/greet/{firstName}/{lastName}")
    public String sayHello(@PathVariable("firstName") String fName, @PathVariable("lastName") String lName){
        return "HI "+ fName + " "+lName+" , WELCOME TO SPRINGBOOT APPLICATION";
    }

    // http://127.0.0.1:9000/oct2024/v1/hello/greetwithparams?fName=VAMSHI
    @GetMapping("/greetwithparams")
    public String sayHelloWithParams(@RequestParam("fName") String inputName){
        return "HI "+ inputName + " , WELCOME TO SPRINGBOOT APPLICATION - WITH PARAMS";
    }
    // http://127.0.0.1:9000/oct2024/v1/hello/greetwithparameters?fName=FIRST&&lName=LAST
    @GetMapping("/greetwithparameters")
    public String sayHelloWithParams(@RequestParam("fName") String fName, @RequestParam("lName") String lName){
        return "HI "+ fName + " "+lName+" , WELCOME TO SPRINGBOOT APPLICATION - WITH PARAMS";
    }

    @PostMapping("/post")
    public String postMessage(@RequestBody Message message){
        log.info("INSIDE THE POST MESSAGE: Input received - {} {}", message.getMsgId(), message.getMsgString());
        calculatorService.postMessage(message);
        return message.toString();
    }

}
