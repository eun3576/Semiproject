<%@page import="dto.Review"%>
<%@page import="dto.Profile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<% List<Profile> list = (List)request.getAttribute("profile");%>

<% List<Review> Blist = (List)request.getAttribute("Blist"); %>

<!-- 구글웹폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

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

tr:hover { 
	background-color: lightgray; 

}

body{ 
    font-family : 'Jua', sans-serif;
}

</style>



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
<!--                 <div class="col-sm-10"> -->
<!--                     <h2 class="text-center">나의 게시판</h2> -->
<br>     
<hr>                
                     
   <table class="table table-striped">
   <tr class="info">
   		<th width="20%">작성일</th>
   		<th id="titile" width="70%">제목</th>
   		<th width="10%">조회수</th>
   	</tr>
   	
 <%	for(int i=0; i<Blist.size(); i++) { %>
 
<tr>
	<td><%=Blist.get(i).getWriteDate() %></td>
	<td><a href="/review/view?review_no=<%=Blist.get(i).getReview_no() %>"><%=Blist.get(i).getTitle() %></a></td>
	<td><%=Blist.get(i).getViews() %></td>
</tr>
<%	} %>

   </table>                  
  
                </div>
<!--         </div> col-sm-10  -->
</div> <!-- container end-->

</div> <!-- class="col-xs-2"> -->

<%@ include file="../profile/paging.jsp" %>

    <!-- footer삽입 -->
<footer class="footer"><%@include file="../layout/footer.jsp" %></footer>
  


