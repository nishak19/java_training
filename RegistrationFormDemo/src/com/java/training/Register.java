package com.java.training;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
  
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext servletContext = config.getServletContext();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(servletContext.getInitParameter("connectionURL"),
					servletContext.getInitParameter("userName"), servletContext.getInitParameter("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email=request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String dbUserNm="";
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		boolean usernameExists=false;
			try {
				PreparedStatement ps1 = connection.prepareStatement("select username from customer where username=?");
				ps1.setString(1, username);
				 ResultSet r1=ps1.executeQuery();
				 if(r1.next()) 
		         {
		           dbUserNm =  r1.getString(1);
		           System.out.println(dbUserNm);
		           System.out.println(firstname);
			}} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
	           if(!username.equals(dbUserNm)) 
	           {	
	        	   PreparedStatement ps;
				try {
					ps = connection.prepareStatement("insert into customer" + 
							" ( firstname, lastname,email, username, password) values" + 
							" (?, ?, ?, ?, ?);");
					ps.setString(1, firstname);
		   			ps.setString(2, lastname);
		   			ps.setString(3, email);
		   			ps.setString(4, username);
		   			ps.setString(5, password);
		        	   int executeUpdate=ps.executeUpdate();
		        	   if (executeUpdate == 1) {
			   				RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerSuccess.jsp");
			   				requestDispatcher.forward(request, response);
			   			} else {
			   				RequestDispatcher requestDispatcher = request.getRequestDispatcher("resgistrationForm.jsp");
			   				requestDispatcher.forward(request, response);
			   			}
			        	   ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	   		
	        	 
	           }
	           else {
	        	   HttpSession req=request.getSession();
	        	   String message = "Username already exists,please choose another";
	        	   req.setAttribute("error", message);
	              RequestDispatcher requestDispatcher = request.getRequestDispatcher("registrationForm.jsp");
	   			  requestDispatcher.forward(request, response);
	   			 writer.append("<h1>Username already exists,please choose another</h1>");
	              usernameExists = true;
	           }
	           writer.close();
	         }
	         
	      
		}
	
	


