package com.jayeshtajane.salarymanager.modal.employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jayeshtajane.salarymanager.modal.MyConnection;

public class DeleteEmployee {
	
	public DeleteEmployee() {
		super();
	}
	
	public void delete(int empId) {
		Connection con = MyConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "delete from employee where empid=" + empId;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("INFO :"+ empId +" Employee deleted"); 
	}
}
