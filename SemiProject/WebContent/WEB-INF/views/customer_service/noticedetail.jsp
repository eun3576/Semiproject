<%@page import="java.util.List"%>
<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<% 
	//공지사항 번호 가져오기
	int notice_no = Integer.parseInt(request.getParameter("notice_no"));

	//공지사항 총 개수 가져오기
	int cntList = (int)request.getAttribute("cntList");
%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	//현재 공지사항 번호 저장
	var curNo = <%=notice_no%>
	
	//총 공지사항 개수 저장
	var cntList = <%=cntList %>
	
	//현재 공지사항번호 전달하여 ajax 호출
	ajax(curNo)
	
	//다음 버튼이 눌리면 이벤트 발생
	$("#btnNext").click(function(){
		
		//현재 공지사항 번호가 1보다 크다면 
		if(curNo > 1){
			//이전 공지사항 번호로 지정
			curNo = curNo - 1
			//이전 공지사항 번호를 전달하여 ajax호출
			ajax(curNo)
		}else{
			//마지막 공지사항일 경우 창 띄우기
			alert("마지막 페이지입니다.")
		}
	})
	
	//이전 버튼 눌리면 이벤트 발생
	$("#btnPre").click(function(){
		
		//현재 공지사항 번호가 총 공지사항 개수보다 작으면
		if(curNo < cntList){
			//다음 공지사항 번호로 지정
			curNo = curNo + 1
			//다음 공지사항 번호를 전달하여 ajax 호출
			ajax(curNo)
		}else{
			//마지막 공지사항일 경우 창 띄우기
			alert("마지막 페이지입니다.")
		}
	})
})

//공지사항 번호를 전달 받는 ajax
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
<br><br>
<a href="#header" style="cursor:pointer;background:url('../resources/img/arrows_circle_top_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:46%;margin-top:-20px;z-index:100;"></a>
<a href="#footer" style="cursor:pointer;background:url('../resources/img/arrows_bottom_circle_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:54%;margin-top:-20px;z-index:100;"></a>
<%@ include file="../layout/footer.jsp" %>
