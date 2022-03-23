<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./managerLayout/header.jsp" %>

<style>

.intro{
  width:100%;
  height:30px;
}
.intro h1{
  font-family:'Oswald', sans-serif;
  letter-spacing:2px;
  font-weight:normal;
  font-size:14px;
  color:#222;
  text-align:center;
  margin-top:10px;
}
.intro a{
  color:#e74c3c;
  font-weight:bold;
  letter-spacing:0;
}
.intro img{
  width:20px;
  heght:20px;
  margin-left:5px;
  margin-right:5px;
  position:relative;
  top:5px;
}

*{margin:0;padding:0;box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;}
#container{
  width:715px;
  height:230px;
  margin:50px auto;
}
.button-1{
  width:140px;
  height:50px;
  border:1px solid #34495e;
  float:left;
  text-align:center;
  cursor:pointer;
  position:relative;
  box-sizing:border-box;
  overflow:hidden;
  margin:0 0 40px 0;
}
.button-1 a{
  font-family:arial;
  font-size:16px;
  color:#34495e;
  text-decoration:none;
  line-height:50px;
  transition:all .5s ease;
  z-index:2;
  position:relative;
}
.eff-1{
  width:140px;
  height:50px;
  top:-2px;
  right:-140px;
  background:#34495e;
  position:absolute;
  transition:all .5s ease;
  z-index:1;
}
.button-1:hover .eff-1{
  right:0;
}
.button-1:hover a{
  color:#fff;
}

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

<div class = "text-center">

<a href="/"> <img alt="영추영추" src="../resources/img/logo.png" width="300" height="125"> </a>

<hr>

<%-- 비로그인 상태 --%>
<% if( session.getAttribute("login") == null ) {%>
<h3><strong>관리자 계정의 로그인이 필요합니다</strong></h3><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/manager/login'">로그인</button>
<% } %>

<%-- 로그인 상태 --%>
<% if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) {%>
<h3><strong><%=session.getAttribute("id") %>님, 환영합니다!</strong></h3>

<div id="container">
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/"> 메인페이지 </a>
  </div>
	<div class="button-1">
    <div class="eff-1"></div>
    <a href="/manager/user"> 회원 관리 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/manager/itemAdd"> 제품 등록 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/manager/item"> 제품 관리 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/manager/review"> 게시글 관리 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/reviewcomment/list"> 댓글 관리 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/inquiryanswer/list"> 1:1문의 관리 </a>
  </div>
  <div class="button-1">
    <div class="eff-1"></div>
    <a href="/managernotice/list"> 공지사항 관리 </a>
  </div>
  	<div class="button-1">
    <div class="eff-1"></div>
    <a href="/chat/manager"> 실시간 채팅 관리 </a>
  	</div>
   <div class="button-1">
    <div class="eff-1"></div>
    <a href="/manager/logout"> 로그아웃 </a>
  </div>
</div>

<hr>

<% } %>

</div>

</div><!-- .container -->