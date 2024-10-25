package com.example.oct2024.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "courses")
public class Duration {
    private String name;
    private int duration;
    private Date createddate;
}
