package com.jayeshtajane.salarymanager.modal.history;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeshtajane.salarymanager.modal.MyConnection;

public class History {
	private ArrayList<Data> history;
	private String json;
	public String getHistory() {
		System.out.println("CALL : getHistory()");  
		history = new ArrayList<Data>();
		
		try {
			Connection con = MyConnection.getConnection();
			
			Statement stmt1;
			stmt1 = con.createStatement();
			String query = "select * from transaction order by time DESC";
			ResultSet transactionResult = stmt1.executeQuery(query);
			
			while(transactionResult.next()) {
				System.out.println("INFO : Transaction data found");
				Data data = new Data();
				
				Statement stmt2;
				stmt2 = con.createStatement();
				query = "select ename from employee where empid = " + transactionResult.getString("empid");
				ResultSet employeeResult = stmt2.executeQuery(query);
				
				while(employeeResult.next()) {
					data.setEmpName(employeeResult.getString("ename"));
					data.setAmount(transactionResult.getDouble("amount"));
					if(transactionResult.getString("extra").equals("y"))
						data.setExtra(true);
					else
						data.setExtra(false);
					String date[] = formatDate(transactionResult.getString("time"));
					data.setDate(date[0]); 
					data.setTime(date[1]); 
					history.add(data);
					System.out.println(data);
				}
				
				ObjectMapper om = new ObjectMapper();
				json = om.writeValueAsString(history);
			}
	        System.out.println("INFO : Transaction data geted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO : Transaction data not geted");
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	private String[] formatDate(String timestamp) {
		String ts[] = timestamp.split(" ");
		String t[] = ts[1].split(":");
		ts[1] = t[0] + ":" + t[1];
		return ts;
	} 
	
	private class Data {
		private String empName;
		private int historyId;
		private boolean isExtra;
		private double amount;
		private String date;
		private String time;
		
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public int getHistoryId() {
			return historyId;
		}
		public void setHistoryId(int historyId) {
			this.historyId = historyId;
		}
		public boolean isExtra() {
			return isExtra;
		}
		public void setExtra(boolean isExtra) {
			this.isExtra = isExtra;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
	}

}
