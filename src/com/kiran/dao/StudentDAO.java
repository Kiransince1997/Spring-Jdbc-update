package com.kiran.dao;

import java.util.List;
import java.util.Map;

import com.kiran.api.Student;

public interface StudentDAO {
	
	public void insert(Student student);
	
	public void insert(List<Student> list);
	
	public List<Student> findAllStudents();
	
	public Student findStudentByID(int RollNo);
	
	public List<Student> findStudentByName(String name);

	public boolean DeleteTableById(int RollNo);
	
	int deleteStudentByNameAndAddress(String name, String address);
	
	void cleanUpTable();
	
	public Map<String, List<String>> groupAllStudentsByAddress();
	
	public int updateStudent(Student student);
	int updateStudent(List<Student> studentList);
	
}
