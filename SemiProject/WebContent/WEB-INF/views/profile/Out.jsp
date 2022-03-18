<%@page import="dto.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>


<script>

function outbutton(str) { 
	
	if(str.search(/\s/) != -1) {  
		
		alert("비밀번호") //공백이 있는 경우
		
		return true; 
		
	} else { 
		
		return false; } }



// function out() {



//   alert("탈퇴 되었습니다.");

//   window.location.href = "/main";

// } 

</script>

 
 <% 

Profile profile = (Profile)request.getAttribute("profile");
 
 %>

<style type="text/css">


#submenu{
	text-align:center;
	line-height:40px;
	background:#78CCC8;
	height:150px;
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

</style>

<div class="container text-center">
<br><br>
<div class="col-xs-offset-11 col-xs-1 btn-xs" onclick="location.href='/inquiry/write'"><button>문의하기</button></div>
<br><br>
<div id="submenu" class="col-xs-2">
<h3><strong><a href="/profile/main">마이페이지</a></strong></h3>
<span><a href="/profile/list">회원수정</a></span><br>
<span><a href="/profile/out">회원탈퇴</a></span>
</div>


 
<div class="container">
    <div class="row">
         <div class="col-xs-10">
            <div class="col-sm-2"></div>
                <div class="col-sm-9">
                    <h2 class="text-center">회원 탈퇴</h2>
                     
<br>
                     
                    <table class="table table-striped">
                      <tr>
                        <td>아이디</td>
                        <td><%= request.getParameter("id") %></td>
                      </tr>
                       
                      <tr>
                        <td>패스워드</td>
                        <td>
                        <input type="password"  id="password" class="form-control">
                        </td>
                      </tr>
                       
                    <tr>
                        <td colspan="2" class="text-center">
                         <button id="outbutton" class="btn btn-danger">탈퇴</button>
                         
                         <!-- 탈퇴하고 난 페이지 -> 알러트 -> 메인 -->
                    
                         
                         
                         <!-- 메인페이지로 가는 버튼 -->
                        <a href="/main"><input type="submit" value="메인" class="btn btn-info"></a>
                         

                         </td>    
                    </tr>
                           
                    </table>
                 
                </div>
        </div> <!-- col-sm-12 -->
    </div><!-- row -->
</div> <!-- container end-->

</div>


</body>
</html>