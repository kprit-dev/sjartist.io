package com.art.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.*;
import java.sql.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
        PrintWriter out = response.getWriter();
	    
	    
	    
	    try (Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
		    	Statement st=con.createStatement();){
		    	
	    	String name=request.getParameter("name");
		    String email=request.getParameter("email");
		    String message=request.getParameter("message");
		    Mail.composeMail(email, message);
		  
		    System.out.print(name+" "+email+" "+message);
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			String query="insert into artist values('"+ name +"','"+email+"','"+message+"')";
		
			int chk=st.executeUpdate(query);
			if(chk==1)
			out.println("Details inserted Successfully!");
			else
			out.println("Failed to insert employee Datails");
		    	
			response.sendRedirect("http://localhost:8081/SJArtist/");
			 
		    }
		    catch(Exception e) {
		    	System.out.println(e);
		    }
	}

}
