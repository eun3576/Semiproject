<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../layout/header.jsp" %>

<% List<Notice> list = (List)request.getAttribute("list");%>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


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
<br><br><br><br><br>
<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3>
<span><strong><a href="/notice/list">공지사항</a></strong></span><br>
<span><a href="/inquiry/list">1:1질문</a></span>
</div>

<div id="list" class="col-xs-10">

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>날짜</th>
</tr>

<%	for(int i=0; i<list.size(); i++) { %>
<tr onclick="location.href='/notice/detail?notice_no=<%=list.get(i).getNotice_no() %>'">
	<td><%=list.get(i).getNotice_no() %></td>
	<td><%=list.get(i).getTitle() %></td>
	<td><%=list.get(i).getWrite_date() %></td>
</tr>
<%	} %>

</table>

</div>

</div>

<%@ include file="./layout/noticePaging.jsp" %>










</body>
</html>