<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영추영추 [관리자페이지]</title>
    <link rel="stylesheet" type="text/css" href="<%= root%>/resources/se2/css/manager/template.css">
    <link rel="stylesheet" type="text/css" href="<%= root%>/resources/se2/css/manager/managerPage.css">
    
</head>
<body>
	<aside>
		<div class="admin-logo"><a href="<%=root%>/">영추영추</a><p class="admin-label">관리자페이지</p></div>
		<ul>
			<li class="admin-aside-top_menu"><span class="admin-aside-top_text">회원</span>
				<ul class="admin-aside-side_menus">
					<li><a href="<%=root%>/manager/user">회원 조회/관리</a></li>
				</ul>
			</li>
			<li class="admin-aside-top_menu"><span class="admin-aside-top_text">제품</span>
				<ul class="admin-aside-side_menus">
					<li><a href="<%=root%>/manager/itemAdd">제품 등록</a></li>
					<li><a href="<%=root%>/manager/item">제품 조회/관리</a></li>
				</ul>
			</li>
			<li class="admin-aside-top_menu"><span class="admin-aside-top_text">게시글</span>
				<ul class="admin-aside-side_menus">
					<li><a href="<%=root%>/manager/review">게시글 조회/관리</a></li>
<!-- <<<<<<< mej -->
					<li><a href="<%=root%>/reviewcomment/list">댓글 관리</a></li>
					<li><a href="<%=root%>/inquiryanswer/list">1:1문의 관리</a></li>
					<li><a href="<%=root%>/managernotice/list">공지사항 관리</a></li>
				</ul>
			</li>
			<li class="admin-aside-top_menu"><span class="admin-aside-top_text"></span>
				<ul class="admin-aside-side_menus">
					<li><a href="<%=root%>/chat/manager">실시간 채팅관리</a></li>
<!-- ======= -->
<!-- >>>>>>> main -->
				</ul>
			</li>
		</ul>
	</aside>
	