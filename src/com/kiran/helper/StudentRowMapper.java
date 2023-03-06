package com.kiran.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.kiran.api.Student;

public class StudentRowMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setRollNo(rs.getInt("Roll_no"));
		student.setName(rs.getString("Student_name"));
		student.setAddress(rs.getString("Student_address"));
		System.out.println("Maprow called........");
		return student;
	}

	public void printStudent(List<Student> studentList) {
		for(Student tempStudent : studentList) {
			System.out.println(tempStudent);
		}
	}
	
}
