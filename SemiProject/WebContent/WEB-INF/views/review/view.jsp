<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="dto.Review" %>
<%@ page import="dto.UserInfo" %>
<%@ page import="dto.Attachment" %>
<%@ page import="dto.ReviewComment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% Review viewReview = (Review) request.getAttribute("viewReview"); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<% Attachment attach = (Attachment) request.getAttribute("attach"); %>
<% List<ReviewComment> reviewComment = (List) request.getAttribute("reviewComment"); %>
<% String sessionNick = (String)session.getAttribute("usernick"); %>


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
		if( confirm("게시글을 삭제 하시겠습니까?") ) {
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

.table {
	table-layout:fixed;
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
	<th class="danger">Update_Date</th>
	<% if(viewReview.getUpdateDate() == null) { %>
	<td colspan="2"></td>
	<% } else { %>
	<td colspan="2">(수정됨) <%=viewReview.getUpdateDate() %></td>
	<% } %>	
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
<hr>

<div>

	<h1>댓글 들어갈 영역! 로그인을 하면 댓글 작성칸 활성화!</h1>
	
	<h1>댓글 작성!!</h1>
	
	<% if(!"".equals(sessionNick) && !(sessionNick == null)) { %>
<%-- 		<form action="/review/view?review_no=<%=viewReview.getReview_no() %>" method="post"> --%>
	<form action="#" method="post">
		
		<input type="hidden" name="reviewNo" value="<%=viewReview.getReview_no() %>">
		닉네임: <%=sessionNick %><br>
		작성할 내용: <input type="text" name="commentText">
		
		<button id="commentWrite" class="btn-xs">작성!</button>
	<% } %>
	</form>
	
	<hr>
	<% for(int i=0; i<reviewComment.size(); i++) { %>
		<small>닉네임: <%=reviewComment.get(i).getNickname() %><br>
<%-- 		작성 날짜: <%=reviewComment.get(i).getComment_date() %> --%>
		작성 날짜: <%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reviewComment.get(i).getComment_date()) %>
		수정 날짜: <%=reviewComment.get(i).getComment_update() %><br>
		댓글: <%=reviewComment.get(i).getComment_text() %></small>
		
		<% if (sessionNick != null && sessionNick.equals(reviewComment.get(i).getNickname())) { %>
		<form action="#" method="post">
			<input type="hidden" name="commentNo" value="<%=reviewComment.get(i).getComment_no() %>">
		<button id="commentDel" class="btn-xs">삭제</button><br>
		</form>
		<% } %>
		<hr>
	<% } %>

</div>


</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

