package com.example.oct2024.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity // ORM - Hibernate
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "rollno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rollno_seq")
    @SequenceGenerator(name = "rollno_seq", sequenceName = "student_rollno_seq", allocationSize = 1)
    private int rollno;
    private String name;
    private String city;
    private String createdby;
    private Date createddate;
    private String modifiedby;
    private Timestamp modifieddate;
    @OneToOne
    @JoinColumn(name = "sp_id")
    private Specialization specialization;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
