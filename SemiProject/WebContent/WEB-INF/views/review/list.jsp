<%@ page import="dto.Review" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<% List<Review> reviewList = (List) request.getAttribute("reviewList"); %>

<script type="text/javascript">

$(document).ready(function () {
	
	//후기 남기기 버튼을 이용하여 후기 쓰기 페이지로 이동
	$("#btnWrite").click(function() {
		location.href="/review/write";
	})
	
})

</script>

<div class="container">

<h1 align="center">제품 후기 게시판</h1>
<hr>

	<div class="row text-center">
	
	<% for(int i=0; i<reviewList.size(); i++) { %>
		<div class="col-xs-3 bordered" style="border: 1px solid #555; border-collapse: collapse;">
			제목: <%=reviewList.get(i).getTitle() %><br>
			글번호: <%=reviewList.get(i).getReview_no() %><br>
			작성자: <%=reviewList.get(i).getUser_no() %><br>
			작성날짜: <%=reviewList.get(i).getWriteDate() %><br>
			조회수: <%=reviewList.get(i).getViews() %><br>
			이미지 추가 예정
			<button class="btn-primary btn-xs" onclick="location.href='./view?review_no=<%=reviewList.get(i).getReview_no() %>'">자세히 보기
			</button>
	<%-- 	<a href="./view?reviewno=<%=reviewList.get(i).getReview_no() %>">자세히 보기</a> --%>
		</div>
	<% } %>
	</div>
	
	<!-- 후기 남기기 버튼 -->
	<div>
		<button id="btnWrite" class="btn">후기 남기기</button>
	</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>
