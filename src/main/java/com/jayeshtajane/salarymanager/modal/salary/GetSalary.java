package com.jayeshtajane.salarymanager.modal.salary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class GetSalary {
	
	
	public String get() {
		String json = "";
		try {
			Connection con = MyConnection.getConnection();
			Statement stmt1, stmt2;
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			
			String query = "select * from employee";
			ResultSet employeeResult = stmt1.executeQuery(query);
			ArrayList<Data> salary = new ArrayList<GetSalary.Data>();
			
			while(employeeResult.next()) {
				System.out.println("INFO : Employee data found");
				Data emp = new Data();
				emp.setEmpId(employeeResult.getString("empid"));
				emp.setEmpName(employeeResult.getString("ename"));
				
				query = "select * from employee_balance where empid = " + emp.empId;
				ResultSet balanceResult = stmt2.executeQuery(query);
				while(balanceResult.next()) {
					System.out.println("INFO : Balance found");
					emp.setEmpBalance(balanceResult.getDouble("balance"));
				}
				
				salary.add(emp);
				System.out.println(emp); 
			}
			
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(salary);
	        System.out.println("INFO : Employee data geted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Employee data not geted");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	class Data {
		String empId;
		String empName;
		double empBalance;
		
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

		public double getEmpBalance() {
			return empBalance;
		}

		public void setEmpBalance(double empBalance) {
			this.empBalance = empBalance;
		}

		@Override
		public String toString() {
			return "Data [empNo=" + empId + ", empName=" + empName + ", salary=" + empBalance + "]";
		}
		
	}
}
