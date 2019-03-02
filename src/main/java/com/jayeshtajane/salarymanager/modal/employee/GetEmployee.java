package com.jayeshtajane.salarymanager.modal.employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.employee.Employee;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class GetEmployee {
	private ArrayList<Data> employee = new ArrayList<Data>();
	private String employeeJson;

	public GetEmployee() {
		super();
	}
	
	public String get() {
		try {
			Connection con = MyConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String query = "select * from employee";
			ResultSet employeeResult = stmt.executeQuery(query);
			
			while(employeeResult.next()) {
				System.out.println("INFO : Employee data found");
				Data emp = new Data();
				emp.setEmpId(employeeResult.getString("empid"));
				emp.setEmpName(employeeResult.getString("ename"));
				emp.setEmpSal(employeeResult.getDouble("salary"));
				employee.add(emp);
				System.out.println(emp); 
			}
	        System.out.println("INFO : Employee data geted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Employee data not geted");
		}
		
		ObjectMapper om = new ObjectMapper();
		try {
			employeeJson = om.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return employeeJson;
	}
	
	public String getName(int empid) {
		try {
			Connection con = MyConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String query = "select ename from employee where empid="+empid;
			ResultSet employeeResult = stmt.executeQuery(query);
			
			while(employeeResult.next()) {
				System.out.println("INFO : Employee name found");
				System.out.println("INFO : Employee name = " + employeeResult.getString("ename"));
				return employeeResult.getString("ename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("INFO : Employee data not geted");
		}
		return "";
	}
	
	class Data {
		String empId;
		String empName;
		double empSal;
		
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

		public double getEmpSal() {
			return empSal;
		}

		public void setEmpSal(double empSal) {
			this.empSal = empSal;
		}

		@Override
		public String toString() {
			return "Data [empNo=" + empId + ", empName=" + empName + ", salary=" + empSal + "]";
		}
		
	}
}
