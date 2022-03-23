<%@page import="dto.Chat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<Chat> cList = (List)request.getAttribute("cList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[관리자] 채팅방 목록</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
setInterval(pageReload,60000);
function pageReload(){
	location.reload();
}
</script>
</head>
<body>
<div class="chatWrap" style="width:650px;height:800px;border:1px solid #ccc;box-sizing: border-box;">
<div class="chat_title" style="clear:both;width:650px;height:70px;background:#ccc;clear:both;">
<h3 style="margin:0;padding-top:10px;">현재 채팅방</h3>
<span>1분마다 새로고침됩니다(F5버튼으로 새로고침 가능)</span>
</div>
<%for(int i=0;i<cList.size();i++){ %>
<form action="/chat/manager" method="post">
<p>채팅방명: <%=cList.get(i).getChatRoom() %></p>
<input type="text" value="<%=cList.get(i).getChatRoom() %>" name="chatroom" style="display:none">
<button class="btn btn-primary">입장</button>
<hr>
</form>
<%} %>
</div>
</body>
</html>