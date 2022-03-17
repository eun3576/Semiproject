<%@ page import="dto.Review" %>
<%@ page import="dto.Attachment" %>
<%@ page import="dto.UserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<% Review updateReview = (Review) request.getAttribute("updateReview"); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<% Attachment updateAttach = (Attachment) request.getAttribute("updateAttach"); %>

<!-- bootstrap 추가 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		console.log("수정 버튼 눌러짐 테스트")
		$("form").submit();
		console.log(parameter)
	})
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	})
	
		//파일이 없을 경우
	if(<%=updateAttach != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=updateAttach == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})
})	
	
</script>

<div class="container">

<h1 align="center">후기 수정하기</h1>
<hr>

<div class="row">

<form action="/review/update" method="post" enctype="multipart/form-data">
<!-- 후기 작성 입력 폼데이터 start -->
	
	<input type="hidden" name="reviewNo" value="<%=updateReview.getReview_no() %>">
	<table class="table table-bordered" style="text-align: center; border: 1px;">
	
	<!-- 작성자의 정보를 매개변수로 받아서 
	작성 페이지에 반영하는 것 추후에 추가 해야한다. -->
		<tr>
			<th>글번호: <%=updateReview.getReview_no() %></th>
			<th>작성한 : <%=userInfo.getNickname() %></th>
		</tr>
		<tr>
			<th colspan="2">후기 제목 수정하기</th>
		</tr>
		
		<tr>
			<td colspan="2"><input class="form-control" type="text" 
			name="reviewTitle" value="<%=updateReview.getTitle() %>" 
			placeholder="수정할 후기 제목을 입력 하세요.(공백포함 최대40자)"></td>
		</tr>
		
		<tr><th colspan="2">본문</th></tr>
		
		<tr>
			<td colspan="2"><textarea class="form-control" name="reviewContent" 
			placeholder="수정할 후기 내용을 입력 하세요."><%=updateReview.getContent() %></textarea></td>
		</tr>
	
	</table>


<!-- 첨부파일 -->
<div>

	<div id="beforeFile">
<%	if( updateAttach != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=updateAttach.getStored_img() %>"
		 download="<%=updateAttach.getOrigin_img() %>">
			<%=updateAttach.getOrigin_img() %>
		<img alt="이미지를 불러올 수 없음" src="<%=request.getContextPath() %>
		/upload/<%=updateAttach.getStored_img()%>" width="200" height="200">
		</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
<%	} %>
	</div>

	<div id="afterFile">
		새 첨부파일: 최대 10MB
		<input type="file" name="file">
	</div>
</div>

<br>
</form>
<!-- 후기 수정 폼데이터 end -->


	<div class="text-center">
		<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</div>


</div>
<!-- .container -->

<%@ include file="../layout/footer.jsp"%>
