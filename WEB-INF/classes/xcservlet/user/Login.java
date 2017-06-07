package xcservlet.user;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
			
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/login.jsp");
		dispatcher.forward(request, response);
	}
}