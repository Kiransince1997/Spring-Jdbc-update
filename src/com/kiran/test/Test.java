package com.kiran.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiran.api.Student;
import com.kiran.dao.StudentDAOImpl;
import com.kiran.helper.StudentRowMapper;
import com.kiran.service.StudentDAOHelper;

public class Test {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		System.out.println("Beans.xml file is loaded");
		StudentDAOImpl studentDAOImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		StudentRowMapper mapper = context.getBean("studentRowMapper", StudentRowMapper.class);

		StudentDAOHelper helper = context.getBean("studentDAOHelper", StudentDAOHelper.class);
//		helper.setUpStudentTable();

//		Student s = new Student();
//		s.setRollNo(3);
//		s.setName("Jhon");
//		s.setAddress("Japan");

//		studentDAOImpl.insert(s);
		List<Student> findAllStudents = studentDAOImpl.findAllStudents();
		mapper.printStudent(findAllStudents);
		System.out.println("***********************");
		
		/* fecthing details by using id */

//		System.out.println("fecthing details by using id");
//		Student findStudentByID = studentDAOImpl.findStudentByID(2);
//		System.out.println(findStudentByID);

		/* Deleting record by id */

//		boolean cleanTableById = studentDAOImpl.DeleteTableById(2);
//		if(cleanTableById==true) {System.out.println("Record deleted for the Roll Number 2");};

		/* deleting record using both name and address */

//		studentDAOImpl.deleteStudentByNameAndAddress("kiru", "shimoga");

		/* deleting all the records in the table */

//		studentDAOImpl.cleanUpTable();
		
		/*fecting details by using ResultSetExtractor*/
		
		List<Student> findStudentByName = studentDAOImpl.findStudentByName("Akash");
		mapper.printStudent(findStudentByName);
		
		Map<String, List<String>> groupAllStudentsByAddress = studentDAOImpl.groupAllStudentsByAddress();
		System.out.println(groupAllStudentsByAddress);
	
		/*Update single student */
		
//		Student Akash = new Student();
//		Akash.setRollNo(1);
//		Akash.setAddress("gulbarga");
//		
//		studentDAOImpl.updateStudent(Akash);
System.out.println("****************************************");		
		Student akash = new Student();
		akash.setAddress("Bidar");
		akash.setRollNo(1);
		
		Student aditya = new Student();
		aditya.setAddress("Nukapur");
		aditya.setRollNo(2);
		
		Student nalini = new Student();
		nalini.setAddress("NagendraMata");
		nalini.setRollNo(5);
		
		List<Student> studentList = new ArrayList<>();
		studentList.add(akash);
		studentList.add(aditya);
		studentList.add(nalini);
		
		int updateStudent = studentDAOImpl.updateStudent(studentList);
		System.out.println(updateStudent+" rows are updated succesfuly");
	}
}
