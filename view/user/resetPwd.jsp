<!-- <%@ page contentType="text/html;charset=utf-8" %> -->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>密码重置 - OnlineChat</title>
	<link rel="stylesheet" type="text/css" href="static/css/xc_basic.css">
	<link rel="stylesheet" href="static/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body class="background-gray">
	<header id="header">
		<div>OnlineChat</div>
	</header>
	<div class="banner banner-text center">
		Welcome Back
	</div>
	<div id="body">
		<div class="background-white round box-shadow" style="width: 400px; margin: 0px auto; padding: 30px;">
			<div class="text-warning text-info">可能的提示消息</div>
			<div style="padding-bottom: 40px;">
				<form action="#" method="post">
					<div class="form-ele">新密码</div>
					<input type="password" name="password" class="textbox textbox-block" placeholder="6-20位">
					<div class="form-ele">重复密码</div>
					<input type="password" name="repassword" class="textbox textbox-block">
					<input type="submit" value="重设密码" class="btn btn-primary btn-block">
				</form>
			</div>
		</div>
	</div>
</body>
</html>