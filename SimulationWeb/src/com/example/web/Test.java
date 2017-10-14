package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class Test extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
	        throws ServletException, IOException {  
	  
	    response.setContentType("application/json");  
	    PrintWriter out = response.getWriter(); 
	    JSONObject obj = new JSONObject();
	    String n=request.getParameter("NodeBs");  
	    String p=request.getParameter("RNCs"); 
	    String q=request.getParameter("SGSNs");  
	    
	    
	    
	    
	    
	    System.out.println(obj);
	    out.print( new FisrtClass().execute(Integer.valueOf(n),Integer.valueOf(p),Integer.valueOf(q)));
	     
	 
	   
	}
}
