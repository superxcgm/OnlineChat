<%@ page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="user" type="xcbean.XCUser" scope="request" />
<%
	// boolean isFriend = (boolean)request.getParameter("isFriend");
	boolean isFriend = (Boolean)request.getAttribute("isFriend");

%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户信息 - OnlineChat</title>
	<!-- 网站名使用常量 -->
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<style>
		body, html{
			height: 100%;
			position: relative;
		}
		#outer{
			top: 25%;
			left: 20%;
			width:50%;
			position: absolute;
		}
	</style>
	<script>
		function addFriend(targetId)
		{
			window.location.href = "/newfriend/add?id=" + targetId;
		}
	</script>
</head>
<body class="background-gray">
	<!-- <%= request.getAttribute("isFriend") %> -->
	<div id="outer">
		<div class="portrait center">
			<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait-large" style="width:120px; height: 120px;">
		</div>
		<div class="center">
			<table style="margin: 0px auto;">
				<tr>
					<td>UID</td>
					<td><jsp:getProperty name="user" property="user_id" /></td>
				</tr>
				<tr>
					<td>昵称</td>
					<td><jsp:getProperty name="user" property="user_nick" /></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><jsp:getProperty name="user" property="user_email" /></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td></td>
				</tr>
			</table>
		</div>
		<%
			if(isFriend){
		%>
				<div class='center'>
					<button class='btn btn-primary btn-block' style='width: 50%' onclick='parent.chat("<jsp:getProperty name="user" property="user_id" />","<jsp:getProperty name="user" property="user_nick" />")'>发送信息<button>
				</div>
				<div class='center'>
					<button class='btn btn-warning btn-block' style='width: 50%'>删除</button>
				</div>
		<%
			}else{
				out.print("<div class='center'>" +
							"<button class='btn btn-primary btn-block' style='width: 50%' onclick='addFriend(" + user.getUser_id() + ")'>添加好友</button>" +
						"</div>");
			}
		%>
	</div>
</body>
</html>