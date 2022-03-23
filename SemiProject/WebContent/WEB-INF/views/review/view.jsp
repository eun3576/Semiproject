<%@ page import="dto.Review" %>
<%@ page import="dto.UserInfo" %>
<%@ page import="dto.Attachment" %>
<%@ page import="dto.ReviewComment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% Review viewReview = (Review) request.getAttribute("viewReview"); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<% Attachment attach = (Attachment) request.getAttribute("attach"); %>
<% List<ReviewComment> reviewComment = (List) request.getAttribute("reviewComment"); %>
<% String sessionNick = (String) session.getAttribute("usernick"); %>

<script type="text/javascript">

$(document).ready(function () {
	
	//List 버튼을 눌렀을 때
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/review/list");
	})
	
	//Update 버튼을 눌렀을 때
	$("#btnUpdate").click(function() {
		<%if (sessionNick != null && sessionNick.equals(userInfo.getNickname())) { %>
			$(location).attr("href", "<%=request.getContextPath() %>/review/update?review_no=<%=viewReview.getReview_no() %>");
		<% } else { %>
			alert("본인이 작성한 글만 수정 가능합니다!!")
			history.go(0);
		<% } %>
	})
	
	//Delete 버튼을 눌렀을 때 
	$("#btnDelete").click(function() {
		<%if (sessionNick != null && sessionNick.equals(userInfo.getNickname())) { %>
		if (confirm("게시글을 삭제 하시겠습니까?")) {
			$(location).attr("href", "<%=request.getContextPath() %>/review/delete?review_no=<%=viewReview.getReview_no() %>");
		}
		<% } else { %>
			alert("본인이 작성한 글만 삭제 가능합니다!!")
			history.go(0);
		<% } %>
	})
	
	//댓글 삭제 버튼 (X)처리 
	$("#commentDelBtn").click(function() {
		if (confirm("댓글을 삭제 하시겠습니까?")) {
			$("#commentDel").submit();
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

#commentList, #commentPost {
	background-color: #f2dede;
	padding: 12px;
	border-radius: 5px 5px; 
}

#commentInfo {
	border-top: 1px solid #cfc9c9;
	margin-top: 5px;
	display: inline-block;
	width: 100%;
}

#commentContent {
	float: left;
}

#commentDate {
	float: right;
}

</style>

<div class="container">

<h1 align="center">자세히 보기</h1>
<hr>

<table class="table" style="border: 1px solid #dddddd;">

<tr>
	<th class="danger">ReviewNumber</th><td colspan="1"><%=viewReview.getReview_no() %></td>
	<th class="danger">Title</th><td colspan="3"><%=viewReview.getTitle() %></td>
</tr>  
       
<tr>   
	<th class="danger">Nickname</th><td colspan="1"><%=userInfo.getNickname() %></td>
	<th class="danger">Symptom</th><td colspan="1"><%=userInfo.getSymptom() %></td>
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
	<td colspan="6">
	
	<!-- 첨부파일 -->
	<div>
	
	<%	if( attach != null ) { %>
	<a href="<%=request.getContextPath() %>/upload/<%=attach.getStored_img() %>"
	 download="<%=attach.getOrigin_img() %>">
		<%=attach.getOrigin_img() %>
	<img alt="이미지를 불러올 수 없음" src="<%=request.getContextPath() %>
		/upload/<%=attach.getStored_img()%>" width="100%" height="100%">
	</a>
	<br><br>
	<%	} %>
	
	<%=viewReview.getContent() %>
	
	</div>
	
	</td>
</tr>

<tr>

</tr>
</table>

<br>
<div class="text-center">
	<button id="btnList" class="btn btn-primary">List</button>
	<button id="btnUpdate" class="btn btn-success">Update</button>
	<button id="btnDelete" class="btn btn-danger">Delete</button>
</div>
<hr>

<div>

		
		<!-- 로그인 세션 정보가 있을 때 댓글 작성란 활성화 -->
		<% if(!"".equals(sessionNick) && !(sessionNick == null)) { %>
	<div id="commentPost">
		<strong style="font-size: 20px;">댓글 입력</strong>
	<%-- 		<form action="/review/view?review_no=<%=viewReview.getReview_no() %>" method="post"> --%>
		<form action="#" method="post">
			
			<input type="hidden" name="reviewNo" value="<%=viewReview.getReview_no() %>">
			<%=sessionNick %>: <input type="text" name="commentText" placeholder="댓글을 입력 하세요.">
			
			<button id="commentWrite" class="btn-xs">등록</button>
		</form>
	</div>
	<hr>
		<% } %>
	
	
	<div id="commentList">
	<strong style="font-size: 20px;">댓글 목록</strong>
	<small>&nbsp;&nbsp;&nbsp;댓글 수(<%=reviewComment.size() %>)</small><br>
		<% for(int i=0; i<reviewComment.size(); i++) { %>
			<div id="commentInfo">
			
			<div id="commentContent">
				<small>
				<%=reviewComment.get(i).getNickname() %>: <%=reviewComment.get(i).getComment_text() %><br>
				</small>
				<br>
			</div>
				
			<div id="commentDate">
<%-- 		작성 날짜: <%=reviewComment.get(i).getComment_date() %> --%>
<%-- 		수정 날짜: <%=reviewComment.get(i).getComment_update() %><br> --%>
			<small><%=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(reviewComment.get(i).getComment_date()) %></small>
					
			<% if (sessionNick != null && sessionNick.equals(reviewComment.get(i).getNickname())) { %>
			<span id="commentDelBtn" style="color:red; font-weight: bold; cursor: pointer;">X</span>
			<form id="commentDel" action="#" method="post">
				<input type="hidden" name="commentNo" value="<%=reviewComment.get(i).getComment_no() %>">
			</form>
			<% } %>	
			</div>
			
			</div>
		<% } %>
	</div>
	
	<hr>
	
</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

