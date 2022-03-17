<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>header</title>

<!-- jQuery  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="../../resources/js/userJoinCheck.js"></script>
<script type="text/javascript" src="../resources/js/httpRequest.js"></script>

<!-- 회원가입 ajax script -->
<script type="text/javascript">
function ajaxToServerId(){
	console.log("ajaxToServerId() called")
	//전달 파라미터 저장
	var params = "userJoinid="+userJoinid.value;
	sendRequest("POST", "/user/signup/check", params, ajaxFromServerId);
	console.log(params)
	
}

function ajaxToServerNick(){
		//전달 파라미터 저장
		var params = "userJoinnick="+userJoinnick.value;
		console.log(params)
		sendRequest("POST", "/user/signup/check", params, ajaxFromServerNick);
}

function ajaxFromServerId(){
	console.log("ajaxFromServer() called")
	if(httpRequest.readyState==4){//done, 응답완료
		if(httpRequest.status==200){//정상응답
			console.log("ajax정상응답")
			
			//정상 응답 처리
			appendResultId();
		
		}else{
			console.log("요청/응답 오류")
		}
	}
}

function ajaxFromServerNick(){
	console.log("ajaxFromServer() called")
	if(httpRequest.readyState==4){//done, 응답완료
		if(httpRequest.status==200){//정상응답
			console.log("ajax정상응답")
			
			//정상 응답 처리
			appendResultNick();
		
		}else{
			console.log("요청/응답 오류")
		}
	}
}

//정상 응답 후 응답데이터를 처리하는 함수
function appendResultId(){
	//응답데이터를 #result에 적용한다
	rescheckid.innerHTML = httpRequest.responseText;
}
function appendResultNick(){
	//응답데이터를 #result에 적용한다
	reschecknick.innerHTML = httpRequest.responseText;
}
</script>

