package com.example.oct2024.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double divide( double first, double second){
        return first / second;
    }
}
