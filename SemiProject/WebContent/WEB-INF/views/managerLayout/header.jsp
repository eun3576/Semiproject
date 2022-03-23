<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<!-- 부트 스트랩은 jquery동작 기반 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 부트 스트랩 코드 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title> 영추영추 [관리자 페이지] </title>

<style type="text/css">

#header, #footer {
	text-align: center;
}

#header a {
	text-decoration:none;
}

ul.nav {
	/* ul태그의 기본 리스트스타일(disc) 없애기 */
	list-style-type: none;
	
	/* 기본 여백 제거 */
	padding: 0;
	margin: 0;
}

ul.nav > li {
	/* 수평으로 일렬 배치하기 */
	float: left;
	
	/* 배경색 지정하기 */
	background: #666;
	
	/* 항목 크기 변경하기(배경색포함 변경) */
/* 	padding: 10px 5px; */

	/* 항목 크기 변경하기(배경색미포함 변경) */
	/* 	-> 항목의 간격이 멀어짐 */
/* 	margin: 10px 5px; */

	/* 줄간격을 이용한 높이 조절(배경색포함 변경) */
	line-height: 40px;
	
	/* 테두리를 이용한 메뉴 항목 구분선 만들기 */
/* 	border: 1px solid red; */
	border-right: 1px solid #fff;
	
	
	
	/* 서브메뉴(absolute)의 위치 기준점으로 설정하기 */
	position: relative;
}

/* 메인 메뉴의 항목 텍스트 */
ul.nav > li > a {
	/* 글자 색상 */
	color: #fff;
	
	/* 글자 꾸밈선 제거(underline 제거) */
	text-decoration: none;
	
	/* 글자 스타일 지정 */
	font-size: 15px;	/* 크기 */
	font-weight: bold;	/* 굵게 */
	font-family: "굴림";/* 글꼴 */
	
	/* 내부 여백 */
	/* 	-> 링크 클릭이 가능한 영역 */
	padding: 10px 15px;
	
	/* 외부 여백 */
	/* 	-> 링크 클릭이 불가능한 영역 */
	margin: 0 3px;
}

/* 메인 메뉴의 항목 텍스트에 마우스를 올렸을 때 */
ul.nav > li > a:hover {
	color: tomato;
}


</style>

</head>
<body>

<!-- <div id="header"> <h1><a href="/managerMain">관리자페이지</a></h1>

</div> -->


 