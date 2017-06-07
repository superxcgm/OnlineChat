package xcservlet.user;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Registe extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		// PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");	

		username
		password
		repassword
		email
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/registe.jsp");
		dispatcher.forward(request, response);
	}
}