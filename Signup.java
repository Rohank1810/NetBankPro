package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cn=DbConnection.connect();
		int id;
		String Accountnumber=request.getParameter("accnum");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String city=request.getParameter("city");
		String age=request.getParameter("age");
		String actype=request.getParameter("actype");
		int balance=Integer.parseInt(request.getParameter("bal"));
		
		try {
			PreparedStatement pstmt=cn.prepareStatement("insert into bank values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,0);
			pstmt.setString(2,Accountnumber);
			pstmt.setString(3,name);
			pstmt.setString(4,pass);
			pstmt.setString(5,city);
			pstmt.setString(6,age);
			pstmt.setString(7,actype);
			pstmt.setInt(8,balance);
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("Login.html");
			}
			else{
				response.sendRedirect("Signup.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
