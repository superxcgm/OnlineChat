package xcservlet.newfriend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Confirm extends HttpServlet
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
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser == null)
		{
			PrintWriter out = response.getWriter();
			response.sendRedirect("/");
			return ;
		}
		String sUid = (String)request.getParameter("id");
		if(sUid == null || ((sUid = sUid.trim()) != null && sUid.length() == 0))
			return ;
		XCUser user = XCUser.find(XCUser.FIND_BY_ID, sUid);
		if(user == null)
			return ;
		if(xcUser.getUser_id() == user.getUser_id())
			return ;
		Friend friend = new Friend(xcUser.getUser_id(), user.getUser_id());
		friend.update();
		friend = new Friend(user.getUser_id(), xcUser.getUser_id());
		friend.update();
		return ;
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