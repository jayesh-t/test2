package com.jayeshtajane.salarymanager.modal.attendence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jayeshtajane.salarymanager.modal.MyConnection;

public class SaveAttendence {
	private static Connection con = MyConnection.getConnection();
	private String newAttendence = null;
	
	public String formatAttendence(String attendence) {
		System.out.println("INFO : Attendence before formated = " + attendence);
		//char[] arr = attendence.toCharArray();
		String[] arr = attendence.split(",");
		attendence = "";
		System.out.print("ATTENDENCE = ");
		for( String s : arr)
			System.out.print(s);
		System.out.println();
		// loop for removing the extra commas from attendence
		for(int i = 0; i < arr.length; i++) {
			if(! arr[i].isEmpty()) 
				attendence += arr[i] + ",";
		}
		System.out.println("INFO : Attendence after formated = " + attendence);
		System.out.println("INFO : Attendence formated"); 
		return attendence;
	}
	
	public void save(String attendence, String attendneceDate) {
		System.out.println("INFO : Attendence from client = " + attendence);
		attendence = formatAttendence(attendence); 
		if(checkAttendence(attendneceDate)) {
			System.out.println("INFO : Previous attendence found");
			update(attendence, attendneceDate);
			System.out.println("INFO : Attendence from server = " + newAttendence);
			updateBalance(attendence, newAttendence); 
			return;
		}
		System.out.println("INFO : Previous not attendence found");
		insert(attendence, attendneceDate);
		updateBalance(attendence); 
	}
	private boolean checkAttendence(String attendneceDate) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			String query = "select * from attendence where date='" + attendneceDate + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				newAttendence = rs.getString("attendence");
				return true;
			}
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

	        System.out.println("INFO : Attendence updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Attendence not updated");
		}   
	}
	
	private void updateBalance(String nAttendence, String oAttendence) { // client   server
		System.out.println("CALL : updateBalance(" + nAttendence + ", " + oAttendence + ")");
		
		String salary = "";
		
		if(nAttendence.length() > 0) {
			System.out.println("INFO : if 1");
			if(oAttendence.length() > 0) { 
				System.out.println("INFO : \tif 2");
				String[] oldAttendence = oAttendence.split(",");
				String[] newAttendence = nAttendence.split(",");
				
			    for(int i=0; i < newAttendence.length; i++) {
			    	System.out.println("INFO : \t\tfor 3");
			        boolean isPresent = false;
			        String id = newAttendence[i];
		
			        for(int j = 0; j < oldAttendence.length; j++) {
			        	System.out.println("INFO : \t\t\tfor 4");
			            if(newAttendence[i].equals(oldAttendence[j])) {
			            	System.out.println("INFO : \t\t\t\tif 5");
			                isPresent = true;
			                break;
			            }
			        }
			        if(! isPresent) {
			        	System.out.println("INFO : \t\tfor 6");
			        	if(! id.equals("")) {
			        		System.out.println("INFO : \t\t\tfor 7");
				            salary = getEmployeeSalary(id);
				            updateEmployeeBalance(id, salary, true);
			        	}
			        }
			    }

			    for(int i = 0; i < oldAttendence.length; i++) {
			    	System.out.println("INFO : \t\tfor 8");
			        boolean isPresent = false;
			        String id = oldAttendence[i];
		
			        for(int j = 0; j < newAttendence.length; j++) {
			        	System.out.println("INFO : \t\t\tfor 9");
			            if(newAttendence[j].equals(oldAttendence[i])) {
			            	System.out.println("INFO : \t\t\t\tfor 10");
			                isPresent = true;
			                break;
			            }
			        }
			        if(! isPresent) {
			        	System.out.println("INFO : \t\t\tfor 11");
			        	if(! id.equals("")) {
			        		System.out.println("INFO : \t\t\t\tfor 12");
				            salary = getEmployeeSalary(id);
				            updateEmployeeBalance(id, salary, false);
			        	}
			        }
			    }
			}
			else {
				String[] newAttendence = nAttendence.split(",");
				for(int i = 0; i < newAttendence.length; i++) {
			    	System.out.println("INFO : \t\tfor 13");
			        String id = newAttendence[i];
		
			        if(! id.equals("")) {
		        		System.out.println("INFO : \t\t\t\tfor 12");
			            salary = getEmployeeSalary(id);
			            updateEmployeeBalance(id, salary, true);
		        	}
			    }
			}
		}
		else if(oAttendence.length() > 0) {
			String[] oldAttendence = oAttendence.split(",");
			for(int i = 0; i < oldAttendence.length; i++) {
		    	System.out.println("INFO : \t\tfor 13");
		        String id = oldAttendence[i];
	
		        if(! id.equals("")) {
	        		System.out.println("INFO : \t\t\t\tfor 12");
		            salary = getEmployeeSalary(id);
		            updateEmployeeBalance(id, salary, false);
	        	}
		    }
		}
	}
	
	private void updateBalance(String nAttendence) {
		System.out.println("CALL : updateBalance(" + nAttendence + ")");
		
		String salary = "";
		
		if(nAttendence != "" || nAttendence != null) {
			String[] newAttendence = nAttendence.split(",");
		    for(int i=0; i < newAttendence.length; i++) {
		        String id = newAttendence[i];
	            salary = getEmployeeSalary(id);
	            updateEmployeeBalance(id, salary, true);
		    }
		}
	}
	
	private String getEmployeeSalary(String id) {
		con = MyConnection.getConnection();
		Statement stmt;
		String salary = "";
		try {
			stmt = con.createStatement();
			String query = "select salary from employee where empid='" + id + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) 
				salary = rs.getString("salary");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		return salary;
	}
	
	private String getEmployeeBalance(String id) {
		System.out.println("CALL : getEmployeeBalance("+ id +")");
		con = MyConnection.getConnection();
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
			// TODO Auto-generated catch block
			System.out.println("INFO : Balance not fetched"); 
			e1.printStackTrace();
		}  
		return salary;
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
	    // Query for update balance
	    String query = "UPDATE employee_balance SET balance=" + totalBalance + " WHERE empid=+"+id;
	    Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("INFO : Balance updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("INFO : Balance not updated");
			e.printStackTrace();
		}
	    // Executing the query
	}

}
