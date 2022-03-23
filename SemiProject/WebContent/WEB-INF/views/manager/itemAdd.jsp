<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../template/managerSidebar.jsp" %>
<section>
	<div class="admin-content_area">
		<div class="admin-content">
			<div class="admin-content_title">제품 관리</div>
		</div>
	</div>
	
	<div class="admin-content_area">
		<div class="admin-content">
			<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
			<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>
			
			<script type="text/javascript">
			$(document).ready(function() {
				
				//작성버튼 동작
				$("#btnWrite").click(function() {
					
					//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
					submitContents( $("#btnWrite") );
					
					$("#addform").submit();
				});
				
				//취소버튼 동작
				$("#btnCancel").click(function() {
					history.go(-1);
				});
				
			});
			
			//스마트에디터에 작성한 내용을 <textarea>에 반영하는 함수
			function submitContents( elClickedObj ) {
				
				//에디터의 내용을 #content에 반영한다
				oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
				
				try {
					//<form>태그의 submit을 수행한다
					elClickedObj.form.submit();
				} catch(e) {}
				
			}
			</script>
			
			<style type="text/css">
				#content {
					width: 100%;
				}
							
				.item-add-title{
					font-size: 16px;
				    padding: 15px;
				    color: #949ca6;
				    width: 100%;
				    border-bottom: 1px solid rgba(0,0,0,0.2);
				}
				
				.add-btn{
					height: 30px;
				    width: 100px;
				    margin: 5px 0;
				    background-color: white;
				    border: 1px solid gray;
				    border-radius: 5px;
				}
			</style>
			
			<div class="container">
			
			<h3 class="item-add-title">게시글 쓰기</h3>
			<hr>
			
			<div>
			<form id="addform" action="/manager/itemAdd" method="post" enctype="multipart/form-data">
			
			<table class="table table-bordered">
			<tr><td class="info">제품명</td><td><input type="text" name="product_name" style="width:100%"></td></tr>
			<tr><td class="info">카테고리</td><td>
			<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
				  <input type="checkbox" class="form-check-input" id="btncheck1" autocomplete="off" name="category" value="child">
				  <label for="btncheck1">어린이 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck2" autocomplete="off" name="category" value="woman">
				  <label for="btncheck2">여성 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck3" autocomplete="off" name="category" value="man">
				  <label for="btncheck3">남성 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck4" autocomplete="off" name="category" value="aged">
				  <label for="btncheck4">노인 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck5" autocomplete="off" name="category" value="eye">
				  <label for="btncheck5">눈 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck6" autocomplete="off" name="category" value="intestine">
				  <label for="btncheck6">장 건강</label>
				  <input type="checkbox" class="form-check-input" id="btncheck7" autocomplete="off" name="category" value="vitamin">
				  <label for="btncheck7">비타민</label>
				  <input type="checkbox" class="form-check-input" id="btncheck8" autocomplete="off" name="category" value="exercise">
				  <label for="btncheck8">운동</label>
			</div>
			</td></tr>
			<tr><td class="info" colspan="2">본문</td></tr>
			<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
			</table>
			
			첨부파일 <input type="file" name="file" multiple>
			썸네일 <input type="file" name="Thum">
			
			</form>
			</div>
			
			<div class="text-center" style="margin: 5px;">	
				<button type="button" id="btnWrite" class="btn btn-info add-btn">작성</button>
				<button type="button" id="btnCancel" class="btn btn-danger add-btn">취소</button>
			</div>
			
			<!-- .container -->
			</div>
			
			
			
			<!-- <textarea>태그에 스마트에디터2를 스킨 적용하는 스크립트 -->
			<script type="text/javascript">
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "content", //스킨을 적용할 <textarea>의 id를 적어준다
				sSkinURI: "../resources/se2/SmartEditor2Skin.html",
				fCreate: "createSEditor2"
			})
			</script>
			
			
		</div>
			
	</div>
</section>