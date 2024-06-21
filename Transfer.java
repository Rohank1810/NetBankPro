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
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cn=DbConnection.connect();
		String ac=request.getParameter("accnum");
		int amt=Integer.parseInt(request.getParameter("amt"));
		String ac1=request.getParameter("accnum1");
		int bal=0;
		int newbalance=0;
		int bal1=0;
		int newbalance1=0;
		 
		try {
			PreparedStatement stmt=cn.prepareStatement("select *from bank where Accnum=?");
			stmt.setString(1,ac);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				bal=rs.getInt(8);
			}
			newbalance=bal-amt;
			PreparedStatement st=cn.prepareStatement("update bank set balance=? where Accnum=?");
			st.setInt(1, newbalance);
			st.setString(2,ac);
			st.executeUpdate();
			
			//add to other
			
				PreparedStatement stmtt=cn.prepareStatement("select *from bank where Accnum=?");
				stmtt.setString(1,ac1);
				ResultSet rss=stmtt.executeQuery();
				while(rss.next())
				{
					bal1=rss.getInt(8);
				}
				newbalance1=bal1+amt;
				PreparedStatement stt=cn.prepareStatement("update bank set balance=? where Accnum=?");
				stt.setInt(1, newbalance1);
				stt.setString(2,ac1);
				int i=stt.executeUpdate();
				if(i>0)
				{
					response.sendRedirect("services.html");
				}
				else
				{
					response.sendRedirect("Transfer.html");
				}
			    
			}
			catch (SQLException e) {
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
