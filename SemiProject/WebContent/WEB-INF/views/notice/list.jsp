<%@page import="dto.ManagerNotice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../managerLayout/header.jsp" %>

<%
	List<ManagerNotice> noticeList = (List) request.getAttribute("noticeList");
%>

<script type="text/javascript">
$(document).ready(function(){
	
	// 글쓰기
	$("#btnWrite").click(function(){
		location.href="/notice/write"
	})
})

</script>

<ul	class="nav">
	<li><a href="/">메인페이지</a></li>
	
	<li><a href="/managernotice/list">공지사항관리</a></li>

	<li><a href="/reviewcomment/list">리뷰댓글관리</a></li>
	
	<li><a href="/inquiryanswer/list">1:1문의관리</a></li>
	
	<li><a href="#">배너관리</a></li>
	
</ul>

<div class="container">

<h1>공지사항 목록</h1>

<!-- 검색창 -->
<div class="pull-right">

  <form class="search-form">

       <input type="search" placeholder="제목을 입력하세요" required autocomplete="on" id="search" name="search">

       <input type="submit" value="검색" onclick="clickEvent();">

  </form>
</div>

<script>
function clickEvent() {
	var text = document.getElementById("search");
	$(location).attr("href", "<%=request.getContextPath() %>/notice/list?search=" + text);

}
</script>
<!--  -->

<table class="table table-striped table-hover table-condensed">

<tr class="success">
	<th>글번호</th>
	<th>제목</th>
	<th>작성일</th>
	<th>작성자</th>
</tr>

<%	for(int i=0; i<noticeList.size(); i++) { %>
<tr>
	<td><%=noticeList.get(i).getNoticeNo() %></td>
	<td><a href="./view?noticeNo=<%=noticeList.get(i).getNoticeNo() %>"><%=noticeList.get(i).getTitle()%></a></td>
	<td><%=noticeList.get(i).getWriteDate() %></td>
	<td>관리자<%=noticeList.get(i).getManagerNo() %></td>

</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-right">
	<button id="btnWrite" class="btn btn-primary">글쓰기</button>
</div>

</div><!-- .container -->

<%@ include file="../notice/paging.jsp" %>

<%@ include file="../managerLayout/footer.jsp" %>


