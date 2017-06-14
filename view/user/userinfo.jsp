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
	<title>用户信息 - OnlineChat</title>
	<!-- 网站名使用常量 -->
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<style>
		div#embed{
			padding: 20px;
			width: 500px;
		}
		table{
			width: 100%;
		}
		table td{
			font-size: 18px;
		}
	</style>
</head>
<body class="background-gray">
	<div id="embed">
		<div class="text-warning text-info"><%= strErr %></div>
		<form action="" method="post">
			<div>
				<table>
					<tr>
						<td width="20%">用户ID</td>
						<td width="80%"><jsp:getProperty name="userRaw" property="user_id" /></td>
					</tr>
					<tr>
						<td>用户名</td>
						<td><jsp:getProperty name="userRaw" property="user_name" /></td>
					</tr>
				</table>
			</div>
			<div class="form-ele">昵称</div>
			<div>
				<input type="text" name="nickname" class="textbox textbox-block" value='<jsp:getProperty name="userRaw" property="user_nick" />' placeholder="2到7个字符">
			</div>
			<div class="form-ele">邮箱</div>
			<div>
				<input type="text" name="email" class="textbox textbox-block" value='<jsp:getProperty name="userRaw" property="user_email" />'>
			</div>
			<div class="form-ele">密码</div>
			<div>
				<input type="password" name="password" class="textbox textbox-block" placeholder="必填">
			</div>
			<div class="form-ele">新密码</div>
			<div>
				<input type="password" name="newpassword" class="textbox textbox-block" placeholder="如果不需要修改密码，无须填写">
			</div>
			<div class="form-ele">重复密码</div>
			<div>
				<input type="password" name="repassword" class="textbox textbox-block">
			</div>
			<input type="submit" value="保存" class="btn btn-primary btn-block">
		</form>
	</div>
</body>
</html>