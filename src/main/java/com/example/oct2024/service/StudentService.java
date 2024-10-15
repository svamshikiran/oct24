package com.example.oct2024.service;

import com.example.oct2024.dto.StudentSpecDto;
import com.example.oct2024.mapper.StudentRowMapper;
import com.example.oct2024.model.Student;
import com.example.oct2024.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        System.out.println("INSIDE STUDENT SERVICE");
        return studentRepository.findAll();
        // generate the SQL query - select * from student
        // execute the query
        // read and iterate through the resultSet
        // create the Student object and store the record values
        // add the student object to the student list
    }

    public Student getStudentByRollno(int rollno){
        Optional<Student> opStudent = studentRepository.findById(rollno);
        if(opStudent.isPresent()){
            return opStudent.get();
        }
        else{
            return new Student();
        }
        // select * from student where rollno = ?
    }

    public List<Student> getStudentByName(String name){
        return studentRepository.findByName(name);
    }

    public void upsertStudent(Student student){
        studentRepository.save(student);
        //insert into student values(??????);
    }

    public boolean isStudentPresent(int rollno){
        return studentRepository.existsById(rollno);
    }

    public void deleteStudent(int rollno){
        studentRepository.deleteById(rollno);
    }

    public List<Student> getStudentsBySpecialization(int spId){
        return studentRepository.findBySpecializationId(spId);
    }

    public List<StudentSpecDto> getStudentsWithSpecialization(int spId){
        return jdbcTemplate.query("SELECT ROLLNO, NAME, SP_NAME FROM STUDENT S, SPECIALIZATION SP " +
                "WHERE S.SP_ID=SP.SP_ID AND S.SP_ID=?", new StudentRowMapper(), spId);
    }
}
