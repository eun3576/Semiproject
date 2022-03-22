<%@ page import = "dto.ManagerReviewComment" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../managerLayout/header.jsp" %>    
    
<%
        	List<ManagerReviewComment> reviewCommentList = (List) request.getAttribute("reviewCommentList");
        %>

<script type="text/javascript">

  $(document).ready(function(){
	//삭제
	$("[data-btn='delete'").click(function(){
		if( confirm("댓글을 삭제하시겠습니까?") ) {
			
			var commentno = $(this).parent().parent().children().eq(0).text();
			console.log(commentno)
			$(location).attr("href", "<%=request.getContextPath() %>/reviewcomment/delete?CommentNo=" + commentno);
			
		}
		
	})
// 	//삭제
// 	$("#btnDelete").click(function(){
// 		if( confirm("댓글을 삭제하시겠습니까?") ) {
<%-- 			$(location).attr("href", "<%=request.getContextPath() %>/reviewComment/delete?CommentNo=<%=reviewCommentList.getCommentNo() %>"); --%>
// 		}
		
// 	})
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
<h1>리뷰댓글 목록</h1>

<!-- 검색창 -->
<div class="pull-right">

  <form class="search-form">

       <input type="search" placeholder="댓글내용을 입력하세요" required autocomplete="on" id="search" name="search">

       <input type="submit" value="검색" onclick="clickEvent();">

  </form>
</div>

<script>
function clickEvent() {
	var text = document.getElementById("search");
	$(location).attr("href", "<%=request.getContextPath() %>/reviewcomment/list?search=" + text);

}
</script>
<!--  -->

<table class="table table-striped table-hover table-condensed">
<tr class="success"> 
	<th>댓글번호</th>
	<th>유저번호</th>
	<th>댓글내용</th>
	<th>작성날짜</th>
	<th>수정날짜</th>
	<th>리뷰번호</th>
	<th>삭제</th>
</tr>

<%  for(int i=0; i<reviewCommentList.size(); i++) { %>
<tr>
	<td><%=reviewCommentList.get(i).getCommentNo() %></td>
	<td><%=reviewCommentList.get(i).getUserNo() %></td>
	<td><%=reviewCommentList.get(i).getCommentText() %></td>
	<td><%=reviewCommentList.get(i).getCommentDate() %></td>
	<td><%=reviewCommentList.get(i).getCommentUpdate() %></td>
	<td><%=reviewCommentList.get(i).getReviewNo() %></td>
	<td><button id="btnDelete" data-btn="delete" class="btn btn-danger">삭제</button></td>
</tr>
<% } %>

</table>

</div><!-- .container -->

<%@ include file="../reviewComment/paging.jsp" %>

<%@ include file="../managerLayout/footer.jsp" %>
