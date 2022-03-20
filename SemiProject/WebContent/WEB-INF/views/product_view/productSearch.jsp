<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/minty/bootstrap.min.css">

<script type="text/javascript">
$(document).ready(function(){
	
	var url = document.location.href.split("?")
	console.log(url[1])
	
	if(url[1] != null){
		ajax(pagingCheck())
	}else{
		ajax(arrAllCheck())		
	}
	
})


	
function search(){
	
	var checked = ischecked()
	if(Object.keys(checked).length===0){
		alert('조회 대상을 선택해주세요.')
	}else{
		ajax(checked)				
	}
}

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

</style>

<div class="container">

<div id="checklist" class="text-center" style="margin:30px auto;">
	<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
	  <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off" name="category" value="child">
	  <label class="btn btn-primary" for="btncheck1">어린이 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck2" autocomplete="off" name="category" value="woman">
	  <label class="btn btn-primary" for="btncheck2">여성 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck3" autocomplete="off" name="category" value="man">
	  <label class="btn btn-primary" for="btncheck3">남성 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck4" autocomplete="off" name="category" value="aged">
	  <label class="btn btn-primary" for="btncheck4">노인 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck5" autocomplete="off" name="category" value="eye">
	  <label class="btn btn-primary" for="btncheck5">눈 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck6" autocomplete="off" name="category" value="intestine">
	  <label class="btn btn-primary" for="btncheck6">장 건강</label>
	  <input type="checkbox" class="btn-check" id="btncheck7" autocomplete="off" name="category" value="vitamin">
	  <label class="btn btn-primary" for="btncheck7">비타민</label>
	  <input type="checkbox" class="btn-check" id="btncheck8" autocomplete="off" name="category" value="exercise">
	  <label class="btn btn-primary" for="btncheck8">운동</label>
	</div>
	<button onclick="search();" class="btn btn-info" style="margin:0 10px;width:90px;">조회</button>
	<button onclick="resetCheck();" class="btn btn-info" style="margin:0 10px;width:90px;">초기화</button>
	<button onclick="location.href='/product/search'" class="btn btn-info" style="margin:0 10px;width:90px;">전체조회</button>
</div>

<hr>

<div id="resultLayout"></div>



</div>


</body>
</html>