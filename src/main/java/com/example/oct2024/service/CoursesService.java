package com.example.oct2024.service;

import com.example.oct2024.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Courses> getAllCourses(){
        return mongoTemplate.findAll(Courses.class);
    }

    public Courses getCourseById(int id){
        Query query = new Query();
        query.addCriteria(Criteria.where("courseid").is(id));
        return mongoTemplate.findOne(query, Courses.class);
    }
}
