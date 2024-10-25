package com.example.oct2024.controller;

import com.example.oct2024.model.Message;
import com.example.oct2024.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @Autowired // Dependency Injection - DI
    CalculatorService calculatorService;

    @GetMapping("/divide/{fNum}/{sNum}")
    public ResponseEntity<Object> divide(@PathVariable("fNum") double fNum, @PathVariable("sNum") double sNum){
        if(sNum == 0){
            return new ResponseEntity<>("DIVIDE BY ZERO IS NOT POSSIBLE, PLEASE RETRY WITH DIFFERENT NUMBER", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(calculatorService.divide(fNum, sNum), HttpStatus.OK);
    }

    @GetMapping("/interest/{principle}/{term}/{loanType}")
    public Double getTotalPendingPrinciple(@PathVariable("principle") double principle,
                                                 @PathVariable("term") double term,
                                                 @PathVariable("loanType") String loanType){
        return calculatorService.calculateInterest(principle, term, loanType).block();
    }
}
