package xcservlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Home extends HttpServlet
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

	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser == null){
			response.sendRedirect("/user/login");
			return ;
		}
		request.setAttribute("cUser", xcUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/home.jsp");
		dispatcher.forward(request, response);
	}
	private void pError(HttpServletRequest request,
		HttpServletResponse response, String strErr) throws IOException, ServletException
	{
		// PrintWriter out = response.getWriter();
		// out.print(strErr);
		request.setAttribute("strErr", strErr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/login.jsp");
		dispatcher.forward(request, response);
	}
}