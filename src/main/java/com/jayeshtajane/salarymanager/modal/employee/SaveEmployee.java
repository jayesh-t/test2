package com.jayeshtajane.salarymanager.modal.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jayeshtajane.salarymanager.employee.Employee;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class SaveEmployee {
	
	public SaveEmployee() {
		super();
	}
	
	public void save(Employee emp) {
		try {
			Connection con = MyConnection.getConnection();
			String query = " insert into employee (ename, city, contact, salary) values (?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStmt.setString (1, emp.getEmpName());
	        preparedStmt.setString (2, emp.getEmpCity());
	        preparedStmt.setString (3, emp.getEmpContact());
	        preparedStmt.setString (4, String.valueOf(emp.getEmpSal()));
	        preparedStmt.executeUpdate();
	        
	        long lastId = 0;
	        ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                lastId = generatedKeys.getLong(1);
            }
	        
	        System.out.println("INFO : Generated id = " + lastId); 
	        preparedStmt.close();
	        con.close(); 
	        setBalance(lastId); 
	        System.out.println("INFO : Employee data saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Employee dataa not saved");
		} 
	}
	private void setBalance(long empId) {
		Connection con = MyConnection.getConnection();
        PreparedStatement preparedStmt;
		try {
			String query = " insert into employee_balance (empid, balance) values (?, ?)";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, String.valueOf(empId)); 
	        preparedStmt.setDouble (2, 0);
	        preparedStmt.execute();
	        preparedStmt.close();
	        con.close();
	        System.out.println("INFO : Balance updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
