package com.example.oct2024.service;

import com.example.oct2024.model.Courses;
import com.example.oct2024.model.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Courses> getAllCourses(){
        return mongoTemplate.findAll(Courses.class).stream().filter(courses -> courses.getCoursename() != null).collect(Collectors.toList());
    }

    public Courses getCourseById(int id){
        Query query = new Query();
        query.addCriteria(Criteria.where("courseid").is(id));
        return mongoTemplate.findOne(query, Courses.class);
    }

    public List<Duration> getAllDurations(){
        return mongoTemplate.findAll(Duration.class).stream().filter(duration -> duration.getName()!=null).collect(Collectors.toList());
    }

    public Duration getDurationByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Duration.class);
    }

    public void addCourses(Courses courses){
        mongoTemplate.save(courses);
    }

    public void addDuration(Duration duration){
        mongoTemplate.save(duration);
    }
}
