package xcservlet.user;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Registe extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(true);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");

		XCUser userRaw = new XCUser(username, "", email, "", XCUser.DATA_RAW); /* raw */
		request.setAttribute("userRaw", userRaw);
		/* data audit */
		String strError = Checker.check(username, Checker.CH_LEN | Checker.CH_ALNUM, 6, 20);
		if(strError != null){
			pError(request, response, "用户名" + strError);
			return ;
		}

		strError = Checker.check(password, Checker.CH_LEN, 6, 20);
		if(strError != null){
			pError(request, response, "密码" + strError);
			return ;
		}

		if(!repassword.equals(password)){
			pError(request, response, "两次输入的密码必须一致!");
			return ;
		}

		strError = Checker.check(email, Checker.CH_MAIL);
		if(strError != null){
			pError(request, response, strError);
			return ;
		}

		// out.print(username + "<br />" + password + "<br />" + email);
		
		XCUser xcUser = new XCUser(username, password, email);
		boolean ans = xcUser.update();
		if(!ans){
			pError(request, response, xcUser.getStrErr());
			return ;
		}
		pError(request, response, "success");
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");
		// HttpSession session = request.getSession(true);

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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/registe.jsp");
		dispatcher.forward(request, response);
	}
	private void pError(HttpServletRequest request,
		HttpServletResponse response, String strErr) throws IOException, ServletException
	{
		// PrintWriter out = response.getWriter();
		// out.print(strErr);
		request.setAttribute("strErr", strErr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/registe.jsp");
		dispatcher.forward(request, response);
	}
}