package com.netlink.xml.jdbc;

import java.io.File;

import javax.xml.parsers.*;

import java.sql.*;

import org.w3c.dom.*;	

public class Import 
{
	public static void main(String args[]) throws Exception
	{
		//creating Document Builder
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=dbf.newDocumentBuilder();

		//loading the Driver and Creating Connection to DB
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netlink","shivam","password");  
		PreparedStatement ps=con.prepareStatement("insert into movies(movieName,movieCategory,movieIndustry)values (?,?,?)"); 

		//create a document from file
		Document doc=builder.parse(new File("..//JDBC//xml//Movie.xml"));

		//Extracting root element
		Element root=doc.getDocumentElement();
		System.out.println("Root Element:"+root.getNodeName());	
		
		//Extracting Data 
		String arr[]={"Action","Comedy","Horror"},str,parent;
		
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<doc.getElementsByTagName(arr[i]).getLength();j++)
			{   
				parent=doc.getElementsByTagName(arr[i]).item(j).getParentNode().getNodeName();
				str=doc.getElementsByTagName(arr[i]).item(j).getTextContent();
				
				System.out.println("Parent Node:"+parent+" Category:"+arr[i]+" Movies:"+str);
				
					ps.setString(1,str);
					ps.setString(2,arr[i]);	
					ps.setString(3,parent);
					ps.executeUpdate();
			}
		}	 
		con.close();  
}
}

