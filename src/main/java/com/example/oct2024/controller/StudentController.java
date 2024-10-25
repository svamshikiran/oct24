package com.example.oct2024.controller;

import com.example.oct2024.dto.StudentDto;
import com.example.oct2024.dto.StudentSpecDto;
import com.example.oct2024.model.Student;
import com.example.oct2024.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/student")
@Tag(name = "Student Management", description = "This API provides the feature to manage the STUDENT details")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        System.out.println("INSIDE STUDENT CONTROLLER");
        return studentService.getAllStudents();
    }

    @GetMapping("/get/{rollno}")
    @Operation(summary = "Get Student By Rollno", description = "This method will provide the student details based on the rollno provided as input", method = "getStudentByRollno")
    @ApiResponse(responseCode = "200", description = "POSTIVE RESPONSE")
    @ApiResponse(responseCode = "400", description = "NEGATIVE RESPONSE/BAD REQUEST")
    @ApiResponse(responseCode = "500", description = "ERROR/EXCEPTION RESPONSE")
    public ResponseEntity<Object> getStudentByRollno(@PathVariable("rollno") int rollno){
        if(rollno <= 0)
            return new ResponseEntity<>("NEGATIVE/ZERO Roll No is not accepted", HttpStatus.BAD_REQUEST);
        else{
            try {
                Student studentObj = studentService.getStudentByRollno(rollno);
                if (studentObj.getRollno() == 0)
                    return new ResponseEntity<>("STUDENT DOESN'T EXIST", HttpStatus.BAD_REQUEST);
                else{
                    return new ResponseEntity<>(getStudentDto(studentObj), HttpStatus.OK);
                }

            }catch(Exception exception){
                return new ResponseEntity<>("EXECUTION ERROR - "+exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private StudentDto getStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setRoll(student.getRollno());
        studentDto.setStudentName(student.getName().toLowerCase());
        studentDto.setStudentCity(student.getCity().toLowerCase());
        return studentDto;
    }

    @GetMapping("/getByName/{name}")
    public List<Student> getStudentByName(@PathVariable("name") String name){
        return studentService.getStudentByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student) throws JsonProcessingException {
        studentService.upsertStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) throws JsonProcessingException {
        if(student.getRollno()<= 0)
            return new ResponseEntity<>("NEGATIVE/ZERO Roll No is not accepted", HttpStatus.BAD_REQUEST);
        if(!studentService.isStudentPresent(student.getRollno()))
            return new ResponseEntity<>("CAN'T PERFORM UPDATE - STUDENT doesn't exists with Rollno : "+student.getRollno(), HttpStatus.BAD_REQUEST);
        studentService.upsertStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{rollno}")
    public void deleteStudent(@PathVariable("rollno") int rollno){
        studentService.deleteStudent(rollno);
    }

    @GetMapping("/get/specialization/{spId}")
    public List<StudentSpecDto> getStudentsBySpecialization(@PathVariable("spId") int spId){
        return studentService.getStudentsWithSpecialization(spId);
    }
}
