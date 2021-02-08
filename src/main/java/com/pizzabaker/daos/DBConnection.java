package com.pizzabaker.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection GetConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost/baker";
		Properties props = new Properties();
		props.setProperty("user","ashiq");
		props.setProperty("password","");
		//props.setProperty("ssl","true");
		return DriverManager.getConnection(url, props);
	}
}
