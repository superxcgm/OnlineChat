<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>新朋友 - OnlineChat</title>
	<!-- 网站名使用常量 -->
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<style>
		body{
			position: relative;
			height: 100%;
		}
		html{
			height: 100%;
		}
		div{
			position: absolute;
			top: 40%;
			left: 15%;
			width: 60%;
		}
		form{
			position: relative;
		}
		input.btn{
			position: absolute;
			top: 0px;
			right: 0px;
			width: 100px;
		}
	</style>
</head>
<body class="background-gray">
	<div>
		<form action="" method="post">
			<input type="text" placeholder="昵称／ID／邮箱" class="textbox textbox-block" name="keyword">
			<input type="submit" class="btn btn-primary" value="查找">
		</form>
	</div>
</body>
</html>