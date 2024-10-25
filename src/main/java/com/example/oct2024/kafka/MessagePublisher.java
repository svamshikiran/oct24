package com.example.oct2024.kafka;

import com.example.oct2024.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Component
public class MessagePublisher {

    @Autowired
    KafkaTemplate kafkaTemplate;

    private static String topicName = "student.oct2024.queue";

    public String sendSimpleMessage(Student student) throws JsonProcessingException {

        Message<Student> message = MessageBuilder
                .withPayload(student)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        CompletableFuture future = kafkaTemplate.send(message);
        return "Success";
    }

}
