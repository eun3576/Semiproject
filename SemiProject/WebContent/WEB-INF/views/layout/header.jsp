<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userid = (String)session.getAttribute("userid"); %>
<!DOCTYPE html>
<html>
<title>영추영추</title>

<!-- 구글웹폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


<!-- jQuery  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>
<script type="text/javascript" src="/resources/js/userJoinCheck.js"></script>

<!-- 회원가입 ajax script -->
<script type="text/javascript">
function ajaxToServerId(){
	console.log("ajaxToServerId() called")
	//전달 파라미터 저장
	var params = "joinUserId=" + joinUserId.value;
	sendRequest("POST", "/user/signup/check", params, ajaxFromServerId);
	console.log(params)
	
}

function ajaxToServerNick(){
		//전달 파라미터 저장
		var params = "joinUserNick=" + joinUserNick.value;
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
	//로그인창 닫기 기본설정
	$("#login").hide();
	//로그인 창 열기
	$("#loginOpen").click(function(){
		$("#login").css("display","block");
		$("#login").show();
		//페이지 접속시 처음 input(아이디)에 포커스
		$("#userid").focus();
		if($("#userJoin").show){
			$("#userJoin").hide();
		}
	})
	//로그인창 닫기
	$("#loginClose").click(function(){
		$("#login").css("display","none");
		$("#login").hide();
		
		//닫을 때 기존 기록 삭제
		$("#userid").val("");
		$("#userpw").val("");
		
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
	$("#userJoin").css("display","none");
	//회원가입 창 열기
	$("#userJoinOpen").click(function(){
		$("#userJoin").css("display","block");
		$("#userJoin").show();
		//페이지 접속시 처음 input(아이디)에 포커스
		$("#joinUserId").focus();
		
		if($("#login").show){
			$("#login").hide();
		}
	})
	
	//사용중인 아이디인지 확인
	$("#joinUserId").keyup(function(){
		ajaxToServerId();
	})
	
	//사용중인 닉네임인지 확인
	$("#joinUserNick").keyup(function(){
		ajaxToServerNick();
	})
	
	$("#joinUserId").keyup(function(){
		ajaxToServerId();
	})
	
	//회원가입창 닫기/닫으면서 기록한거 삭제
	$("#userJoinClose").click(function(){
		//숨기기
		$("#userJoin").hide();
		
		//작성한거 삭제
		$("#joinUserId").val("");
		$("#joinUserNick").val("");
		$("#userJoinpw").val("");
		$("#userJoinpwcheck").val("");
		$("#userJoinphone").val("");
		$("#gender1").prop("checked",false);
		$("#gender2").prop("checked",false);
		$("#terms1").prop("checked",false);
		$("#terms2").prop("checked",false);
		$("#terms3").prop("checked",false);
		
	})
	
	//가입하기 버튼 클릭시 submit하도록 한다
	$("#btnJoin").click(function(){
		return check();
		$(this).parents("form").submit();
	})
})
</script>



<!-- 검색창 script -->
<script type="text/javascript">
$(document).ready(function(){
	
	//검색(돋보기) 버튼 눌렀을 때 submit하도록 한다
	$("#searchIcon").click(function(){
		$(this).parents("form").submit();
	})
	
	//검색어 입력창에서 엔터키 입력시 submit하도록 한다
	$("#mSearch").keydown(function(e){
		if(e.keyCode == 13){//엔터키
			$(this).parents("form").submit();
		}
	})
})

function chatOpen(){
<%if(userid!=null&&!userid.equals("")){%>
	window.open('/chat/user','_blank','width=655, height=805')
<%} else{%>
	alert("로그인 후 이용 가능합니다")
	return false;
<%}%>
}
</script>

<!-- 로그인 실패 script -->
<script>
<%if(null != session.getAttribute("login") && !(boolean)session.getAttribute("login")){ %>
	alert("아이디 또는 비밀번호가 일치하지 않습니다. 다시 로그인을 시도해주세요.")
<%}%>
</script>	

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<!-- style sheet -->
<style type="text/css">

body{ 
    font-family : 'Jua', sans-serif;
}

a{text-decoration:none;color:black;}
p{margin:0;}
/* header style layout start */
div{padding:0;margin:0;}
ul{margin:0;}
li{list-style:none;}
#header{margin-top:10px;}
#header #smallNav li{float:right;margin-right:30px;cursor:pointer;font-size:16px;height:16px;}
#header #smallNav li:first-child, #header #smallNav li:nth-child(0){margin-right:50px;}
#header #smallNav li:hover{font-weight:bold;}
#header div{clear:right;margin-right:50px;}
#header div #mSearch{float:right;width:200px;height:30px;border:1px solid #999;border-radius:3px;margin-top:20px;margin-right:5px;padding:0;font-size:13px;line-height: 1em;box-sizing: border-box;}
#header div span{float:right;background-image: url('../../resources/img/search_icon.png');background-size:30px 30px;margin-top:20px;width:30px;height:30px;display:inline-block;cursor:pointer;}
#header h1{background-image: url("../../../resources/img/logo.png");background-size: 220px 100px;width:220px;height:100px;cursor:pointer;margin:20px auto 0;}
#header #nav{width:1200px;margin:0 auto;}
#header #nav > ul{clear:right;margin:30px 0 0 0;padding:0;}
#header #nav > ul > li{display:inline-block;font-size:25px;margin-right:130px;width:130px;cursor:pointer;text-align:center;position:relative;}
#header #nav > ul > li:last-child{margin-right:0;}
#header #nav > ul > li:hover{font-weight:bold;}
#header #nav > ul > li:hover > .subMenu {font-size:16px;overflow:visible;z-index:50;height:50px;}
#header #nav > ul > li > .subMenu{padding:0;background-color:#eee;position:absolute;height:0;font-size:0;overflow:hidden;width:130px;}
#header #nav > ul > li > .subMenu > li{font-size:16px;font-weight:normal;text-align:center;}
#header #nav > ul > li > .subMenu > li:hover{font-weight:bold;}

/* header style layout end */

#footer { text-align: center; }
/* footer style layout */

/* footer style layout start */
#footer{background-color:#eee;margin:0;padding:0;height:100px;padding-top:10px;}
#footer ul{width:1200px;margin:30px auto;padding:0;text-align: center;}
#footer ul li{display:inline-block;margin-right:30px;}
/* footer style layout end */
</style>

<link rel="shortcut icon" href="/resources/img/favicon.ico">

</head>
<body>
<!-- header시작 -->
<div id="header">
<ul id="smallNav">
<%if(null != session.getAttribute("login") && (boolean)session.getAttribute("login")){ %>
<%--로그인 상태 --%>
<li onclick="location.href='/user/logout'">로그아웃</li>
<li onclick="location.href='/profile/main'"><%=session.getAttribute("usernick") %>님</li>
<%} else{ %>
<%--비로그인 상태 --%>
<li id="userJoinOpen">회원가입</li>
<li id="loginOpen">로그인</li>
<%} %>
</ul>
<div>
<form action="/main/search" method="get">
<span id="searchIcon"></span>
<input type="text" name="mSearch" id="mSearch" placeholder="검색어를 입력해주세요" autocomplete="off">
</form>
</div>
<h1 title="영추영추" onclick="location.href='/'"></h1>
<div id="nav">
<ul>
<li><a href="<%request.getContextPath();%>/product/search">제품조회</a></li>
<li><a href="<%request.getContextPath();%>/nutrient/self">영양소추천</a></li>
<li><a href="<%request.getContextPath();%>/review/list">제품후기</a></li>
<li><a href="<%request.getContextPath();%>/product/best">BEST3</a></li>
<li><a href="<%request.getContextPath();%>/notice/list">고객센터</a>
<ul class="subMenu">
<li><a href="<%request.getContextPath();%>/notice/list">공지사항</a></li>
<li><a href="<%request.getContextPath();%>/inquiry/list">1:1 문의</a></li>
</ul>
</li>
</ul>
</div>
</div>
<!-- header끝 -->

<!-- 로그인 시작 -->
<div id="login" style="display:none;width:400px;height:450px;background:#fff;border:2px solid #eee;position:fixed;top:10%;left:50%;margin-left:-200px;z-index:1000;">
<form action="/user/login" method="post">
<span id="loginClose" style="cursor:pointer;float:right;margin-right:10px;margin-top:5px;font-size:20px;font-weight: bold;">X</span>
<h3 style="clear:right;font-size:25px;width:75px;margin:0 auto;">로그인</h3>
<div style="width:300px;margin:50px auto 0;">
<label for="userid" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userid" name="userid" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="아이디" type="text">
</div>
<div style="clear:left;width:300px;margin:15px auto;">
<label for="userpw"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
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
<!-- 로그인 끝 -->
<!-- 회원가입 시작-->
<div id="userJoin" style="display:none;width:400px;height:600px;background:#fff;border:2px solid #eee;position:fixed;top:10%;left:50%;margin-left:-200px;z-index:1000;">
<form action="/user/signup" method="post" name="frm">
<span id="userJoinClose" style="cursor:pointer;float:right;margin-right:10px;margin-top:5px;font-size:20px;font-weight: bold;">X</span>
<h3 style="clear:right;font-size:25px;width:100px;margin:0 auto;">회원가입</h3>
<div style="width:300px;margin:50px auto 0;">
<label for="joinUserId" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="joinUserId" name="joinUserId" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="아이디는 4~6자로 입력" type="text" autocomplete="off">
<span id="rescheckid" style="font-size:14px;color:red;height:18px;display:block;"></span>
</div>
<div style="width:300px;margin:0 auto;">
<label for="joinUserNick" style="cursor:pointer;background:url(../../resources/img/user_person_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="joinUserNick" name="joinUserNick" style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="닉네임" type="text">
<span id="reschecknick" style="font-size:14px;color:red;height:18px;display:block;"></span>
</div>

<div style="clear:left;width:300px;margin:0 auto 18px;">
<label for="userJoinpw"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinpw" name="userJoinpw"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="비밀번호는 영문, 숫자포함 6~8자" type="password" autocomplete="off">
</div>

<div  style="width:300px;margin:0 auto;">
<label for="userJoinpwcheck"  style="cursor:pointer;background:url(../../resources/img/key_pass_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinpwcheck" name="userJoinpwcheck"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="비밀번호 확인" type="password" autocomplete="off">
</div>

<div  style="width:300px;margin:18px auto;">
<label for="userJoinphone"  style="cursor:pointer;background:url(../../resources/img/mobile_icon.png) #ccc no-repeat center;background-size:25px;width:35px;height:35px;float:left;border-top-left-radius: 5px;border-bottom-left-radius: 5px;"></label>
<input id="userJoinphone" name="userJoinphone"  style="width:250px;height:35px;border-top-right-radius: 5px;border-bottom-right-radius: 5px;border:1px solid #ccc;" placeholder="'-' 제외하고 입력해주세요" type="text"">
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
<!-- 회원가입 끝 -->


