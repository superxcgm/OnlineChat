<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>找回密码 - OnlineChat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body class="background-gray">
	<header id="header">
		<div>OnlineChat</div>
	</header>
	<div class="banner banner-text center">
		Welcome Back
	</div>
	<div id="body">
		<div style="padding-left: 20px;">
			<a href="/" class="link-primary">&lt; 想起密码？ 去登陆</a>
		</div>
		<div class="background-white round box-shadow" style="width: 35%; margin: 0px auto; padding: 30px;">
			<div class="text-warning text-info">可能的提示消息</div>
			<div style="padding-bottom: 40px;">
				<form action="#" method="post">
					<div class="form-ele">用户名</div>
					<input type="text" name="username" class="textbox textbox-block">
					<div class="form-ele">绑定的邮箱</div>
					<div style="position: relative;">
						<input type="text" name="email" class="textbox textbox-block">
						<button style="position: absolute; right:0px;top:0px;" class="btn btn-primary">发送验证码</button>
					</div>
					<div class="form-ele">验证码</div>
					<input type="text" name="checkcode" class="textbox textbox-block">
					<input type="submit" value="找回密码" class="btn btn-primary btn-block">
				</form>
			</div>
		</div>
	</div>
</body>
</html>