package xcservlet.user;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Login extends HttpServlet
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

		String username = request.getParameter("username");		
		String password = request.getParameter("password");

		XCUser userRaw = new XCUser(username, "", "", "", 1); /* raw  */
		request.setAttribute("userRaw", userRaw);
		String strError = Checker.check(username, 0); /* not aduit argument, just judge is null ? */
		if(strError != null){
			pError(request, response, "用户名" + strError);
			return ;
		}
		XCUser xcUser  = XCUser.find(XCUser.FIND_BY_NAME, username);
		if(xcUser == null)
		{
			pError(request, response, "用户名不存在!");
			return ;
		}
		if(!xcUser.checkPwd(password)){
			pError(request, response, "密码错误!");
			return ;
		}
		session.setAttribute("cUser", xcUser);
		out.println("登录成功!");
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser != null)
		{
			PrintWriter out = response.getWriter();
			out.print("您已经成功登录!");
			return ;
		}
		XCUser userRaw = new XCUser("", "", "", "", 1); /* raw */
		request.setAttribute("userRaw", userRaw);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/login.jsp");
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