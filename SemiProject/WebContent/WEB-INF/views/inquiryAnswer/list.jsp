<%@ page import="dto.ManagerInquiryAnswer"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../managerLayout/header.jsp"%>

<% List<ManagerInquiryAnswer> inquiryAnswerList = (List) request.getAttribute("inquiryAnswerList"); %>

<script type="text/javascript">
	$(document).ready(function() {

		// 글쓰기
		$("#btnWrite").click(function() {
			location.href = "/inquiryAnswer/write"
		})
		
		//페이지 접속 시 입력창으로 포커스 이동
		$("input").eq(0).focus();
		
		//입력 창에서 엔터키 입력 시 submit하도록 한다 //검색창을 form으로 감싸주어야 합니다
		$("input").eq(0).keydown(function( e ) {
			if( e.keyCode == 13 ) { //엔터키
				$(this).parents("form").submit();
			}
		});
	})
</script>

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
	
	.manager_box{
		margin: 5px;
	}
	
	.manager_input{
		width: 90%;
	    border: none;
	    border-bottom: 1px solid #d1d1d1;
	    padding: 0px 10px;
	    margin: 0px 5px;
	}
	
	.manager_btn{
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
	$(location).attr("href", "<%=request.getContextPath() %>/inquiryanswer/list?search=" + text);	
}
</script>

<section>
	<div class="admin-content_area">
		<div class="admin-content">
			<div class="admin-content_title">1:1문의 관리 <button id="btnWrite" class=" btn-primary pull-right">1:1문의 답변하기</button> </div>
		</div>
	</div>

<!-- 검색창 -->
<form>
	<div class="admin-content_area">
		<div class="admin-content">
			<div class="admin-content_title">1:1문의 검색</div>
			<div class="align-row choice-genre-area manager_box">
			<input type="text"  class="manager_input" id="search" name="search" placeholder="검색할 내용을 입력해주세요.">
			<button class="manager_btn" onclick="userSearchBtn();">검색</button>
  			</div>
  		</div>
	</div>
</form>

<div class="admin-content_area">
			<div class="admin-content">
				<div class="admin-content_title">1:1문의 목록</div>
				<div class="align-row choice-genre-area">
					<div class="search-table" style="min-height: 550px;">
						<table class="table table-border table-hover table-striped" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 0.1%;">답변번호</th>
									<th style="width: 0.1%;">작성날짜</th>
									<th style="width: 0.1%;">작성자</th>
									<th style="width: 0.1%;">문의번호</th>
									<th style="width: 1%;">내용</th>
								</tr>
							</thead>
							<tbody>
							<%for(int i=0;  i < inquiryAnswerList.size(); i++){ %>
								<tr>
									<td><%=inquiryAnswerList.get(i).getAnswerNo()%></td>
									<td><%=inquiryAnswerList.get(i).getWriteDate()%></td>
									<td>관리자<%=inquiryAnswerList.get(i).getManagerNo()%></td>
									<td><%=inquiryAnswerList.get(i).getInquiryNo()%></td>
									<td><a href="./view?InquiryNo=<%=inquiryAnswerList.get(i).getInquiryNo()%>"><%=inquiryAnswerList.get(i).getContent()%></td>
								</tr>
							<%} %>
							</tbody>
						</table>
					</div>
				</div>

				<%@ include file="../inquiryAnswer/paging.jsp"%>
				
				</div>
			</div>
		</section>
