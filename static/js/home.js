// init
$(function(){
	$("#left").height(document.body.scrollHeight);	
	$("#right").height(document.body.scrollHeight);	
	$("#right").outerWidth(document.body.scrollWidth - $("#left").outerWidth());
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
});