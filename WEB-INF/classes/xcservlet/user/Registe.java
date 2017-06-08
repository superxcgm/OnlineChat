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

		HttpSession session = request.getSession(true);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");

		XCUser userRaw = new XCUser(username, password, email, "", 1); /* raw */
		/* data audit */
		String strError = Checker.check(username, Checker.CH_LEN | Checker.CH_ALNUM, 6, 20);
		if(strError != null){
			session.setAttribute("userRaw", userRaw);
			pError(request, response, "用户名" + strError);
			return ;
		}

		strError = Checker.check(password, Checker.CH_LEN, 6, 20);
		if(strError != null){
			session.setAttribute("userRaw", userRaw);
			pError(request, response, "密码" + strError);
			return ;
		}

		if(repassword != password){
			session.setAttribute("userRaw", userRaw);
			pError(request, response, "两次输入的密码必须一致!");
			return ;
		}

		strError = Checker.check(email, Checker.CH_MAIL);
		if(strError != null){
			session.setAttribute("userRaw", userRaw);
			pError(request, response, strError);
			return ;
		}

		/* */
		XCUser xcUser = new XCUser(username, password, email);
		xcUser.update();
		/* 注册成功啦！ */
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
	private void pError(HttpServletRequest request,
		HttpServletResponse response, String strErr) throws IOException, ServletException
	{
		response.sendRedirect("/");
	}
}