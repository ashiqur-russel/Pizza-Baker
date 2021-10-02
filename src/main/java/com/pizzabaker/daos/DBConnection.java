package com.pizzabaker.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection GetConnection() throws SQLException {
		String url = "postgres://firdpiwendrlya:55b9cfb159a525e340ee83c9a78dccbfbec072b10764333064ba9dcb35e9a5aa@ec2-54-217-195-234.eu-west-1.compute.amazonaws.com:5432/dd3sv0muqj3dds
";
		Properties props = new Properties();
		props.setProperty("user","firdpiwendrlya");
		props.setProperty("password","55b9cfb159a525e340ee83c9a78dccbfbec072b10764333064ba9dcb35e9a5aa");
		//props.setProperty("ssl","true");
		return DriverManager.getConnection(url, props);
	}
}
