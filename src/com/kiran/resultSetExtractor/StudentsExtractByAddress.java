package com.kiran.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StudentsExtractByAddress implements ResultSetExtractor<Map<String, List<String>>> {

	@Override
	public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String , List<String>> StudentTable = new HashMap<>();
			while(rs.next()) {
				String studentName = rs.getString("Student_name");
				String studentAddress = rs.getString("Student_address");
				
				List<String> studentList = StudentTable.get(studentAddress);
				if(studentList==null) {
					ArrayList<String> arrayList = new ArrayList<>();
					arrayList.add(studentName);
					
					StudentTable.put(studentAddress, arrayList);
				}else {
					studentList.add(studentName);
					
				}
			}
		return StudentTable;
	}
	
}
