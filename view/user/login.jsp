<%@ page contentType="text/html;charset=utf-8" %>
<%
	String strErr = (String)request.getAttribute("strErr");
	if(strErr == null)
		strErr = "";
%>
<jsp:useBean id="userRaw" type="xcbean.XCUser" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录 - OnlineChat</title>
	<!-- 网站名使用常量 -->
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body class="background-gray">
	<header id="header">
		<div>OnlineChat</div>
	</header>
	<div class="banner banner-text center">
		Talk. Talk. Talk!
	</div>
	<div id="body">
		<div class="background-white round box-shadow" style="width: 35%; margin: 0px auto; padding: 30px;">
			<div class="text-warning text-info"><%= strErr %></div>
			<div style="padding-bottom: 40px;">
				<form action="" method="post">
					<div class="form-ele">用户名／邮箱</div>
					<div style="position:relative;">
						<input type="text" name="username" class="textbox textbox-block" value='<jsp:getProperty name="userRaw" property="user_name" />'>
						<i class="fa fa-user-o" style="position:absolute; top:13px; left:10px;"></i>
					</div>
					<div class="form-ele">密码</div>
					<div style="position:relative;">
						<input type="password" name="password" class="textbox textbox-block">
						<i class="fa fa-lock" style="position:absolute; top:13px; left:10px;"></i>
					</div>
					<input type="submit" value="登录" class="btn btn-primary btn-block">
				</form>
			</div>
			<div style="padding-bottom: 30px;">
				<p><a href="/user/registe" class="link-primary">&gt; 请从这里注册新帐户</a></p>
				<p><a href="/user/forgetPwd" class="link-primary">&gt; 如果您忘记密码 请点这里</a></p>
			</div>
			<div>
				<div class="order" style="padding-bottom: 20px;">  
				   <span style="white-space:pre"></span><span class="line"></span>  
				   <span style="white-space:pre"></span><span class="txt">社交帐号登录</span>  
				   <span style="white-space:pre"></span><span class="line"></span>  
				</div>
				<ul class="list-vertical">
					<li style="width:33%"><a href="#"><i class="fa fa-qq fa-5x"></i></a></li>
					<li style="width:33%"><a href="#"><i class="fa fa-wechat fa-5x"></i></a></li>
					<li style="width:33%"><a href="#"><i class="fa fa-github fa-5x"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>