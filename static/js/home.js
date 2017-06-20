// init
var screenHeight;
var screenWidth;
var friend_count = -1;
var mysplit = "[superxc_split]";
var mylineSplit = "[superxc_line_split]";
var msg_queue = new Array();
window.setInterval(messageloop, 3000);
window.setInterval(refreshFriendList, 3000); 
String.prototype.format= function(){
   var args = arguments;
   return this.replace(/\{(\d+)\}/g,function(s,i){
     return args[i];
   });
};
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
var msg_item = '<div class="msg-item" onclick="{0}">\
						<input type="hidden" class="userType" value="{1}">\
						<input type="hidden" class="userId" value="{2}">\
						<input type="hidden" class="msg_time" value="{3}" />\
						<div class="portrait">\
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">\
						</div>\
						<div class="right-panel">\
							<div class="nickname">\
								{4}\
							</div>\
							<div class="text-content">\
								{5}\
							</div>\
						</div>\
					</div>';
var contact_item = '<div class="contact-item"  onclick="showProfile({0})">\
						<input type="hidden" class="userType" value="0">\
						<input type="hidden" class="userId" value="{0}">\
						<div class="portrait">\
							<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">\
						</div>\
						<div class="nickname">\
							{1}\
						</div>\
					</div>';
$(function(){
	screenHeight = document.body.scrollHeight;
	screenWidth = document.body.scrollWidth;
	// $("#left").height(screenHeight);	
	// $("#right").height(screenHeight);	
	// $("#right").outerWidth(screenWidth - $("#left").outerWidth());
	$("#cUserInfo>.option").click(function(){
		if($("#cUserInfo>.option>.option-panel").css("display") == "none")
			$("#cUserInfo>.option>.option-panel").css("display", "block");
		else
			$("#cUserInfo>.option>.option-panel").css("display", "none");
	});
	$("#menu .list-vertical>li").click(function(){
		$("#menu .list-vertical>li").removeClass("active");
		$(this).addClass("active");
		$("#mix-list>div").css("display", "none");
		var index = $("#menu .list-vertical>li").index(this);
		if(index == 0){
			$("#msg-list").css("display", "block");
		}else if(index == 1){
			$("#contact-list").css("display", "block");
		}else if(index == 2){
			$("#room-list").css("display", "block");
		}
	});
	// $("#right>.msg-content").height(screenHeight - $("#right>.msg-sent").height() - $("#right>.msg-username").height());
	// window.setTimeout(messageloop(), 1000);
	messageloop();
	refreshFriendList();
});
function showProfile(uid)
{
	$("#right").html('<iframe src="/user/profile?id=' + uid + '" frameborder="0" style="width:100%; height: 100%;" name="right-frame"></iframe>');	
}
function sendMsg(obj)
{
	var userType = $("#right>.userType").val();
	var userId = $("#right>.userId").val();
	var content = trim($("#right>.msg-sent>textarea").val());

	// var beforeHtml = $("#right>.msg-content").html();
	// $("#right>.msg-content").html(beforeHtml + msgItem);
	if(content.length == 0)
		return;
	var tmp = 'u' + userId;
	var username = trim($("#right>.msg-username").html() + "");
	msg_queue[tmp] = msg_queue[tmp] + "0" + mysplit + content + mylineSplit;
	$("#right>.msg-sent>textarea").val("");
	/* update msg-list */
	if($("#msg-list>.msg-item>.userId[value='" + userId + "']").length == 0){
		$("#msg-list").html(msg_item.format('chat(\'' + userId + '\',\'' + username + '\')', '', userId, "", username, content) + $("#msg-list").html());
	}else{
		$("#msg-list>.msg-item>.userId[value='" + userId + "']").siblings(".right-panel").children(".text-content").html(content);
	}
	/* 1 stands for friend msg */
	$.post('/sendMsg', {type: "1", user_recv: userId, text: content});
	refreshMsgContent(userId);
}
function refreshMsgContent(uid)
{
	var msgItem = '<div class="msg-item">\
					<div class="float-{0}">\
						<img src="/static/img/soccor-80-80.jpg" alt="" class="portrait">\
					</div>\
					<div class="msg-detail float-{0} round">{1}</div>\
				</div>';
	/* {0} left or right */
	var tmp = 'u' + uid;
	// console.log(msg_queue[tmp]);
	if(msg_queue[tmp] == undefined){
		msg_queue[tmp] = '';
	}
	// console.log(msg_queue[tmp]);
	var resultStr = '';
	var line = (msg_queue[tmp]).split(mylineSplit);
	for(i = 0; i < line.length; ++i){
		if(!isNull(line[i])){
			var ele = line[i].split(mysplit);
			if(ele[0] == "0"){
				resultStr = resultStr + msgItem.format("right", ele[1]);
			}else{
				resultStr = resultStr + msgItem.format("left", ele[1]);
			}
		}
	}
	$("#right>.msg-content").html(resultStr);
}
function chat(userId, userName)
{
	// var userId = $(obj).children(".userId").val(); 
	// var userName = trim($(obj).find(".nickname").text());
	// console.log(userType);
	// console.log(userId);
	// console.log(userName);
	var rightArea = '\
			<input type="hidden" class="userType" value="" />\
			<input type="hidden" class="userId" value="" />\
			<div class="msg-username center">\
				启宏与蛙\
			</div>\
			<div class="msg-content">\
				\
			</div>\
			<div class="msg-sent">\
				<textarea></textarea>\
				<button class="btn btn-primary" style="width: 80px;" onclick="sendMsg(this)">发送</button>\
			</div>';
	$("#right").html(rightArea);
	// $("#right>.msg-content").height(screenHeight - $("#right>.msg-sent").height() - $("#right>.msg-username").height());
	$("#right>.msg-username").text(userName);
	// $("#right>.userType").val(userName);
	$("#right>.userId").val(userId);

	refreshMsgContent(userId);

	$("#right>.msg-sent>textarea").keydown(function(event){
		/* Enter */
		// console.log(event.keyCode);
		if(event.keyCode == 13){
			sendMsg();
		}
	});
};
function modifyInfo()
{
	$("#right").html('<iframe src="/user/userinfo" frameborder="0" style="width:100%; height: 100%;" name="right-frame"></iframe>');
}
function newfriend()
{
	$("#right").html('<iframe src="/newfriend/search" frameborder="0" style="width:100%; height: 100%;" name="right-frame"></iframe>');	
}
function confirm_Add(obj, targetId, user_name)
{
	$(obj).attr("onclick", "");
	layer.msg(user_name + '[' + targetId + ']请求添加您为好友？', {
	  time: 0 //不自动关闭
	  ,btn: ['接受', '拒绝']
	  ,yes: function(index){
	    layer.close(index);
	    // console.log("发送一个ajax，同意请求");
	    $.get("/newfriend/confirm?id=" + targetId);
	  }
	});
}
function messageloop()
{
	$.get("/getMsg", function(data){
		if(!isNull(data)){
			var list = data.split(mylineSplit);
			console.log(list);
			for(i = 0; i < list.length; ++i){
				if(!isNull(list[i])){
					console.log(list[i]);
					var ele = (list[i]).split(mysplit);
					switch(ele[2]){
						case "2": /* system message add friend */
							$("#msg-list").html(msg_item.format('confirm_Add(this,' + ele[0] + ',\''+ ele[1] +'\')', '', '', ele[4],'系统消息', "新朋友请求") + $("#msg-list").html());
							break;
						case "1":
							/* if chat already exist, just modify msg, otherwise insert into it */
							if($("#msg-list>.msg-item>.userId[value='" + ele[0] + "']").length == 0){
								$("#msg-list").html(msg_item.format('chat(\'' + ele[0] + '\',\'' + ele[1] + '\')', '', ele[0], ele[4], ele[1], ele[3]) + $("#msg-list").html());
							}else{
								$("#msg-list>.msg-item>.userId[value='" + ele[0] + "']").siblings(".right-panel").children(".text-content").html(ele[3]);
							}
							var tmp = 'u' + ele[0];
							// console.log(msg_queue[tmp]);
							if(msg_queue[tmp] == undefined){
								msg_queue[tmp] = '';
							}
							msg_queue[tmp] = msg_queue[tmp] + "1" + mysplit + ele[3] + mylineSplit;
							refreshMsgContent(ele[0]);
							break;
					}
				}
			}
		}
	});
}
function refreshFriendList()
{
	$.get("/getFriendCount", function(data){
		var cnt = parseInt(data);
		if(cnt != friend_count){
			/* friend should sorted in server */
			$.get("/getFriendList", function(data){
				console.log(data);
				var list = data.split(mylineSplit);
				console.log(list);
				$("#contact-list").html("");
				for(i = 0; i < list.length; ++i){
					if(!isNull(list[i])){
						console.log(list[i]);
						var ele = (list[i]).split(mysplit);
						$("#contact-list").html($("#contact-list").html() + contact_item.format(ele[0], ele[1]));
					}
				}
				friend_count = cnt;
			});
		}
	});
}
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}