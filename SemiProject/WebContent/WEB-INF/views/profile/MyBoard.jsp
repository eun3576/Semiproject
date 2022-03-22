<%@page import="dto.Review"%>
<%@page import="dto.Profile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<% List<Profile> list = (List)request.getAttribute("profile");%>

<% List<Review> Blist = (List)request.getAttribute("Blist"); %>



<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>




<style type="text/css">


#submenu{
	text-align:center;
	line-height:40px;
	background:#B0E0E6;
	height:190px;
}

th{
	text-align:center;
}

a{
	color:black;
}

a:hover{
	text-decoration:none;
	color:blue;
}

/* footer 하단 고정 [S] */
body {
    margin: 0;
    padding: 0;
    height: 100%;
}

#body-wrapper {
    min-height: 100%;
    position: relative;
}

#body-content {
    margin-top: 100px;
    padding-bottom: 100px; /* footer의 높이 */
}

footer {
    width: 100%;
    height: 100px; /* footer의 높이 */
    position: absolute;  
    bottom: 0;
    left: 0;
}
/* footer 하단 고정 [E] */


</style>

<body>

<div id="body wrapper">
  <div id="body-conten">

<div class="container text-center">
<br><br>

<div class="col-xs-offset-11 col-xs-1 btn-xs" onclick="location.href='/inquiry/write'"><button>문의하기</button></div>

<br><br>

<div id="submenu" class="col-xs-2">
<h3><strong><a href="/profile/main">마이페이지</a></strong></h3>
<span><a href="/profile/board">나의게시판</a></span><br>
<span><a href="/profile/main">회원수정</a></span><br>
<span><a href="/profile/out">회원탈퇴</a></span>
</div>

<div class="container">
<!--     <div class="row"> -->
        <div class="col-xs-10">
            <div class="col-sm-2"></div>
                <div class="col-sm-9">
                    <h2 class="text-center">나의 게시판</h2>
<br>     
<hr>                
                     
   <table class="table table-striped">
   <tr class="info">
   		<th>제목</th>
   		<th>조회수</th>
   		<th>작성일</th>
   	</tr>
   	
 <%	for(int i=0; i<Blist.size(); i++) { %>
 
<tr>
	<td><%=Blist.get(i).getTitle() %></td>
	<td><%=Blist.get(i).getViews() %></td>
	<td><%=Blist.get(i).getWriteDate() %></td>
</tr>
<%	} %>

   </table>                  
  
                </div>
        </div> <!-- col-sm-12 --> 
</div> <!-- container end-->

</div> <!-- class="col-xs-2"> -->



</div> <!-- body-conten -->
  
    <!-- footer삽입 -->
<footer class="footer"><%@include file="../layout/footer.jsp" %></footer>
  
</div>



</body>
