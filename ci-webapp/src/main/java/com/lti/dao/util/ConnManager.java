package com.lti.dao.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

public class ConnManager {
	
	/*public static Connection connect() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Step 2. Establishing connection with the database
			String url = "jdbc:oracle:thin:@localhost:1522:OSE";
			String user = "system";
			String pass = "aAa343434";
			
			return DriverManager.getConnection(url, user, pass);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}

	}*/
	
	public static Connection connect() {
		try{
			Properties dbProps = new Properties();
			dbProps.load(ConnManager.class.getClassLoader().getResourceAsStream("dev-db.properties"));
			//dbProps.load(new FileReader("dev-db.properties"));
			Class.forName(dbProps.getProperty("driverName"));
			return DriverManager.getConnection(dbProps.getProperty("url"),dbProps.getProperty("user"),dbProps.getProperty("pass"));
		}
		catch(ClassNotFoundException | SQLException  | IOException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		}

	}
}
