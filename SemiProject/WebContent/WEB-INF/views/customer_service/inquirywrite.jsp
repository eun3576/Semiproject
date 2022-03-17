<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		
		var pwtest = /^[0-9]+$/.test($('#password').val())
		if(pwtest){
			$("#inquiryform").submit()		
		}else{
			alert("비밀번호는 숫자만 입력해주세요.")
			$('#password').val("")
		}
		
		
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<style type="text/css">


#submenu{
	text-align:center;
	line-height:40px;
	border:1px solid #ccc;
	height:180px;
}

hr{
	border:1px solid #ccc;
}

a{
	color:black;
}

a:hover{
	text-decoration:none;
	color:black;
}

</style>

<div class="container text-center">
<br><br><br><br><br>

<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3><hr>
<span><a href="/notice/list">공지사항</a></span><br>
<span><strong><a href="/inquiry/list">1:1질문</a></strong></span>
</div>

<div id="write" class="col-xs-10">

<form id="inquiryform" action="./write" method="post">
  <fieldset>
    <legend>문의하기</legend>
 
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title" name="title" placeholder="Enter title">
    </div>
    
    <div class="form-group">
      <label for="password">비밀번호</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Password">
    </div>
    
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" id="content" name="content" rows="3"></textarea>
    </div>

    <button type="button" id="btnWrite" class="btn btn-primary">작성</button>
    <button type="button" id="btnCancel" class="btn btn-primary">취소</button>
  </fieldset>
</form>

</div>
</div>


</body>
</html>
