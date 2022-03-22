<%@page import="dto.UserInfo"%>
<%@page import="dao.impl.ManagerDaoImpl"%>
<%@page import="dao.face.ManagerDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	


<%
	/* List<UserInfo> userList = (List) request.getAttribute("userList"); */
%>

	


<style>
	.pagination{
		padding: 10px 0;
		margin: 0 auto;
		text-align: center;
	}
	.pagination > a {
		color:grey;
		text-decoration: none;
		display:inline-block; 
		min-width:35px;
		
		border:1px solid transparent;
		padding:0.5rem;
		text-align: center;
	}
	
	.pagination > a:hover,
	.pagination > a.on {
		border:1px solid lightgray;
		color:#ff9f43;
	}
	.q1-a1{
		background-color: rgb(231, 241, 253,0.5);
	}
	
 	.admin-qna-row{ 
 		width: 100%;
 		margin: 5px auto;
 		padding-left: 10px;
 	} 
	.notice-regit-reply {
		width: 98%;
		min-height: 100px;
		margin: 10px auto ;
 	    padding: 5px 0 ;
	    border: 2px solid #cfcfcf;
	    border-radius: 6px;
	    box-sizing: border-box;
	}
	.comment_inbox_text{
	    display: block;
	    width: 100%;
	    min-height: 100px;
	    border: 0;
	    font-size: 13px;
	    resize: none;
	    box-sizing: border-box;
	    background: transparent;
	    color: var(--skinTextColor);
	    outline: 0;
	}
	.form-btn {
		border:none;
	}
	.form-btn.form-btn-normal {
		background-color: white;
		color:gray;
		border:1px solid gray;	
		width: 50px;
		height: 30px;
		padding: 5px;
	}
	
	.manager_user_box{
		margin: 5px;
	}
	
	.manager_user_input{
		width: 90%;
	    border: none;
	    border-bottom: 1px solid #d1d1d1;
	    padding: 0px 10px;
	    margin: 0px 5px;
	}
	
	.manager_user_input:checked{
		border-bottom: 1px solid black;
	}
	
	.manager_user_btn{
		width: 10%;
	    border: 1px solid #cdcdcd;
	    background-color : #fff;
	    margin: 0px 5px;
	    cursor: pointer;
	}
</style>

<%@ include file="../template/managerSidebar.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function viewContent(obj){
		var con = document.querySelectorAll(".con");
		var ans = document.querySelectorAll(".ans");
				
		if(con != null){
			for(var i=0; i<con.length; i++){
				con[i].style.display = "none";	
			}				
		}
		
		if(ans != null){
			for(var i=0; i<ans.length; i++){
				ans[i].style.display = "none";	
			}				
		}
		
		var q1 = document.querySelectorAll(".q"+obj.id);
		var a1 = document.querySelectorAll(".a"+obj.id);
		
		if(q1 != null){
			for(var i=0; i<q1.length; i++){
				q1[i].style.display = "";	
			}				
		}
		
		if(a1 != null){
			for(var i=0; i<a1.length; i++){
				a1[i].style.display = "";	
			}				
		}
		
	}
</script>
<script>
	
	function userSearchBtn() {
		var text = document.getElementById("search").value;
		
		$(location).attr("href", "<%=request.getContextPath() %>/manager/user?search=" + text);
	}
	
</script>
<section>
	<div class="admin-content_area">
		<div class="admin-content">
			<div class="admin-content_title">제품 관리</div>
		</div>
	</div>
	
	<div class="admin-content_area">
		<div class="admin-content">
			<div class="admin-content_title">회원 검색</div>
			<div class="align-row choice-genre-area manager_user_box">
				<input type="text"  class="manager_user_input" id="search" name="search" placeholder="검색할 아이디를 입력해주세요.">
				<button class="manager_user_btn" onclick="userSearchBtn();">검색</button>
			</div>
			
		</div>
	</div>
		<div class="admin-content_area">
			<div class="admin-content">
				<div class="admin-content_title">회원 목록</div>
				<div class="align-row choice-genre-area">
					<div class="search-table" style="min-height: 550px;">
						<table class="table table-border table-hover table-striped" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 1%;">번호</th>
									<th style="width: 1%;">아이디</th>
									<th style="width: 1%;">비밀번호</th>
									<th style="width: 1%;">닉네임</th>
									<th style="width: 1%;">성별</th>
									<th style="width: 1%;">상태</th>
									<th style="width: 1%;">전화번호</th>
									<th style="width: 1%;">삭제</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					</div>
				</div>
				<!-- 페이지 네비게이션 자리 -->
				<div class="pagination">
				
					
					
				</div>
					
				<form class="page-form" action="notice.jsp" method="post">
					<input type="hidden" name="pageNo">
				</form>
			</div>
			
		</div>
</section>
</body>
</html>