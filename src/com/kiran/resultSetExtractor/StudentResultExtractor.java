package com.kiran.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.kiran.api.Student;

public class StudentResultExtractor implements ResultSetExtractor<List<Student>>{

	@Override
	public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
	
		List<Student> list = new ArrayList<>();
		while(rs.next()) {
		Student student = new Student();
		student.setRollNo(rs.getInt("Roll_no"));
		student.setName(rs.getString("Student_name"));
		student.setAddress(rs.getString("Student_address"));
	
		list.add(student);}
		System.out.println("Inside the ResultSet Extractor method");
		return list;
	}

}
