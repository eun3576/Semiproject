<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.ManagerNotice"%>
<%@ include file="../managerLayout/header.jsp" %>

<%
	ManagerNotice viewNotice = (ManagerNotice) request.getAttribute("viewNotice");
%>

<script type="text/javascript">
$(document).ready(function(){
	//목록
	$("#btnList").click(function(){
		$(location).attr("href","<%=request.getContextPath() %>/managernotice/list");
	})
	
	//수정
	$("#btnUpdate").click(function(){
		$(location).attr("href", "<%=request.getContextPath() %>/notice/update?noticeNo=<%=viewNotice.getNoticeNo() %>");
	})
	
	//삭제
	$("#btnDelete").click(function(){
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/notice/delete?noticeNo=<%=viewNotice.getNoticeNo() %>");
		}
		
	})
})
</script>

<div class="container">

<h1>공지사항 상세보기</h1>

<hr>
<table class="table table-borderd">
	
<tr>
<td class="info">글번호</td><td colspan="3"><%=viewNotice.getNoticeNo() %></td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3"><%=viewNotice.getTitle() %></td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3"><%=viewNotice.getWriteDate() %></td>
</tr>

<tr>
<td class="info">관리자번호</td><td colspan="3"><%=viewNotice.getManagerNo() %></td>
</tr>

<tr>
<td class="info">내용</td><td colspan="5"><%=viewNotice.getContent() %></td>
</tr>

</table>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../managerLayout/footer.jsp" %>


    