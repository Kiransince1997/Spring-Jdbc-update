package com.kiran.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.api.Student;
import com.kiran.dao.StudentDAO;

@Service("studentDAOHelper")
public class StudentDAOHelper {

	@Autowired
	private StudentDAO studentDAOImpl;

	public void setUpStudentTable() {

		Student s1 = new Student();
		s1.setRollNo(1);
		s1.setName("Akash");
		s1.setAddress("Bidar");

		Student s2 = new Student();
		s2.setRollNo(2);
		s2.setName("Aditya");
		s2.setAddress("Ranebennur");

		Student s3 = new Student();
		s3.setRollNo(3);
		s3.setName("Ajith");
		s3.setAddress("Haveri");

		ArrayList<Student> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3);

		
		studentDAOImpl.insert(list);
		System.out.println("inserted Successfully");
		

	}
}
