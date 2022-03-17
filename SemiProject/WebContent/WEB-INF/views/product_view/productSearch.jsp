<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.1.3/dist/minty/bootstrap.min.css">

<script type="text/javascript">
$(document).ready(function(){
	
	ajax(arrAllCheck())		

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
		type:"post"
		,url:"/product/search"
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

function arrAllCheck(){
	
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

function ischecked(){
	
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
	<button onclick="search();" class="btn btn-info" style="margin:0 10px;">조회</button>
</div>

<hr>

<div id="resultLayout"></div>



</div>
</body>
</html>