<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--<<<<<<< jyj-->
    
<!-- 웹 페이지 하단부 파일 -->
<!-- <hr> -->
<!--=======-->
<!-- footer 시작-->
<div id="footer" >
	<small>영추영추Inc.</small>
<ul>
<li style="font-weight:bold;">팀 명: 영양 챙겨야조</li>
<li style="cursor:pointer;" onclick="alert('준비중입니다')">개인정보정책</li>
<li style="cursor:pointer;"  onclick="alert('준비중입니다')">이용약관</li>
<button type = "button" onclick="location.href='/managerMain.jsp'"class="btn-primary">관리자페이지</button>

<button onclick="location.href='<%=request.getContextPath() %>/manager/login'"class="btn-primary">관리자로그인[TEST]</button>

</ul>
</div>
<!-- footer끝 -->
</body>
</html>
