package com.jayeshtajane.salarymanager.modal.attendence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class Attendence {
	private static String attendenceJson;
	private static String attendneceDate;
	
	private ArrayList<Attendence> attendenceList = new ArrayList<Attendence>();
	
	private String empName;
	private String empNo;
	private boolean isPresent;
	
	private static Connection con;
		
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	
	public ArrayList<Attendence> getAttendenceList() {
		return attendenceList;
	}

	public void setAttendenceList(ArrayList<Attendence> attendenceList) {
		this.attendenceList = attendenceList;
	}

	public Attendence() {
		super();
		con = MyConnection.getConnection();
	}
	
	public String formatAttendence(String attendence) {
		System.out.println("INFO : Attendence before formated = " + attendence);
		char[] arr = attendence.toCharArray();
		attendence = "";
		// loop for removing the extra commas from attendence
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != ',') {
				attendence += arr[i] + ",";
			}
		}
		System.out.println("INFO : Attendence after formated = " + attendence);
		System.out.println("INFO : Attendence formated"); 
		return attendence;
	}
	public void save(String attendence, String attendneceDate) {
		attendence = formatAttendence(attendence); 
		if(checkAttendence(attendneceDate)) {
			update(attendence, attendneceDate);
			return;
		}
		insert(attendence, attendneceDate);
	}
	private boolean checkAttendence(String attendneceDate) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			String query = "select * from attendence where date='" + attendneceDate + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) 
				return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		return false;
	}
	private void insert(String attendence, String attendneceDate) {
		try {
			String query = " insert into attendence (attendence, date) values (?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	        preparedStmt.setString (1, attendence);
	        preparedStmt.setString (2, attendneceDate);
	        preparedStmt.execute();
	        preparedStmt.close();
	        con.close();
	        System.out.println("INFO : Attendence saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Attendence not saved");
		}
	}
	private void update(String attendence, String attendneceDate) {
		try {
			String query = "update attendence set attendence=? where date=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	        preparedStmt.setString (1, attendence);
	        preparedStmt.setString (2, attendneceDate);
	        preparedStmt.execute();
	        preparedStmt.close();
	        con.close();
	        System.out.println("INFO : Attendence updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Attendence not updated");
		}   
	}
	public String getAttendence(String attendneceDate) {
		System.out.println("CALL : getAttendence()");
		String attendence = "";
		Statement stmt1;
		Statement stmt2;
		if(attendenceList == null) {
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
						Attendence att = new Attendence();
						char arr[] = attendence.toCharArray();
						String id = "";
						
						att.empNo = employeeResult.getString("empid"); 
						att.empName = employeeResult.getString("ename");
						att.isPresent = false;
						
						for(int i = 0; i < arr.length; i++) {
							if(arr[i] != ',') {
								id += arr[i];
							}
							if(employeeResult.getString("empno") == id) {
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
						Attendence att = new Attendence();
						
						att.empNo = employeeResult.getString("empid");
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
			finally {
				
			}
		}
		System.out.println("INFO : Attendence list returned. - "  + attendence);
		System.out.println("INFO : Attendence JSON. - "  + attendenceJson);
		return attendenceJson;
	}

	private void eget() {
		
	}
	@Override
	public String toString() {
		return "Attendence [empName=" + empName + ", empNo=" + empNo + ", isPresent=" + isPresent + "]";
	}
	
}
