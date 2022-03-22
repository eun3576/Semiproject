<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./managerLayout/header.jsp" %>

<div class="container">

<div class = "text-center">


<%-- 비로그인 상태 --%>
<% if( session.getAttribute("login") == null ) {%>
<strong>관리자 계정의 로그인이 필요합니다</strong><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/manager/login'"class="btn-primary">로그인</button>
<% } %>

<%-- 로그인 상태 --%>
<% if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) {%>
<strong><%=session.getAttribute("id") %>님, 환영합니다</strong><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/managernotice/list'" class="btn-primary">공지사항 관리</button>
<button onclick="location.href='<%=request.getContextPath() %>/reviewcomment/list'" class="btn-success">리뷰댓글 관리</button>
<button onclick="location.href='<%=request.getContextPath() %>/inquiryanswer/list'" class="btn-info">1:1문의 관리</button>
<button onclick="location.href='<%=request.getContextPath() %>/ %>'" class="btn-warning">메인페이지배너 관리</button>

<!-- 메인페이지배너관리 주소 기입해야합니다 -->

<hr>
<button onclick="location.href='<%=request.getContextPath() %>/manager/logout'"class="btn-danger">로그아웃</button>
<% } %>

</div>

</div><!-- .container -->

<%@ include file="./managerLayout/footer.jsp" %>