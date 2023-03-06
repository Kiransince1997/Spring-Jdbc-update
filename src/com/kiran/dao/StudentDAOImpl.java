package com.kiran.dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kiran.api.Student;
import com.kiran.helper.StudentRowMapper;
import com.kiran.resultSetExtractor.StudentResultExtractor;
import com.kiran.resultSetExtractor.StudentsExtractByAddress;

@Repository("studentDAO")
public class StudentDAOImpl implements StudentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private int rowCount;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Student student) {
		String sql = "insert into student values(?,?,?)";
		Object[] args = { student.getRollNo(), student.getName(), student.getAddress() };
		int noOfrowsInserted = jdbcTemplate.update(sql, args);
		System.out.println("No of rows inserted: " + noOfrowsInserted);
	}

	@Override
	public List<Student> findAllStudents() {
		String selectQuery = "select * from Student";
		List<Student> query = jdbcTemplate.query(selectQuery, new StudentRowMapper());

		return query;
	}

	@Override
	public Student findStudentByID(int RollNo) {
		String sql = "select * from Student where Roll_no=?";
		Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), RollNo);
		return student;
	}

	@Override
	public boolean DeleteTableById(int RollNo) {
		String sql = "delete from student where Roll_no=?";
		int noOfrowDeleted = jdbcTemplate.update(sql, RollNo);
		return noOfrowDeleted == 1;
	}

	@Override
	public int deleteStudentByNameAndAddress(String name, String address) {
		String sql = "delete " + "from student " + "where Student_name=? or Student_address=?";
		Object[] args = { name, address };
		int noOfrowsDeleted = jdbcTemplate.update(sql, args);

		System.out.println("No of rows deleted : " + noOfrowsDeleted);
		return noOfrowsDeleted;
	}

	@Override
	public void cleanUpTable() {
		/* it will delete the records present in the table not a table */
		String sql = "Truncate table Student";
		jdbcTemplate.update(sql);
		System.out.println("Table cleaned successfully...>>>>....");

	}

	@Override
	public void insert(List<Student> list) {
		String sql = "insert into Student value(?,?,?)";
		ArrayList<Object[]> list2 = new ArrayList<>();
		for (Student tempStudent : list) {
			Object[] args = { tempStudent.getRollNo(), tempStudent.getName(), tempStudent.getAddress() };
			list2.add(args);
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(sql, list2);
	    System.out.println("no of row inserted"+batchUpdate);
	}

	@Override
	public List<Student> findStudentByName(String name) {
			String sql = "Select * from Student where Student_name=?";
			List<Student> query = jdbcTemplate.query(sql, new StudentResultExtractor(),name);
		return query;
	}

	@Override
	public Map<String, List<String>> groupAllStudentsByAddress() {
		
			String sql="Select * from Student";
			Map<String, List<String>> query = jdbcTemplate.query(sql, new StudentsExtractByAddress());
		return query;
	}

	@Override
	public int updateStudent(Student student) {
		String sql ="update student set Student_address=? where Roll_no=?";

		int update = jdbcTemplate.update(sql,student.getAddress(), student.getRollNo());
		System.out.println(update +" updated successfully");
		return update;
	}

	@Override
	public int updateStudent(List<Student> studentList) {
		String sql="update student set Student_address=? where Roll_no=?";
		 int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
			 ps.setString(1, studentList.get(i).getAddress());
			 ps.setInt(2, studentList.get(i).getRollNo());
			 
			 System.err.println("inside the setValues method");
			}
			@Override
			public int getBatchSize() {
				System.out.println("inside the getBatchSize method");
				return studentList.size();
			}
		});
		 for(int i=0; i<batchUpdate.length; i++) {
			 if(batchUpdate[i] == 1) {
				 rowCount++;
			 }
		 }
		return rowCount;
	}

	// this method intialized in beans.xml
//	public DataSource getDataSource() {
//		String url="jdbc:mysql://127.0.0.1:3306/school?useSSL=false";
//		String userName="root";
//		String passWord="Kiran@12345";
//		DataSource dataSource = new DriverManagerDataSource(url, userName, passWord);
//		return dataSource;
//		
//	}

}
