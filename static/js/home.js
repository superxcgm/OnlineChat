// init
var screenHeight;
var screenWidth;
$(function(){
	screenHeight = document.body.scrollHeight;
	screenWidth = document.body.scrollWidth;
	$("#left").height(screenHeight);	
	$("#right").height(screenHeight);	
	$("#right").outerWidth(screenWidth - $("#left").outerWidth());
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
});
function sendMsg(obj)
{
	var userType = $("#right>.userType").val();
	var userId = $("#right>.userId").val();
	var content = $("#right>.msg-sent>textarea").val();
	var msgItem = '\
				<div class="msg-item">\
					<div class="float-right">\
						<img src="../static/img/soccor-80-80.jpg" alt="" class="portrait">\
					</div>\
					<div class="msg-detail float-right round">' + content + '</div>\
				</div>';
				// <div class="msg-item">
				// 	<div class="float-left">
				// 		<img src="../static/img/soccor-80-80.jpg" alt="" class="portrait">
				// 	</div>
				// 	<div></div>
				// 	<div class="msg-detail float-left round">不在</div>
				// </div>
	var beforeHtml = $("#right>.msg-content").html();
	$("#right>.msg-content").html(beforeHtml + msgItem);
	$("#right>.msg-sent>textarea").val("");
}
function chat(obj)
{
	var userType = $(obj).children(".userType").val();
	var userId = $(obj).children(".userId").val(); 
	var userName = $(obj).find(".nickname").text().trim();
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
	$("#right>.msg-content").height(screenHeight - $("#right>.msg-sent").height() - $("#right>.msg-username").height());
	$("#right>.msg-username").text(userName);
	$("#right>.userType").val(userName);
	$("#right>.userId").val(userId);

	$("#right>.msg-sent>textarea").keydown(function(event){
		/* Enter */
		console.log(event.keyCode);
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