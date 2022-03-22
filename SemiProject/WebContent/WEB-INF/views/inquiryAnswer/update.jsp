<%@page import="dto.ManagerInquiryAnswer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../managerLayout/header.jsp" %>

<%
	ManagerInquiryAnswer updateInquiryAnswer = (ManagerInquiryAnswer) request.getAttribute("updateInquiryAnswer");
%>

<!-- 스마트에디터 2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents( $("#btnUpdate") )
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	

});
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

<h3>문의답변 작성하기</h3>
<hr>

<div>
<form action="/inquiryAnswer/update" method="post" >
<input type="hidden" name="inquiryno" value="<%=updateInquiryAnswer.getInquiryNo() %>" />

<table class="table table-bordered">
<tr><td class="info">관리자번호</td><td><%=updateInquiryAnswer.getManagerNo() %></td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"><%=updateInquiryAnswer.getContent() %></textarea></td></tr>
</table>

<div class="text-center">	
	<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- .container -->
</div>


<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<%@ include file="../managerLayout/footer.jsp" %>
