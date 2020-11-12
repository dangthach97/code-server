package com.academy.jtravel.Utils;

import java.sql.Connection;
import java.util.ResourceBundle;

public class ConnectionUtils {
	private static Connection conn;

	// Singleton
	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = MySQLConnectionUtils.getMySQLConnection();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
			}
		}
		return conn;
	}
 
}
