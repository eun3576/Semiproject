<%@ page import="dto.Review" %>
<%@ page import="dto.UserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% Review viewReview = (Review) request.getAttribute("viewReview"); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>

<script type="text/javascript">

$(document).ready(function () {
	
	//List 버튼을 눌렀을 때
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/review/list");
	})
	
	//Update 버튼을 눌렀을 때
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/review/update?review_no=<%=viewReview.getReview_no() %>");
	})
	
	//Delete 버튼을 눌렀을 때 
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/review/delete?review_no=<%=viewReview.getReview_no() %>");
		}
	})
	
})

</script>

<div class="container">

<h1 align="center">자세히 보기</h1>
<hr>

<table class="table" style="1px solid border;">

<tr>
	<th class="danger">ReviewNumber</th><td colspan="5"><%=viewReview.getReview_no() %></td>
</tr>

<tr>
	<th class="danger">Title</th><td colspan="1"><%=viewReview.getTitle() %></td>
	<th class="danger">Write_Date</th><td colspan="1"><%=viewReview.getWriteDate() %></td>
	<th class="danger">Update_Date</th><td colspan="1"><%=viewReview.getUpdateDate() %></td>
</tr>

<tr>
	<th class="danger">Nickname</th><td><%=userInfo.getNickname() %></td>
	<th class="danger">Views</th><td colspan="3"><%=viewReview.getViews() %></td>
</tr>

<tr>
	<th class="danger" colspan="6">Content</th>
</tr>

<tr>
	<td colspan="6"><%=viewReview.getContent() %></td>
</tr>

</table>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">List</button>
	<button id="btnUpdate" class="btn btn-success">Update</button>
	<button id="btnDelete" class="btn btn-danger">Delete</button>
</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

