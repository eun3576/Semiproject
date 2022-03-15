<%@page import="dto.Inquiry"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<% List<Inquiry> list = (List)request.getAttribute("list"); %>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

function pwtest(inquiry_no, password){
	
	var inputpw = prompt("비밀번호를 입력하세요")
	
	if(password == inputpw){
		location.href="/inquiry/detail?inquiry_no="+inquiry_no;
	} else {
		alert("비밀번호가 틀렸습니다.")
	}
}

</script>

<style type="text/css">


#submenu{
	text-align:center;
	line-height:40px;
	background:#78CCC8;
	height:150px;
}

th{
	text-align:center;
}

a{
	color:black;
}

a:hover{
	text-decoration:none;
	color:blue;
}

</style>

<div class="container text-center">
<br><br><br>

<div class="col-xs-offset-11 col-xs-1 btn-xs" onclick="location.href='/inquiry/write'"><button>문의하기</button></div>

<br><br>

<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3>
<span><a href="/notice/list">공지사항</a></span><br>
<span><strong><a href="/inquiry/list">1:1질문</a></strong></span>
</div>

<div id="list" class="col-xs-10">

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>제목</th>
	<th>작성자</th>
	<th>날짜</th>
</tr>

<%	for(int i=0; i<list.size(); i++) { %>
<tr onclick="pwtest(<%=list.get(i).getInquiry_no() %>, <%=list.get(i).getPassword()%>);">
	<td><%=list.get(i).getTitle() %></td>
	<td><%=list.get(i).getId() %></td>
	<td><%=list.get(i).getWrite_date() %></td>
</tr>
<%	} %>

</table>

</div>

</div>

<%@ include file="./layout/inquiryPaging.jsp" %>










</body>
</html>
