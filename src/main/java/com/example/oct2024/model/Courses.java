package com.example.oct2024.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "courses")
public class Courses {
    private int courseid;
    private String coursename;
}
