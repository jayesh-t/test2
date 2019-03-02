package com.jayeshtajane.salarymanager.modal.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jayeshtajane.salarymanager.employee.Employee;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class UpdateEmployee {
	
	public UpdateEmployee() {
		super();
	}
	
	public void update(Employee emp) {
		Connection con = MyConnection.getConnection();
		try {
			String query = "update employee set ename=?, city=?, contact=?, salary=? where empid=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	        preparedStmt.setString (1, emp.getEmpName());
	        preparedStmt.setString (2, emp.getEmpCity());
	        preparedStmt.setString (3, emp.getEmpContact());
	        preparedStmt.setDouble (4, emp.getEmpSal());
	        preparedStmt.setInt (5, emp.getEmpId());
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
}
