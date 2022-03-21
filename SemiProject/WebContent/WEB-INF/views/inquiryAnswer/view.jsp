<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.ManagerInquiryAnswer"%>
<%@ include file="../managerLayout/header.jsp" %>

<%
	ManagerInquiryAnswer viewInquiryAnswer = (ManagerInquiryAnswer) request.getAttribute("viewInquiryAnswer");
%>

<script type="text/javascript">
$(document).ready(function(){
	//목록
	$("#btnList").click(function(){
		$(location).attr("href","<%=request.getContextPath() %>/inquiryanswer/list");
	})
	
	//수정
	$("#btnUpdate").click(function(){
		$(location).attr("href", "<%=request.getContextPath() %>/inquiryanswer/update?answerNo=<%=viewInquiryAnswer.getAnswerNo() %>");
	})
	
	//삭제
	$("#btnDelete").click(function(){
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/inquiryanswer/delete?inquiryNo=<%=viewInquiryAnswer.getInquiryNo() %>");
		}
		
	})
})
</script>

<div class="container">

<h1>문의답변 상세보기</h1>
<hr>
<table class="table table-borderd">
<tr>
<td class="info">답변번호</td><td colspan="3"><%=viewInquiryAnswer.getAnswerNo() %></td>
</tr>

<tr>
<td class="info">작성날짜</td><td colspan="3"><%=viewInquiryAnswer.getWriteDate() %></td>
</tr>

<tr>
<td class="info">작성자</td><td colspan="3"><%=viewInquiryAnswer.getManagerNo() %></td>
</tr>

<tr>
<td class="info">문의번호</td><td colspan="3"><%=viewInquiryAnswer.getInquiryNo() %></td>
</tr>

<tr>
<td class="info">내용</td><td colspan="5"><%=viewInquiryAnswer.getContent() %></td>
</tr>

</table>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../managerLayout/footer.jsp" %>


    