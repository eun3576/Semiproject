<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 스마트에디터2 설치 -->
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
		submitContents( $("#btnWrite") );
		
		$("form").submit();
	})
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	})
	
})	

//스마트에디터에 작성한 내용을 <textarea>에 반영하는 함수
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch(e) {}
	
}
	
</script>

<style type="text/css">


</style>

<div class="container">

<h1 align="center">후기 남기기</h1>
<hr>

<div class="row">

<form action="./write" method="post" enctype="multipart/form-data">
<!-- 후기 작성 입력 폼데이터 start -->

	<table class="table table-bordered" style="text-align: center; border: 1px solid #234;">
	
	<!-- 작성자의 정보를 매개변수로 받아서 
	작성 페이지에 반영하는 것 추후에 추가 해야한다. -->
	
		<tr>
			<th colspan="1">후기 남기기</th>
			<th>닉네임: <%=session.getAttribute("usernick") %></th>
		</tr>
		
		<tr>
			<td colspan="2"><input class="form-control" type="text" placeholder="후기 제목 (공백포함 최대40자)" name="reviewTitle"></td>
		</tr>
		
		<tr>
			<td colspan="2"><textarea class="form-control" id="content" name="reviewContent" placeholder="후기 내용을 입력 하세요."></textarea></td>
		</tr>
	
	</table>
	
	첨부파일: 최대 10MB <input type="file" name="file">

</form>
<!-- 후기 작성 입력 폼데이터 end -->

	<div class="text-center">
		<button type="button" id="btnWrite" class="btn btn-info">작성</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</div>

</div>
<!-- .container -->

<!-- <textarea>태그에 스마트에디터2를 스킨 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //스킨을 적용할 <textarea>의 id를 적어준다
	sSkinURI: "../resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
})
</script>

<%@ include file="../layout/footer.jsp"%>
