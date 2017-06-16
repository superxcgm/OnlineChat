package xcservlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class GetFriendCount extends HttpServlet
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
		PrintWriter out = response.getWriter();
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser == null)
		{
			response.sendRedirect("/");
			return ;
		}
		int cnt = xcUser.getFriendCount();
		out.print(cnt);
	}
}