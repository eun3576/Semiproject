<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../managerLayout/header.jsp" %>

<!-- 스마트에디터2 설치 -->
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
		submitContents( $("#btnWrite") );
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

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
#content {
	width: 100%;
}
</style>

<div class="container">

<h3>문의답변 작성</h3>
<hr>

<div>
<form action="./write" method="post">

<table class="table table-bordered">

<tr><td class="info">관리자번호</td><td><%=session.getAttribute("managerNo") %></td></tr>

<tr><td class="info">문의번호</td><td><input type="text" name ="inquiryNo" style="width:25%"/></td></tr>

<tr><td class="info" colspan="2">본문</td></tr>

<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>

</table>
</form>
</div>

<div class="text-center">	

	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	
</div>

<!-- .container -->
</div>

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

<%@ include file="../managerLayout/footer.jsp" %>