<!-- 로그인,회원가입 script -->
<script type="text/javascript">
$(document).ready(function(){
// 	$("#loginOpen").click(function(){
// 		location.href="/user/login";
// 	}) 
	//로그인창 닫기 기본설정
	$("#login").hide();
	//로그인 창 열기
	$("#loginOpen").click(function(){
		$("#login").show();
		//페이지 접속시 처음 input(아이디)에 포커스
		$("#userid").focus();
		if($("#userJoin").show){
			$("#userJoin").hide();
		}
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
	//로그인 버튼 클릭시 submit하도록 한다
	$("#btnlogin").click(function(){
		$(this).parents("form").submit();
	})
	//패스워드 입력창에서 엔터키 입력시 submit하도록 한다
	$("#userpw").keydown(function(e){
		if(e.keyCode == 13){//엔터키
			$(this).parents("form").submit();
		}
	})
	
	//회원가입
	//회원가입창 닫기 기본설정
	$("#userJoin").hide();
	//회원가입 창 열기
	$("#userJoinOpen").click(function(){
		$("#userJoin").show();
		//페이지 접속시 처음 input(아이디)에 포커스
		$("#userJoinid").focus();
		
		if($("#login").show){
			$("#login").hide();
		}
	})
	
	//사용중인 아이디인지 확인
	$("#userJoinid").keyup(function(){
		ajaxToServerId();
	})
	//사용중인 닉네임인지 확인
	$("#userJoinnick").keyup(function(){
		ajaxToServerNick();
	})
	
	//회원가입창 닫기
	$("#userJoinClose").click(function(){
		$("#userJoin").hide();
	})
	
	//가입하기 버튼 클릭시 submit하도록 한다
	$("#btnJoin").click(function(){
		return check();
		$(this).parents("form").submit();
	})
	//닉네임 입력창에서 엔터키 입력시 submit하도록 한다
	$("#userJoinnick").keydown(function(e){
		if(e.keyCode == 13){//엔터키
			$(this).parents("form").submit();
		}
	})
		
})
</script>

<!-- style sheet -->
<style type="text/css">
/* header style layout start */
div{padding:0;}
li{list-style:none;}
#header #smallNav li{float:right;margin-right:30px;cursor:pointer;}
#header #smallNav li:first-child, #header #smallNav li:nth-child(0){margin-right:50px;}
#header #smallNav li:hover{font-weight:bold;}
#header div{clear:right;margin-right:50px;}
#header div #mSearch{float:right;height:24px;border:1px solid #999;border-radius:3px;margin-top:10px;margin-right:5px;}
#header div span{float:right;background-image: url('../../resources/img/search_icon.png');background-size:30px 30px;width:25px;height:25px;display:inline-block;margin-top:10px;cursor:pointer;}
#header h1{background-image: url("../../../resources/img/logo.png");background-size: 220px 100px;width:220px;height:100px;cursor:pointer;margin:20px auto 0;}
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

#footer { text-align: center; }
/* footer style layout */
</style>
</head>
<body>
<div id="header">
<ul id="smallNav">
<%if(null != session.getAttribute("login") && (boolean)session.getAttribute("login")){ %>
<%--로그인 상태 --%>
<li onclick="location.href='/user/logout'">로그아웃</li>
<li><%=session.getAttribute("usernick") %>님</li>
<%} else{ %>
<%--비로그인 상태 --%>
<li id="userJoinOpen">회원가입</li>
<li id="loginOpen">로그인</li>
<%} %>
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
<!-- 제품 후기 선택시 제품 후기 리스트로 이동 기능 추가 예정 -->
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

<!-- 로그인 -->
<div id="login" style="width:400px;height:450px;background:#fff;border:2px solid #eee;position:fixed;top:10%;left:50%;margin-left:-200px;z-index:1000;">
<form action="/user/login" method="post">
<span id="loginClose" style="cursor:pointer;float:right;margin-right:10px;margin-top:5px;font-size:20px;font-weight: bold;">X</span>
<h3 style="clear:right;font-size:25px;width:75px;margin:0 auto;">로그인</h3>
<div style="width:300px;margin:50px auto 0;">
<label for="userid" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userid" name="userid" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="아이디" type="text">
</div>
<div style="clear:left;width:300px;margin:15px auto;">
<label for="userpw"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userpw" name="userpw"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="비밀번호" type="password"  autocomplete="off">
</div>
<div style="width:300px;margin:0 auto;">
<input type="checkbox" id="loginkeep" name="loginkeep"><label for="loginkeep">로그인 상태 유지</label>
<a onclick="alert('준비중입니다');" style="cursor:pointer;margin-left:50px;">ID/PW 찾기</a>
</div>
<div id="buttons">
<div class="socialLogin" style="width:130px;margin:40px auto;">
<span style="margin-right:23px;cursor:pointer;background:url('../../resources/img/kakao_logo_icon.png') no-repeat;background-size:50px;width:50px;height:50px;display:inline-block;" onclick="alert('준비중입니다');"></span>
<span style="cursor:pointer;background:url('../../resources/img/naver_logo_icon.png') no-repeat;background-size:50px;width:50px;height:50px;display:inline-block;"  onclick="alert('준비중입니다');"></span>
</div>
<button id="btnlogin" style="font-size:16px;width:80px;height:35px;margin:0 auto;display:block;background:rgb(47,140,198);border-radius:5px;border:none;cursor:pointer;color:white;font-weight:bold;">로그인</button>
</div>
</form>
</div>

<!-- 회원가입 -->
<div id="userJoin" style="width:400px;height:600px;background:#fff;border:2px solid #eee;position:fixed;top:10%;left:50%;margin-left:-200px;z-index:1000;">
<form action="/user/signup" method="post" name="frm">
<span id="userJoinClose" style="cursor:pointer;float:right;margin-right:10px;margin-top:5px;font-size:20px;font-weight: bold;">X</span>
<h3 style="clear:right;font-size:25px;width:100px;margin:0 auto;">회원가입</h3>
<div style="width:300px;margin:50px auto 0;">
<label for="userJoinid" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinid" name="userJoinid" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="아이디는 4~6자로 입력" type="text">
<span id="rescheckid" style="font-size:14px;color:red;height:18px;display:block;"></span>
</div>
<div style="width:300px;margin:0 auto;">
<label for="userJoinnick" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinnick" name="userJoinnick" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="닉네임" type="text">
<span id="reschecknick" style="font-size:14px;color:red;height:18px;display:block;"></span>
</div>

<div style="clear:left;width:300px;margin:0 auto 18px;">
<label for="userJoinpw"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinpw" name="userJoinpw"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="비밀번호는 영문, 숫자포함 6~8자" type="password" autocomplete="off">
</div>

<div  style="width:300px;margin:0 auto;">
<label for="userJoinpwcheck"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinpwcheck" name="userJoinpwcheck"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="비밀번호 확인" type="password" autocomplete="off">
</div>

<div  style="width:300px;margin:18px auto;">
<label for="userJoinphone"  style="cursor:pointer;background:url(../../resources/img/mobile_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:39px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinphone" name="userJoinphone"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="'-' 제외하고 입력해주세요" type="text">
</div>

<div style="width:155px;margin:15px auto;">
<label for="gender"  style="margin-right:20px;" >성별</label>
<input type="radio" id="gender1" name="gender" value="M" required="required">남
<input type="radio" id="gender2" name="gender" value="F"  style="margin-left:20px;">여
</div>

<div style="width:300px;margin:0 auto;">
<input type="checkbox" name="terms1" id="terms1" required="required" > <label for="terms1">이용약관에 대한 동의(필수)</label>
</div>
<div style="width:300px;margin:0 auto;">
<input type="checkbox" name="terms2" id="terms2" required="required"> <label for="terms2">개인정보 수집 및 이용 동의(필수)</label>
</div>
<div style="width:300px;margin:0 auto 20px;">
<input type="checkbox" name="terms3" id="terms3" required="required"> <label for="terms3">만 14세 이상입니다(필수)</label>
</div>
<div id="buttons">
<button id="btnJoin" style="font-size:16px;width:80px;height:35px;margin:0 auto;display:block;background:rgb(47,140,198);border-radius:5px;border:none;cursor:pointer;color:white;font-weight:bold;">회원가입</button>
</div>
</form>
</div>


</body>
</html>
