package com.example.oct2024.controller;

import com.example.oct2024.model.Courses;
import com.example.oct2024.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/all")
    public List<Courses> getAllCourses(){
        return coursesService.getAllCourses();
    }

    @GetMapping("/get/{courseid}")
    public Courses getCoursesById(@PathVariable("courseid") int courseid){
        return coursesService.getCourseById(courseid);
    }
}
