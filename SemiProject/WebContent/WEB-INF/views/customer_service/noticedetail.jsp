<%@page import="java.util.List"%>
<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../layout/header.jsp" %>

<% 
	int notice_no = Integer.parseInt(request.getParameter("notice_no"));
	int cntList = (int)request.getAttribute("cntList");
%>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">


$(document).ready(function(){
	var curNo = <%=notice_no%>
	var cntList = <%=cntList %>
	
	ajax(curNo)
	
	$("#btnNext").click(function(){
		if(curNo > 1){
			curNo = curNo - 1
			ajax(curNo)
		}else{
			alert("마지막 페이지입니다.")
		}
	})
	
	$("#btnPre").click(function(){
		if(curNo < cntList){
			curNo = curNo + 1
			ajax(curNo)
		}else{
			alert("마지막 페이지입니다.")
		}
	})
})

function ajax(curNo){
	$.ajax({
		type:"post"
		,url:"/notice/detail"
		,data:{
			notice_no:curNo
		}
		, dataType:"html"
		, success:function(res){
			console.log("AJAX 성공")
			
			$('#resultLayout').html(res)
		}
		, error:function(){
			console.log("AJAX 실패")
		}
	})
}
</script>

<style type="text/css">

#submenu{
	text-align:center;
	line-height:40px;
	background:#78CCC8;
	height:150px;
}

th{
	text-align:center;
}

a{
	color:black;
}

a:hover{
	text-decoration:none;
	color:blue;
}


</style>

<div class="container text-center">
<br><br><br><br><br>
<div id="submenu" class="col-xs-2">
<h3><strong>고객센터</strong></h3>
<span><strong><a href="/notice/list">공지사항</a></strong></span><br>
<span><a href="/inquiry/list">1:1질문</a></span>
</div>

<div class="col-xs-10">
	<div id="resultLayout"></div>

	<button id="btnPre" class="btn btn-secondary">
		이전
	</button> 
	<button class="btn btn-secondary" onclick="location.href='/notice/list'">
		목록
	</button> 
	<button id="btnNext" class="btn btn-secondary">
		다음
	</button>
</div>

</div>