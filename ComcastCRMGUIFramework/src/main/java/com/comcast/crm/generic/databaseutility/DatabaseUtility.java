package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection con;
	public void getDbconnection(String url,String username, String password) throws Throwable {
		try {
		Driver d=new Driver();
		DriverManager.registerDriver(d);
	 con = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void closeDbConnection() throws Throwable {
		try {
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	public ResultSet executeSelectQuery(String query) throws Throwable{
		ResultSet result=null;
		try
		{
		
		Statement statement = con.createStatement();
		result = statement.executeQuery(query);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
	
	public int executeNonSelectQuery(String query) throws Throwable {
		int result=0;
		try
		{
			Statement statement = con.createStatement();
			result = statement.executeUpdate(query);
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
		public void getDbconnection() throws Throwable {
			try {
			Driver d=new Driver();
			DriverManager.registerDriver(d);
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
	}


