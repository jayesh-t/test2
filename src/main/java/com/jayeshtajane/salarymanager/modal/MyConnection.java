package com.jayeshtajane.salarymanager.modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
	private static Connection connection = connect();
	
	public static Connection getConnection() {
		try {
			if(connection == null || connection.isClosed()) {
				connection = connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	private static Connection connect() {
		// Connection properties
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "";
		String dbname = "employee_salary_manager";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("INFO : MySql driver loaded");
			connection = DriverManager.getConnection( url + dbname, username, password);
			System.out.println("INFO : Connection successful");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR : MySql not driver loaded");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ERROR : Connectivity problem");
			e.printStackTrace();
		}
		return connection;
	}
}
