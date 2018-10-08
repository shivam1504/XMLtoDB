package com.netlink.driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Driver 
{
	private String driver = "com.mysql.jdbc.Driver";
	private String connectionUrl = "jdbc:mysql://localhost:3306/";
	private String database = "student";
	private String userid = "shivam";
	private String password = "password";
	private Connection con=null;
	
	public Driver()
	{
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(connectionUrl+database, userid, password);
			} 
		catch (Exception e) {
			e.printStackTrace();
		}

		finally
		{	try
		{
			con.close();  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}  
	public java.sql.Statement returnStatement()throws Exception
	{
		return con.createStatement();	
	}
}
