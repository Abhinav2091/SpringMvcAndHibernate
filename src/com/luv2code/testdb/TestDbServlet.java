package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//set up user connection
		String user="root";
		String pass="root";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true";
		String driver="com.mysql.jdbc.Driver";
		
		//get db connection
		
		//try(Connection con =DriverManager.getConnection(jdbcUrl,user,pass))
		try
		{
			PrintWriter out = response.getWriter();
			
			out.println("connected to jdbc:"+jdbcUrl);
			
			Class.forName(driver);
			
			Connection con =DriverManager.getConnection(jdbcUrl,user,pass);
			
			if(con != null)
				System.out.println("Sucess!!!");
			
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
