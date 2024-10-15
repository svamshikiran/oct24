package com.example.oct2024.repository;

import com.example.oct2024.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findByName(String name);
    //select * from student where name=?

    public List<Student> findByCity(String city);
    //select * from student where city=?

    //Derived Query
    public List<Student> findByNameOrCity(String name, String City);
    //select * from student where name=? or city=?

    //Named Query
    @Query(value = "select s.* from student s, specialization sp where s.sp_id = sp.sp_id and sp.sp_id=?", nativeQuery = true)
    public List<Student> findBySpecializationId(int spId);
}
