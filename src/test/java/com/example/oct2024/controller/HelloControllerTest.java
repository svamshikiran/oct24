package com.example.oct2024.controller;

import com.example.oct2024.model.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    HelloController helloController;

    @Test
    void sayHello() {
        String expectedString = "WELCOME TO SPRINGBOOT FRAMEWORK";
        String actualString = helloController.sayHello();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testSayHello() {
        String fName = "TEST_FNAME";
        String lName = "TEST_LNAME";
        String actualOutput = helloController.sayHello(fName, lName);
        Assertions.assertTrue(actualOutput.contains(fName) && actualOutput.contains(lName));
    }

    @Test
    void testSayHello1() {
        String input = "TESTINPUT";
        String actualOutput = helloController.sayHello(input);
        Assertions.assertTrue(actualOutput.contains(input));
    }

    @Test
    void sayHelloWithParams() {
        String input = "TESTINPUT";
        String actualOutput = helloController.sayHelloWithParams(input);
        Assertions.assertTrue(actualOutput.contains(input));
    }

    @Test
    void testSayHelloWithParams() {
        String fName = "TEST_FNAME";
        String lName = "TEST_LNAME";
        String actualOutput = helloController.sayHelloWithParams(fName, lName);
        Assertions.assertTrue(actualOutput.contains(fName) && actualOutput.contains(lName));
    }

    @Test
    void postMessage() {
        Message message = new Message();
        message.setMsgId(123);
        message.setMsgString("TESTMESSAGE");
        String actualOutput = helloController.postMessage(message);
        assertNotNull(actualOutput);
        assertTrue(actualOutput.contains(String.valueOf(message.getMsgId())));
        assertTrue(actualOutput.contains(message.getMsgString()));
    }
}