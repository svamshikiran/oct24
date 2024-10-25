package com.example.oct2024.controller;

import com.example.oct2024.dto.StudentDto;
import com.example.oct2024.model.Student;
import com.example.oct2024.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

    @InjectMocks // Where we are mocking
    StudentController studentController;

    @Mock // What we are mocking
    StudentService studentService;

    private Student student;

    @BeforeEach //it executes before the test scenarios - used for setting up tbe test data
    void doSetup(){
        student = new Student();
        student.setRollno(100);
        student.setName("TESTNAME");
        student.setCity("TESTCITY");
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void getStudentByRollnoNegative() {
        ResponseEntity responseEntity = studentController.getStudentByRollno(-1);
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCode().value());
        assertTrue(responseEntity.getStatusCode().is4xxClientError());
        assertTrue(((String)responseEntity.getBody()).contains("NEGATIVE"));
    }

    @Test
    void getStudentByRollnoExists() {
        when(studentService.getStudentByRollno(anyInt())).thenReturn(student); // Mocking scenario
        ResponseEntity responseEntity = studentController.getStudentByRollno(100);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(100, ((StudentDto)responseEntity.getBody()).getRoll());
    }

    @Test
    void getStudentByRollnoDoesnotExists() {
        when(studentService.getStudentByRollno(anyInt())).thenReturn(new Student()); // Mocking scenario
        ResponseEntity responseEntity = studentController.getStudentByRollno(100);
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCode().value());
    }

    @Test
    void getStudentByRollnoException() {
        when(studentService.getStudentByRollno(anyInt())).thenThrow(new RuntimeException()); // Mocking scenario
        ResponseEntity responseEntity = studentController.getStudentByRollno(100);
        assertNotNull(responseEntity);
        assertEquals(500, responseEntity.getStatusCode().value());
        assertTrue(((String)responseEntity.getBody()).contains("EXECUTION ERROR"));
    }

    @Test
    void getStudentByName() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getStudentsBySpecialization() {
    }
}