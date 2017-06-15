<%@ page contentType="text/html;charset=utf-8" %>
<%
	String[][] users = (String[][])request.getAttribute("users");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>新朋友 - OnlineChat</title>
	<!-- 网站名使用常量 -->
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/static/css/searchResult.css">
	<script>
		function profile(uid)
		{
			window.location.href = "/user/profile?id=" + uid;
		}
	</script>
</head>
<body class="background-gray">
	<div id="outer">
		<ul class="list-vertical">
			<%
				if(users == null){
					out.println("没有找到符合条件的用户！");	
				}else{
					String tmp = "<li>"+
									"<div class='user round' onclick='profile(%s)'>"+
										"<div class='portrait'>"+
											"<img src='/static/img/soccor-80-80.jpg' alt='' class='portrait'>"+
										"</div>"+
										"<div class='right-panel'>"+
											"<div>ID：%s</div>"+
											"<div>昵称：%s</div>"+
										"</div>"+
									"</div>"+
								"</li>";
					for(String[] user:users)
						out.print(String.format(tmp, user[0], user[0], user[1]));
				}
			%>
		</ul>
	</div>
</body>
</html>