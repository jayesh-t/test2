package com.jayeshtajane.salarymanager.modal.attendence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class GetAttendence {
	private ArrayList<Data> attendenceList;
	private String attendenceJson;
	
	public GetAttendence() {
		super();
	}
	
	public String getAttendence(String attendneceDate) {
		Connection con = MyConnection.getConnection();
		System.out.println("CALL : getAttendence("+ attendneceDate +")");
		String attendence = "";
		Statement stmt1;
		Statement stmt2;
		attendenceList = new ArrayList<Data>();
		try {
			stmt1 = con.createStatement();
			String query = "select * from attendence where date='" + attendneceDate + "'";
			ResultSet attendenceResult = stmt1.executeQuery(query);

			stmt2 = con.createStatement();
			query = "select empid,ename from employee";
			ResultSet employeeResult = stmt2.executeQuery(query);
			
			boolean attendenceFound = false;
			
			while(attendenceResult.next()) {
				System.out.println("INFO : Privious attendence found");
				attendenceFound = true;
				attendence = attendenceResult.getString("attendence");
				System.out.println("INFO : Attendence fetched. - " + attendence);
				
				while(employeeResult.next()) {
					Data att = new Data();
					//char arr[] = attendence.toCharArray();
					String[] arr = attendence.split(",");
					
					
					att.empId = employeeResult.getString("empid"); 
					att.empName = employeeResult.getString("ename");
					att.isPresent = false;
					
					for(String id : arr) {
						if(att.empId.equals(id)) {
							att.isPresent = true;
							break;
						}
					}
					System.out.println("INFO : Employee fetched. - " + att);
					attendenceList.add(att);
				}
			}
			if(! attendenceFound) {
				System.out.println("INFO : No privious attendence found");
				while(employeeResult.next()) {
					Data att = new Data();
					
					att.empId = employeeResult.getString("empid");
					att.empName = employeeResult.getString("ename");
					att.isPresent = false;  
					
					System.out.println("INFO : Employee fetched. - " + att);
					attendenceList.add(att);
				}
			}
			System.out.println("INFO : Attendence list initialized.");
			ObjectMapper om = new ObjectMapper();
			attendenceJson = om.writeValueAsString(attendenceList);
			System.out.println("INFO : Attendence list created.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("INFO : Attendence list returned. - "  + attendence);
		System.out.println("INFO : Attendence JSON. - "  + attendenceJson);
		return attendenceJson;
	}
	
	class Data {
		String empId;
		String empName;
		boolean isPresent;
		
		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empNo) {
			this.empId = empNo;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public boolean isPresent() {
			return isPresent;
		}

		public void setPresent(boolean isPresent) {
			this.isPresent = isPresent;
		}

		@Override
		public String toString() {
			return "Data [empNo=" + empId + ", empName=" + empName + ", isPresent=" + isPresent + "]";
		}
		
	}
}
