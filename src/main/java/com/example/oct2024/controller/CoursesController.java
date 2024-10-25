package com.example.oct2024.controller;

import com.example.oct2024.model.Courses;
import com.example.oct2024.model.Duration;
import com.example.oct2024.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add/courses")
    public void addCourses(@RequestBody Courses courses){
        coursesService.addCourses(courses);
    }

    @PostMapping("/add/duration")
    public void addDuration(@RequestBody Duration duration){
        coursesService.addDuration(duration);
    }

    @GetMapping("/all/duration")
    public List<Duration> getAllDuration(){
        return coursesService.getAllDurations();
    }
}
