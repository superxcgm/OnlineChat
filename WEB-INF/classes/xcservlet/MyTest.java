package xcservlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyTest extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		session.invalidate();
	}
}