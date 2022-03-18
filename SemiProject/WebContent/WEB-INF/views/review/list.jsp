<%@ page import="dto.Review" %>
<%@ page import="dto.UserInfo" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% List<Review> reviewList = (List) request.getAttribute("reviewList"); %>
<% List<UserInfo> nickList = (List) request.getAttribute("nickList"); %>
<% Boolean isLogin = (Boolean) request.getSession().getAttribute("login"); %>

<script type="text/javascript">

$(document).ready(function () {
	
	//후기 남기기 버튼을 이용하여 후기 쓰기 페이지로 이동
	$("#btnWrite").click(function() {
		
	<% if (isLogin == null) { %>
		alert('로그인이 필요 합니다!')
		location.href="/";
	<% } else { %>
		location.href="/review/write";
		
	<% } %>
	})
})

</script>

<style type="text/css">


.main_list {
    width: 1000px;
    margin: 0 auto;
    text-align: center;
}

.list_start {
    text-align: center;
}

.list_detail {
    display: inline-block;
    width: 220px;
    height: 170px;
	border: 2px solid #f2dede;
 	border-radius: 40px 80px; 
    margin: 6px;
    padding: 9px;
    background-color: #f2dede;
    overflow-x:hidden;
}

/* 텍스트가 div영역을 초과하면 생기는 스크롤바 숨기기 */
.list_detail::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

</style>

<div class="container">

<h1 align="center">제품 후기 게시판</h1>
<hr>


<div class="main_list">

	<% for(int i=0; i<reviewList.size(); i++) { %>

		<div class="list_detail">
			제목: <%=reviewList.get(i).getTitle() %><br>
			닉네임: <%=nickList.get(i).getNickname() %><br>
			증상: <%=nickList.get(i).getSympton() %><br>
			작성날짜: <%=reviewList.get(i).getWriteDate() %><br>
			조회수: <%=reviewList.get(i).getViews() %><br>
			이미지 준비중<br>
			<button class="btn-primary btn-xs" onclick="location.href='./view?review_no=<%=reviewList.get(i).getReview_no() %>'">자세히 보기
			</button>
<%-- 		<a href="./view?reviewno=<%=reviewList.get(i).getReview_no() %>">자세히 보기</a>  --%>
		</div>
		
	<% } %>
	
</div><!-- main_list class end -->


<!-- 후기 남기기 버튼 -->
<button id="btnWrite" class="btn btn-primary">후기 남기기</button>

	
</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>
