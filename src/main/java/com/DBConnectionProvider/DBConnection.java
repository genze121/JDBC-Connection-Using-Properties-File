package com.DBConnectionProvider;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

	private static String DRIVER_NAME;
	static {
		try {
			DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
			Class.forName(DRIVER_NAME).newInstance();
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.getMessage();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static Connection getConnection() {

		Connection connection = null;	
		try {
			File file = new File("src/main/resources/config.properties");
			FileInputStream fileInputStream = new FileInputStream(file);
			if (file.exists()) {
				Properties properties = new Properties();
				properties.load(fileInputStream);

				String URL = properties.getProperty("url");
				String USERNAME = properties.getProperty("username");
				String PASSWORD = properties.getProperty("password");

				if (connection == null) {
					connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					System.out.println("Connection established successfully......");
				} else {
					System.out.println("Something went wrong.Please try again.....");
				}

			} else {
				System.out.println("Error while performing file operations.....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void main(String[] args) {

		DBConnection.getConnection();
	}

}
