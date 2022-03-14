<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>header</title>
<!-- jQuery  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//로그인창 닫기 기본설정
	$("#login").hide();
	//로그인 창 열기
	$("#loginOpen").click(function(){
		$("#login").show();
	})
	//로그인창 닫기
	$("#loginClose").click(function(){
		$("#login").hide();
	})
	//로그인 유지 준비중
	$("#loginkeep").click(function(){
		alert("준비중입니다")
		this.checked=false;
	})
		
})
</script>
<!-- style sheet -->
<style type="text/css">
/* header style layout start */
div{padding:0;}
li{list-style:none;}
#header #smallNav li{float:right;margin-right:30px;cursor:pointer;}
#header #smallNav li:first-child{margin-right:50px;}
#header #smallNav li:hover{font-weight:bold;}
#header div{clear:right;margin-right:50px;}
#header div #mSearch{float:right;height:24px;border:1px solid #999;border-radius:3px;margin-top:10px;margin-right:5px;}
#header div span{float:right;background-image: url('../resources/img/search_icon.png');background-size:30px 30px;width:25px;height:25px;display:inline-block;margin-top:10px;cursor:pointer;}
#header h1{background-image: url("../resources/img/logo.png");background-size: 220px 100px;width:220px;height:100px;cursor:pointer;margin:20px auto 0;}
#header #nav{width:1200px;margin:0 auto;}
#header #nav > ul{clear:right;margin:30px 0 0 0;padding:0;}
#header #nav > ul > li{display:inline-block;font-size:25px;margin-right:169px;width:100px;cursor:pointer;text-align:center;position:relative;}
#header #nav > ul > li:last-child{margin-right:0;}
#header #nav > ul > li:hover{font-weight:bold;}
#header #nav > ul > li:hover > .subMenu {font-size:16px;overflow:visible;}
#header #nav > ul > li:hover > .subMenuA{height:70px;}
#header #nav > ul > li:hover > .subMenuB{height:50px;} 
#header #nav > ul > li > .subMenu{padding:0;background-color:#eee;position:absolute;height:0;font-size:0;overflow:hidden;}
#header #nav > ul > li > .subMenu > li{font-size:16px;font-weight:normal;}
#header #nav > ul > li > .subMenu > li:hover{font-weight:bold;}
#header #nav > ul > li > .subMenuA{width:450px;}
#header #nav > ul > li > .subMenuA > li{margin:0 10px;width:90px;float:left;}
#header #nav > ul > li > .subMenuB{width:102px;text-align:center;}
/* header style layout end */
</style>
</head>
<body>
<div id="header">
<ul id="smallNav">
<li>회원가입</li>
<li id="loginOpen">로그인</li>
</ul>
<div>
<span title="search"></span>
<input type="text" name="m_search" id="mSearch" placeholder="검색어를 입력해주세요">
</div>
<h1 title="영추영추" onclick="location.href='/'"></h1>
<div id="nav">
<ul>
<li>제품조회
<ul class="subMenu subMenuA">
<li>노화방지</li>
<li>눈 건강</li>
<li>소화기계</li>
<li>어린이 건강</li>
<li>순환기계</li>
<li>장 건강</li>
<li>호흡기계</li>
<li>여성 건강</li>
<li>관절&amp;연골</li>
<li>면역계</li>
<li>에너지</li>
<li>남성 건강</li>
</ul>
</li>
<li>제품추천</li>
<li>제품후기</li>
<li>BEST3</li>
<li>고객센터
<ul class="subMenu subMenuB">
<li>공지사항</li>
<li>1:1 문의</li>
</ul>
</li>
</ul>
</div>
</div>

<div>

</div>

</body>
</html>