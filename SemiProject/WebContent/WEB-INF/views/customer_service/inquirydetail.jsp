<%@page import="dto.InquiryAnswer"%>
<%@page import="dto.Inquiry"%>
<%@page import="java.util.List"%>
<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<%
	Inquiry inquiry = (Inquiry)request.getAttribute("inquiry"); 
	InquiryAnswer inquiryAnswer = (InquiryAnswer)request.getAttribute("inquiryAnswer");
	
	if(inquiryAnswer.getContent() == null || "".equals(inquiryAnswer.getContent())){
		inquiryAnswer.setContent("답변을 기다리는 중입니다");
	}
%>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

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

button{
	margin:10px;
}

</style>

<div class="container text-center">
<br><br><br><br><br>
<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3><hr>
<span><a href="/notice/list">공지사항</a></span><br>
<span><strong><a href="/inquiry/list">1:1질문</a></strong></span>
</div>

<div class="col-xs-10">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
			<h3 id="title" align="left"><%=inquiry.getTitle() %></h3>
			<h6 id="write_date" align="left"><%=inquiry.getWrite_date() %></h6><hr>
			<p id="content" style="word-wrap: break-word;"><%=inquiry.getContent() %></p><hr>
			<p><%=inquiryAnswer.getContent() %></p>
			
			
				<div class="btn-group btn-group-md" role="group">
					 <button class="btn btn-secondary" onclick="location.href='/inquiry/list'">
				    	목록
					</button>
					<button class="btn btn-secondary" onclick="location.href='/inquiry/delete?inquiry_no=<%=inquiry.getInquiry_no()%>'">
				    	삭제
					</button>
				</div>
				
			</div>
		</div>
	</div>
	
</div>

</div>
