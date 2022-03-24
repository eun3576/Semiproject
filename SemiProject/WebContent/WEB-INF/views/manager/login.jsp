<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../managerLayout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터키 입력 시 submit하도록 한다
	$("input").eq(1).keydown(function( e ) {
		if( e.keyCode == 13 ) { //엔터키
			$(this).parents("form").submit();
		}
	});

	//로그인 버튼 
	$("#btnLogin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 
	$("#btnCancel").click(function() {
		history.go(-1);
	})
	
})
</script>

<style type="text/css">
form {
	width: 500px;
	margin: 0 auto; 
	}
	</style>

<style>

button{
  background:#c0c0c0;
  color:#ffffff;
  border:none;
  position:relative;
  height:45px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}

</style>

<div class="container">

<img src="../resources/img/entermanager.jpg">

<form action="./login" method="post" class="form-horizontal">

	<div class="form-group">
		<label for="id" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-10">
			<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력해주세요" required autocomplete="on">
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="control-label col-xs-2">패스워드</label>
		<div class="col-xs-10">
			<input type="text" id="password" name="password" class="form-control" placeholder="패스워드를 입력해주세요">
		</div>
	</div>
	
	<br>
	
	<div class="text-center">
		<button type="button" id="btnLogin" class="btn">로그인</button>
		<button type="button" id="btnCancel" class="btn">취소</button>
	</div>

</form>

</div><!-- .container -->
