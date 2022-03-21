<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<!-- 공지사항 리스트 가져오기 -->
<% List<Notice> list = (List)request.getAttribute("list");%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">

#submenu{
	text-align:center;
	line-height:40px;
	border:1px solid #ccc;
	height:180px;
}

hr{
	border:1px solid #ccc;
}

th{
	text-align:center;
}

a{
	color:black;
}

a:hover{
	text-decoration:none;
	color:black;
}


</style>

<div class="container text-center">
<br><br><br><br><br>
<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3><hr>
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

<!-- 공지사항 목록 보여주기 -->
<%	for(int i=0; i<list.size(); i++) { %>
<!-- 해당 공지사항 행을 누르면 해당 공지사항 번호를 상세보기 서블릿으로 전달 후 이동 -->
<tr onclick="location.href='/notice/detail?notice_no=<%=list.get(i).getNotice_no() %>'">
	<td><%=list.get(i).getNotice_no() %></td>
	<td><%=list.get(i).getTitle() %></td>
	<td><%=list.get(i).getWrite_date() %></td>
</tr>
<%	} %>

</table>

</div>

</div>
<a href="#header" style="cursor:pointer;background:url('../resources/img/arrows_circle_top_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:46%;margin-top:-20px;z-index:100;"></a>
<a href="#footer" style="cursor:pointer;background:url('../resources/img/arrows_bottom_circle_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:54%;margin-top:-20px;z-index:100;"></a>
<%@ include file="./layout/noticePaging.jsp" %>
<%@ include file="../layout/footer.jsp" %>
