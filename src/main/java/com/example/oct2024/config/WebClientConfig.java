package com.example.oct2024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                //.filter(logRequest()) // Apply ExchangeFilterFunction
                .build();
    }
    /*
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {

            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());

            clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return clientRequest.();
        });
    }*/
}
