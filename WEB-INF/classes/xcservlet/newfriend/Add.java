package xcservlet.newfriend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Add extends HttpServlet
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
		String str;
		/* 不能添加自己为好友 */
		if(!xcUser.isFriend(user)){
			if(MsgRecord.systemMsg(MsgRecord.SYS_ADD_FRIEND, "" + xcUser.getUser_id(), "" + user.getUser_id()))
				str = "请求已经成功发出，等待对方回应。";
			else
				str = "请求未能成功发出，未知错误。";
		}else{
			str = "添加失败！已经是朋友了，无需重复添加";
		}
		request.setAttribute("str", str);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/newfriend/addResult.jsp");
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