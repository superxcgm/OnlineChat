package xcservlet.user;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class UserInfo extends HttpServlet
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

		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");		
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		String repassword = request.getParameter("repassword");

		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		XCUser userRaw = new XCUser(xcUser.getUser_id(), xcUser.getUser_name(), "", email, nickname, 1); /* raw  */
		request.setAttribute("userRaw", userRaw);

		String strError = Checker.check(nickname, Checker.CH_LEN, 2, 7);
		if(strError != null){
			pError(request, response, "昵称" + strError);
			return ;
		}
		strError = Checker.check(email, Checker.CH_MAIL);
		if(strError != null){
			pError(request, response, strError);
			return ;
		}
		if(!xcUser.checkPwd(password)){
			pError(request, response, "密码不正确！修改失败！");
			return ;	
		}
		if(newpassword != null && newpassword.length() > 0){
			strError = Checker.check(newpassword, Checker.CH_LEN, 6, 20);
			if(strError != null){
				pError(request, response, "新密码" + strError);
				return ;
			}
			if(!newpassword.equals(repassword)){
				pError(request, response, "两次输入的密码必须一致!");
				return ;	
			}
			xcUser.setUser_pwd(newpassword);
		}
		xcUser.setUser_nick(nickname);
		xcUser.setUser_email(email);
		xcUser.update();
		pError(request, response, "更新成功！重新登录生效！"); /* successful instead */
		
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
		request.setAttribute("userRaw", xcUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/userinfo.jsp");
		dispatcher.forward(request, response);
	}
	private void pError(HttpServletRequest request,
		HttpServletResponse response, String strErr) throws IOException, ServletException
	{
		// PrintWriter out = response.getWriter();
		// out.print(strErr);
		request.setAttribute("strErr", strErr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/userinfo.jsp");
		dispatcher.forward(request, response);
	}
}