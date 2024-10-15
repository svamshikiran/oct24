package com.example.oct2024.mapper;

import com.example.oct2024.dto.StudentSpecDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<StudentSpecDto> {
    @Override
    public StudentSpecDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentSpecDto studentSpecDto = new StudentSpecDto();
        studentSpecDto.setRollno(rs.getInt("rollno"));
        studentSpecDto.setName(rs.getString("name"));
        studentSpecDto.setSpName(rs.getString("sp_name"));
        return studentSpecDto;
    }
}
