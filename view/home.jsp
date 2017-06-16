<%@ page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="cUser" type="xcbean.XCUser" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>OnlineChat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/xc_basic.css">
	<link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/static/css/home.css">
	<script src="/static/js/jquery-3.2.1.min.js"></script>
	<script src="/static/js/home.js"></script>
	<script src="/static/layer/layer.js"></script>
</head>
<body>
	<div id="outer">
		<div id="left" class="background-dark">
			<div id="logo">
				OnlineChat
			</div>
			<div id="cUserInfo">
				<div class="portrait">
					<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait-large">
				</div>
				<div class="nickname" onclick="modifyInfo()">
					<jsp:getProperty name="cUser" property="user_nick" /><!-- 昵称 -->	
				</div>
				<div class="option">
					<i class="fa fa-bars fa-2x"></i>
					<div class="option-panel round">
						<ul>
							<li onclick="newfriend()"><i class="fa fa-user-plus"></i> 新朋友</li>
							<li><i class="fa fa-home"></i> 进入聊天室</li>
							<li><i class="fa fa-folder"></i> 文件管理</li>
							<li><a href="/user/logout"><i class="fa fa-power-off"></i> 退出</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="search-panel">
				<div style="position: absolute; left:4px; color: black;"><i class="fa fa-search"></i></div>
				<input type="text" class="textbox" placeholder="Search">
			</div>
			<div id="menu">
				<ul class="list-vertical center">
					<li style="width:33%" class="active"><i class="fa fa-comment-o fa-2x"></i></li>
					<li style="width:33%"><i class="fa fa-users fa-2x"></i></li>
					<li style="width:33%"><i class="fa fa-home fa-2x"></i></li>
				</ul>
			</div>
			<div id="mix-list">
				<div id="msg-list">
					<!-- <div class="msg-item" onclick="chat(this)">
						<input type="hidden" class="userType" value="0">
						<input type="hidden" class="userId" value="1024">
						<div class="portrait">
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">
						</div>
						<div class="right-panel">
							<div class="nickname">
								启宏与蛙
							</div>
							<div class="text-content">
								在吗?在吗?在吗?在吗?在吗?在吗?在吗?在吗?在吗?在吗?
							</div>
						</div>
					</div> -->
					
				</div>
				<div id="contact-list" style="display: none;">
					<!-- <div class="contact-item"  onclick="chat(this)">
						<input type="hidden" class="userType" value="0">
						<input type="hidden" class="userId" value="1024">
						<div class="portrait">
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">
						</div>
						<div class="nickname">
							启宏与蛙
						</div>
					</div> -->
					
				</div>
				<div id="room-list"  style="display: none;">
					<div class="room-item"  onclick="chat(this)">
						<input type="hidden" class="userType" value="1">
						<input type="hidden" class="userId" value="100035">
						<div class="portrait">
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">
						</div>
						<div class="nickname">
							不落皇旗
						</div>
					</div>
					<div class="room-item"  onclick="chat(this)">
						<input type="hidden" class="userType" value="1">
						<input type="hidden" class="userId" value="100047">
						<div class="portrait">
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">
						</div>
						<div class="nickname">
							布洛格
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="right" class="background-gray">
			<!-- <input type="hidden" class="userType" value="" />
			<input type="hidden" class="userId" value="" />
			<div class="msg-username center">
				启宏与蛙
			</div>
			<div class="msg-content">
				
			</div>
			<div class="msg-sent">
				<textarea></textarea>
				<button class="btn btn-primary" style="width: 80px;" onclick="sendMsg(this)">发送</button>
			</div> -->
		</div>
	</div>
</body>
</html>