package com.java.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection=null;
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
		try {
			String firstname = "";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (null != username && null != password) {
				if (connection != null) {
					PreparedStatement ps = connection
							.prepareStatement("select * from customer where username=? and password=?");
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet resultSet = ps.executeQuery();
					while (resultSet.next()) {
						firstname = resultSet.getString(1);
					}
				}
			}
			//Setting to Session
			HttpSession session = request.getSession();
			if (firstname.length() != 0) {
				session.setAttribute("firstName", firstname);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginSuccess.jsp");
				requestDispatcher.forward(request, response);
			} else {
				session.setAttribute("error", "Invalid Login");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
				requestDispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	}


