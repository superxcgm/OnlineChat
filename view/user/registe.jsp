<!-- <%@ page contentType="text/html;charset=utf-8" %> -->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册 - OnlineChat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body class="background-gray">
	<header id="header">
		<div>OnlineChat</div>
	</header>
	<div class="banner banner-text center">
		I Would Like to Talk!
	</div>
	<div id="body">
		<div>
			<a href="/index.html" class="link-primary">&lt; 已有帐号？ 去登陆</a>
		</div>
		<div class="background-white round box-shadow" style="width: 400px; margin: 0px auto; padding: 30px;">
			<div class="text-warning text-info">可能的提示消息</div>
			<div style="padding-bottom: 40px;">
				<form action="" method="post">
					<div class="form-ele">用户名</div>
					<input type="text" name="username" class="textbox textbox-block" placeholder="仅限字母、数字，6-20位">
					<div class="form-ele">密码</div>
					<input type="password" name="password" class="textbox textbox-block" placeholder="6-20位">
					<div class="form-ele">重复密码</div>
					<input type="password" name="repassword" class="textbox textbox-block">
					<div class="form-ele">邮箱</div>
					<input type="text" name="email" class="textbox textbox-block">
					<input type="submit" value="注册" class="btn btn-primary btn-block">
				</form>
			</div>
		</div>
	</div>
</body>
</html>