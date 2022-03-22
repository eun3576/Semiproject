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

function check(){

	//수정 입력값 받기
	var nickname = $('#nick').val()
	var phonenumber = $('#phone').val()
	var password = $('#pass').val()
	
	//변경되는 값이 들어가지 않을 때 
	var data = { }
	
	if (nickname != null ){
		data.nickname = nickname
	}
	if (phonenumber != null ){
		data.phonenumber = phonenumber
	}
	if (password != null ){
		data.password = password
	}
	
	// console.log(data)
	
	ajax(data)
	

}

//오라클에서 확인해야함.

function ajax(data){
	$.ajax({
		type:"post"
		,url:"/profile/list"
		,data:data
		, dataType:"html"
		, success:function(res){
			console.log("AJAX 성공")
			
			alert("수정되었습니다")
			
			
		}
		, error:function(){
			console.log("AJAX 실패")
		}
	})
}



</script>


 
 <% 

 Profile profile = (Profile)request.getAttribute("profile");
 
 %>

<style type="text/css">


#submenu{
	text-align:center;
	line-height:40px;
	background:#B0E0E6;
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
<!--     <div class="row"> -->
        <div class="col-xs-10">
            <div class="col-sm-2"></div>
                <div class="col-sm-9">
                    <h2 class="text-center">회원 정보 수정</h2>
<br>                     
                     
                    <table class="table table-striped">
                      <tr>
                        <td class="info">아이디</td>
                         <td class="info"><%= profile.getId() %></td> 
                      </tr>
                       
                      <tr>
                        <td>닉네임</td>
                        <td>
                        <input type="text"  value="<%= profile.getNickname() %>"  id="nick" class="form-control">
                        </td>
                      </tr>
                       
                      <tr>
                        <td>휴대전화</td>
                        <td>
                        <input type="text" value="<%= profile.getPhonenumber() %>"  id="phone" class="form-control">
                        </td>
                      </tr>
                       
                      <tr>
                        <td>패스워드</td>
                        <td>
                        <input type="password" value="<%= profile.getPassword() %>" id="pass" class="form-control">
                        </td>
                      </tr>
                       
                    <tr>
                        <td colspan="2" class="text-center">
                         <button type="submit" onclick="check();" class="btn btn-info"><a href="/profile/list">확인</a></button>
                         
                         </td>    
                    </tr>
                    </table>
                 
                </div>
        </div> <!-- col-sm-12 -->
  <!--   </div> --><!-- row -->
</div> <!-- container end-->

</div> <!-- class="col-xs-2"> -->





</body>
</html>