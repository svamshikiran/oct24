package com.example.oct2024.service;

import com.example.oct2024.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CalculatorService {

    @Autowired
    WebClient webClient;
    public double divide( double first, double second){
        return first / second;
    }

    public Mono<Double> calculateInterest(double principle, double term, String loanType){
        return webClient.get().uri("interest/calculate/"+principle+"/"+term+"/"+loanType)
                .retrieve().bodyToMono(Double.class);
    }

    public void postMessage(Message message){
        webClient.post().uri("hello/post").body(Mono.just(message), Message.class)
                .retrieve().bodyToMono(Void.class);
    }


}
