package xcservlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class SendMsg extends HttpServlet
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
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser == null)
		{
			response.sendRedirect("/");
			return ;
		}
		String type = request.getParameter("type");
		String user_recv = request.getParameter("user_recv");
		String text = request.getParameter("text");

		if(user_recv == null || ((user_recv = user_recv.trim()) != null && user_recv.length() == 0) )
			return ;
		XCUser user = XCUser.find(XCUser.FIND_BY_ID, user_recv);
		if(user == null)
			return ;
		if(text == null || ((text = text.trim()) != null && text.length() == 0) )
			return ;
		if(type.equals("1")){
			xcUser.sendUserMsg(user, text);
		}
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
	}
}