<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 웹 페이지 하단부 파일 -->
<!-- footer 시작-->

<style>
button{
button:hover{
  background:#fff;
  color:#a6a3a4;
	}
}
</style>

<div id="footer" >
	<small>Copyright &copy; 영추영추 2022</small>
<ul>
<li style="font-weight:bold;">팀 명: 영양 챙겨야조</li>
<li style="cursor:pointer;" onclick="alert('준비중입니다')">개인정보정책</li>
<li style="cursor:pointer;"  onclick="alert('준비중입니다')">이용약관</li>

<button onclick="location.href='<%=request.getContextPath() %>/manager/login'">관리자로그인</button>

</ul>
</div>
<!-- footer끝 -->
</body>
</html>
