package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposit
 */
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cn=DbConnection.connect();
		String ac=request.getParameter("accnum");
		int deposit=Integer.parseInt(request.getParameter("deposit"));
		int bal=0;
		int newbalance=0;
		 
		try {
			PreparedStatement stmt=cn.prepareStatement("select *from bank where Accnum=?");
			stmt.setString(1,ac);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				bal=rs.getInt(8);
			}
			newbalance=bal+deposit;
			PreparedStatement st=cn.prepareStatement("update bank set balance=? where Accnum=?");
			st.setInt(1, newbalance);
			st.setString(2,ac);
			int i=st.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("services.html");
			}
			else{
				response.sendRedirect("Deposit.html");
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
