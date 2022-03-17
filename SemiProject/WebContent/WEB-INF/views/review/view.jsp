<%@ page import="dto.Review" %>
<%@ page import="dto.UserInfo" %>
<%@ page import="dto.Attachment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% Review viewReview = (Review) request.getAttribute("viewReview"); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<% Attachment attach = (Attachment) request.getAttribute("attach"); %>

<!-- bootstrap 추가 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


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

<style type=text/css>

th {
	text-align:center;
	width: 20%;
}

</style>

<div class="container">

<h1 align="center">자세히 보기</h1>
<hr>

<table class="table">

<tr>
	<th class="danger">ReviewNumber</th><td colspan="1"><%=viewReview.getReview_no() %></td>
	<th class="danger">Title</th><td colspan="3"><%=viewReview.getTitle() %></td>
</tr>  
       
<tr>   
	<th class="danger">Nickname</th><td colspan="1"><%=userInfo.getNickname() %></td>
	<th class="danger">Symptom</th><td colspan="1"><%=userInfo.getSympton() %></td>
	<th class="danger">Views</th><td colspan="1"><%=viewReview.getViews() %></td>
</tr>

<tr>
	<th class="danger">Write_Date</th><td colspan="2"><%=viewReview.getWriteDate() %></td>
	<th class="danger">Update_Date</th><td colspan="2"><%=viewReview.getUpdateDate() %></td>
</tr>

<tr>
	<th class="danger" colspan="6" align="center">Content</th>
</tr>

<tr>
	<td colspan="6"><%=viewReview.getContent() %></td>
</tr>

<tr>

</tr>
</table>

<!-- 첨부파일 -->
<div>
<%	if( attach != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=attach.getStored_img() %>"
 download="<%=attach.getOrigin_img() %>">
	<%=attach.getOrigin_img() %>
<img alt="이미지를 불러올 수 없음" src="<%=request.getContextPath() %>
	/upload/<%=attach.getStored_img()%>" width="200" height="200">
</a>
<%	} %>
</div>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">List</button>
	<button id="btnUpdate" class="btn btn-success">Update</button>
	<button id="btnDelete" class="btn btn-danger">Delete</button>
</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

