<%@page import="dto.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Chat chat = (Chat)request.getAttribute("chat"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chat</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

setInterval(ajaxGetServerList,2500);

function ajaxToServer(){
	console.log("ajaxGetServerList() called")
	
	//전달 파라미터 저장
	var params = "chatId="+chatId.value+"&chatRcontent="+chatRcontent.value+"&chatroom="+chatroom.value;
	console.log(params)
	
	sendRequest("GET", "/chat/inout", params, ajaxFromServer);
}

function ajaxFromServer(){
	console.log("ajaxFromServer() called")
	if(httpRequest.readyState==4){//done, 응답완료
		if(httpRequest.status==200){//정상응답
			console.log("ajax정상응답")
			
			//정상 응답 처리
			appendResult();
		
			//정상 응답처리 후 입력창 초기화
			chatRcontent.value=""
		}else{
			console.log("요청/응답 오류")
		}
	}
}

//정상 응답 후 응답데이터를 처리하는 함수
function appendResult(){
	//응답데이터가 저장이 되면 다시 ajax를 호출해 채팅 목록을 가져온다
	if(httpRequest.responseText=="true"){
		ajaxToServerForList();
	}else{
		alert("전송이 실패하였습니다. 다시 입력해주세요")
	}
}

function ajaxToServerForList(){
	console.log("ajaxToServerForList() called")
	
	//전달 파라미터 저장
	var params = "chatroom="+chatroom.value;
	console.log(params)
	
	sendRequest("POST", "/chat/inout", params, ajaxFromForListServer);
}

function ajaxFromForListServer(){
// 	console.log("ajaxFromForListServer() called")
	if(httpRequest.readyState==4){//done, 응답완료
		if(httpRequest.status==200){//정상응답
			console.log("ajax정상응답")
			
			//정상 응답 처리
			appendForListResult();
		
			//정상 응답처리 후 입력창 초기화
			chatRcontent.value=""
		}else{
			console.log("요청/응답 오류")
		}
	}
}

//정상 응답 후 응답데이터를 처리하는 함수
function appendForListResult(){
	//응답데이터가 저장이 되면 다시 ajax를 호출해 채팅 목록을 가져온다
	chatContent.innerHTML = httpRequest.responseText;
	
	var chatting = document.getElementById("chatContentBox");
	chatting.scrollTop = chatting.scrollHeight;
}


function ajaxGetServerList(){
// 	console.log("ajaxGetServerList() called")
	
	//전달 파라미터 저장
	var params = "chatroom="+chatroom.value;
// 	console.log(params)
	
	sendRequest("POST", "/chat/inout", params, ajaxListFromServer);
}

function ajaxListFromServer(){
// 	console.log("ajaxListFromServer() called")
	if(httpRequest.readyState==4){//done, 응답완료
		if(httpRequest.status==200){//정상응답
// 			console.log("ajax정상응답")
			
			//정상 응답 처리
			appendListResult();
		
		}else{
			console.log("요청/응답 오류")
		}
	}
}

//정상 응답 후 응답데이터를 처리하는 함수
function appendListResult(){
	//응답데이터가 저장이 되면 다시 ajax를 호출해 채팅 목록을 가져온다
	chatContent.innerHTML = httpRequest.responseText;
}

$(document).ready(function(){
	$("#chatRcontent").keydown(function(e){
		if(e.keyCode == 13){//엔터키
			ajaxToServer();
		}
	})
})
</script>

<style type="text/css">
div{margin:0;padding:0;}
</style>

</head>
<body>
<div id="chat_container" style="width:650px;height:800px;border:1px solid #eee;box-sizing: border-box;">
<div class="chat_title" style="clear:both;width:650px;height:50px;background:#ccc;clear:both;">
<span style="float:left;font-size:16px;font-weight: bold;margin-top:10px;margin-left:10px;">채팅방</span>
<button class="btn btn-danger" style="width:70px;height:30px;float: right;margin-top:10px;margin-right:10px;" onclick="location.href='/chat/manager'">목록</button>
</div>
<div id="chatContentBox" style="width:650px;height:600px;overflow:auto;">
<div id="chatContent" style="clear:both;margin:10px 20px;height:60px;">
</div>
</div>
<div class="messageBox" style="margin:50px auto 0;width:610px;clear:both;">
<input type="text" value="관리자" style="display:none;" name="chatId" id="chatId">
<input type="text" value="<%=chat.getChatRoom()%>" style="display:none;" name="chatroom" id="chatroom">
<textarea rows="3"class="form-control" style="width:500px;resize:none;float:left;" placeholder="메시지를 입력해주세요(100bytes)" name="chatRcontent" id="chatRcontent"></textarea>
<button class="btn btn-default" style="float:left;margin-left:10px;width:100px;height:70px;font-size:16px;" onclick="ajaxToServer();">전송</button>
</div>
</div>
</body>
</html>