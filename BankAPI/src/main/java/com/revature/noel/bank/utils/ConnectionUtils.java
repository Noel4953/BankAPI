package com.revature.noel.bank.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection createConnection() {
		String url = System.getenv("mydbl");
		
		try {
			Connection conn = DriverManager.getConnection(url);
			// A factory - pass in string details for any type of database.
            // The DriverManager factory will give you back a connection
            // implementation specifically for Postgres.
			return conn;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
}
}