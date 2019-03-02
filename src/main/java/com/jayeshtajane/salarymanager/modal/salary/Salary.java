package com.jayeshtajane.salarymanager.modal.salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.jayeshtajane.salarymanager.modal.MyConnection;

public class Salary {
	
	public void pay(String empId, String payableAmount) {
		System.out.println("CALL : pay("+ empId + "," + payableAmount +")");

		updateEmployeeBalance(empId, payableAmount, false);
		createMoneyTransaction(empId, payableAmount, false);
		
		System.out.println("INFO : "+ payableAmount +"Salary paid to " + empId);
	}
	public void payExtra(String empId, String payableAmount) {
		System.out.println("CALL : payExtra("+ empId + "," + payableAmount +")");
		
		updateEmployeeBalance(empId, payableAmount, false);
		createMoneyTransaction(empId, payableAmount, true);
		
		System.out.println("INFO : " + payableAmount + "give Extra to " + empId);
	}
	
	void updateEmployeeBalance(String id, String amt, boolean increse) {
		System.out.println("CALL : updateEmployeeBalance("+ id +", "+ amt +", "+ increse +")");
	    Connection con = MyConnection.getConnection();
	    String balance = getEmployeeBalance(id);
	    int totalBalance;
	    
	    // increase or decrease
	    
	    if(increse) {
	        totalBalance = Integer.parseInt(balance) + Integer.parseInt(amt);
	        System.out.println("INFO : Balance addtion");
	    }
	    else {
	    	totalBalance = Integer.parseInt(balance) - Integer.parseInt(amt);
	    	System.out.println("INFO : Balance substraction");
	    }
	    String query = "UPDATE employee_balance SET balance=" + totalBalance + " WHERE empid=+"+id;
	    Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("INFO : Balance updated");
		} catch (SQLException e) {
			System.out.println("INFO : Balance not updated");
			e.printStackTrace();
		}
	}
	
	private String getEmployeeBalance(String id) {
		System.out.println("CALL : getEmployeeBalance("+ id +")");
		Connection con = MyConnection.getConnection();
		Statement stmt;
		String salary = "";
		try {
			stmt = con.createStatement();
			String query = "select balance from employee_balance where empid='" + id + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) 
				salary = rs.getString("balance");
			System.out.println("INFO : Balance fetched"); 
		} catch (SQLException e1) {
			System.out.println("INFO : Balance not fetched"); 
			e1.printStackTrace();
		}  
		return salary;
	}
	
	private void createMoneyTransaction(String id, String amt, boolean extra) {
		System.out.println("createMoneyTransaction("+ id+", "+amt+", "+extra+")");  
		
		TimeZone tzone = TimeZone.getTimeZone("Asia/Kolkata");
	    TimeZone.setDefault(tzone);
	    
	    Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current date of the day using Date  - : " + formattedDate);
        
        String query;
        
        try {
	    	query = "INSERT INTO transaction(empid, amount, time, extra) VALUES (?, ?, ?, ?)";
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	        preparedStmt.setString (1, id);
	        preparedStmt.setString (2, amt);
	        preparedStmt.setString (3, formattedDate);
	        
		    if(extra) {
		    	preparedStmt.setString (4, "y");
		    }
		    else {
		    	preparedStmt.setString (4, "n");
		    }
	        preparedStmt.execute();
	        System.out.println("INFO : "+amt+" Salary or extra pay to " + id +" at "+formattedDate);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("INFO : Salary not pay");
		}
	}
}
