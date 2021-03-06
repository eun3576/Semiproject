<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/minty/bootstrap.min.css"> -->

<script type="text/javascript">
$(document).ready(function(){
	
	//url에서 '?'를 기준으로 문자열 추출'
	var url = document.location.href.split("?")
	
	if(url[1] != null){
		//요청파라미터가 존재하면 ajax로 파라미터 값 전달하기
		ajax(pagingCheck())
	}else{
		//요청파라미터가 없으면 체크박스 전체 체크한 값 ajax로 전달하기
		ajax(arrAllCheck())		
	}
	
})

//조회 버튼용 함수
function search(){
	
	//체크된 값 가져오기
	var checked = ischecked()
	
	
	if(Object.keys(checked).length===0){
		//체크된 값이 없으면 창 띄우기
		alert('조회 대상을 선택해주세요.')
	}else{
		//체크된 값을 ajax로 전달하기
		ajax(checked)				
	}
}

//체크된 값을 전달받아 서버와 통신
function ajax(checked){
	$.ajax({
		type:"get"
		,url:"/product/result"
		,data:checked
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

//요청 파라미터에 정보를 가져와 object타입으로 변환
function pagingCheck(){
	var checked = {}
	if(null != "<%=request.getParameter("child")%>"){
		checked.child = "<%=request.getParameter("child")%>"
	}
	if(null != "<%=request.getParameter("woman")%>"){
		checked.woman = "<%=request.getParameter("woman")%>"
	}
	if(null != "<%=request.getParameter("man")%>"){
		checked.man = "<%=request.getParameter("man")%>"
	}
	if(null != "<%=request.getParameter("aged")%>"){
		checked.aged = "<%=request.getParameter("aged")%>"
	}
	if(null != "<%=request.getParameter("eye")%>"){
		checked.eye = "<%=request.getParameter("eye")%>"
	}
	if(null != "<%=request.getParameter("intestine")%>"){
		checked.intestine = "<%=request.getParameter("intestine")%>"
	}
	if(null != "<%=request.getParameter("vitamin")%>"){
		checked.vitamin = "<%=request.getParameter("vitamin")%>"
	}
	if(null != "<%=request.getParameter("exercise")%>"){
		checked.exercise = "<%=request.getParameter("exercise")%>"
	}
	if(null != "<%=request.getParameter("curPage")%>"){
		checked.curPage = "<%=request.getParameter("curPage")%>"
	}
	return checked
}

//초기 화면을 위한 체크박스 전체 값 전달기능
function arrAllCheck(curPage){
	
	var checked = {
			child:$('input:checkbox[id="btncheck1"]').val()
			, woman:$('input:checkbox[id="btncheck2"]').val()
			, man:$('input:checkbox[id="btncheck3"]').val()
			, aged:$('input:checkbox[id="btncheck4"]').val()
			, eye:$('input:checkbox[id="btncheck5"]').val()
			, intestine:$('input:checkbox[id="btncheck6"]').val()
			, vitamin:$('input:checkbox[id="btncheck7"]').val()
			, exercise:$('input:checkbox[id="btncheck8"]').val()
		}
	
	return checked
}

//사용자가 체크한 값들 전달하는 기능
function ischecked(curPage){
	
	var checked = {}
	
	if($('input:checkbox[id="btncheck1"]').is(":checked") == true){
		checked.child = $('input:checkbox[id="btncheck1"]').val()
	}
	if($('input:checkbox[id="btncheck2"]').is(":checked") == true){
		checked.woman = $('input:checkbox[id="btncheck2"]').val()
	}
	if($('input:checkbox[id="btncheck3"]').is(":checked") == true){
		checked.man = $('input:checkbox[id="btncheck3"]').val()
	}
	if($('input:checkbox[id="btncheck4"]').is(":checked") == true){
		checked.aged = $('input:checkbox[id="btncheck4"]').val()
	}
	if($('input:checkbox[id="btncheck5"]').is(":checked") == true){
		checked.eye = $('input:checkbox[id="btncheck5"]').val()
	}
	if($('input:checkbox[id="btncheck6"]').is(":checked") == true){
		checked.intestine = $('input:checkbox[id="btncheck6"]').val()
	}
	if($('input:checkbox[id="btncheck7"]').is(":checked") == true){
		checked.vitamin = $('input:checkbox[id="btncheck7"]').val()
	}
	if($('input:checkbox[id="btncheck8"]').is(":checked") == true){
		checked.exercise = $('input:checkbox[id="btncheck8"]').val()
	}
	
	
	return checked
}

//초기화 버튼 클릭시 체크박스 전체 해제
function resetCheck(){
	$('#btncheck1').attr('checked', false);
	$('#btncheck2').attr('checked', false);
	$('#btncheck3').attr('checked', false);
	$('#btncheck4').attr('checked', false);
	$('#btncheck5').attr('checked', false);
	$('#btncheck6').attr('checked', false);
	$('#btncheck7').attr('checked', false);
	$('#btncheck8').attr('checked', false);
}

</script>

<style type="text/css">

hr{
	border:1px solid #ccc;
}

label{
	margin:3px 5px;
}

</style>

<div class="container">

<div id="checklist" class="text-center" style="margin:30px auto;">
	<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
	  <input type="checkbox" class="form-check-input" id="btncheck1" autocomplete="off" name="category" value="child">
	  <label for="btncheck1">어린이 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck2" autocomplete="off" name="category" value="woman">
	  <label for="btncheck2">여성 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck3" autocomplete="off" name="category" value="man">
	  <label for="btncheck3">남성 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck4" autocomplete="off" name="category" value="aged">
	  <label for="btncheck4">노인 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck5" autocomplete="off" name="category" value="eye">
	  <label for="btncheck5">눈 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck6" autocomplete="off" name="category" value="intestine">
	  <label for="btncheck6">장 건강</label>
	  <input type="checkbox" class="form-check-input" id="btncheck7" autocomplete="off" name="category" value="vitamin">
	  <label for="btncheck7">비타민</label>
	  <input type="checkbox" class="form-check-input" id="btncheck8" autocomplete="off" name="category" value="exercise">
	  <label for="btncheck8">운동</label>
	</div>
</div>

<div class="text-center">
	<button onclick="search();" class="btn btn-info" style="margin:0 5px;width:70px;">조회</button>
	<button onclick="resetCheck();" class="btn btn-info" style="margin:0 5px;width:70px;">초기화</button>
	<button onclick="location.href='/product/search'" class="btn btn-info" style="margin:0 5px;width:70px;">전체조회</button>
</div>
<hr>

<div id="resultLayout" style="height:100%;"></div>


</div>
<a href="#header" style="cursor:pointer;background:url('../resources/img/arrows_circle_top_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:46%;margin-top:-20px;z-index:100;"></a>
<a href="#footer" style="cursor:pointer;background:url('../resources/img/arrows_bottom_circle_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:54%;margin-top:-20px;z-index:100;"></a>
<br><br><br>
<%@ include file="../layout/footer.jsp" %>