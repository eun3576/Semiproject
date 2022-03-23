<%@page import="dto.ProductCategory"%>
<%@page import="dto.Product"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	//제품 리스트 가져오기
	List<Product> list = (List)request.getAttribute("list");
	//카테고리 리스트 가져오기
	List<ProductCategory> categoryList = (List)request.getAttribute("categoryList");
	
	//URL에 전달될 값을 위한 변수 생성 
	//-> productPaging.jsp에서 사용하기위함
	String child = "";
	String woman = "";
	String man = "";
	String aged = "";
	String eye = "";
	String intestine = "";
	String vitamin = "";
	String exercise = "";
	
	//쿼리스트링으로 전달된 값들 가져오기 
	//-> productPaging.jsp에서 사용하기위함
	if("child".equals((String)request.getAttribute("child"))){
		child = (String)request.getAttribute("child");
	}
	if("woman".equals((String)request.getAttribute("woman"))){
		woman = (String)request.getAttribute("woman");
	}
	if("man".equals((String)request.getAttribute("man"))){
		man = (String)request.getAttribute("man");
	}
	if("aged".equals((String)request.getAttribute("aged"))){
		aged = (String)request.getAttribute("aged");
	}
	if("eye".equals((String)request.getAttribute("eye"))){
		eye = (String)request.getAttribute("eye");
	}
	if("intestine".equals((String)request.getAttribute("intestine"))){
		intestine = (String)request.getAttribute("intestine");
	}
	if("vitamin".equals((String)request.getAttribute("vitamin"))){
		vitamin = (String)request.getAttribute("vitamin");
	}
	if("exercise".equals((String)request.getAttribute("exercise"))){
		exercise = (String)request.getAttribute("exercise");
	}
	
%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

//선택된 제품번호를 전달받아 상세보기 ajax호출
function detail(product_no){
	detailajax(product_no)
}

//제품번호를 이용하여 서버와 통신
function detailajax(product_no){
	$.ajax({
		type:"get"
		,url:"/product/detail"
		,data:{
			product_no:product_no
		}
		, dataType:"html"
		, success:function(res){
			console.log("AJAX 성공")
			
			$('#resultLayout').html(res)
		}
		, error:function(){
			console.log("AJAX 실패")
		}
	})
}

</script>


<style type="text/css">

.main_list {
    width: 1000px;
    margin: 0 auto;
    text-align: center;
}

.list_start {
    text-align: center;
}

.list_detail {
    display: inline-block;
    width: 310px;
    height: 280px;
	border: 2px solid #f2dede;
 	border-radius: 30px 30px; 
    margin: 6px;
    padding: 9px;
    background-color: #f2dede;
}

.paging{
	display: flex;
    justify-content: center;
    margin-top: 50px;
}

table, th{
	width:100%;
	text-align:center;
	margin:0 auto;
}

.bottomLine{
	text-align:center;
	margin:0 auto;
}



</style>


<div class="main_list">
<%for(int i=0; i<list.size(); i++) {%>
	<div class="list_detail">
		<div class="product">
			<table>
				<tr>
					<th style="text-align:center;"><div style="min-height:40px;"><a onclick="detail(<%=list.get(i).getProduct_no() %>);"><%=list.get(i).getProduct_name() %></a></div></th>
				</tr>
				
				<tr>
					<!-- 여기 썸네일 파일 추가해야함 -->
					<td class="bottomLine"><img src="<%=request.getContextPath() %>/upload/<%=list.get(i).getProduct_img() %>" width="80%" height="200" onclick="detail(<%=list.get(i).getProduct_no() %>);" /></td>
				</tr>
				
				<tr>
					<td>
					<%for(int j=0; j<categoryList.size(); j++){ %>
						<%if(list.get(i).getProduct_no() == categoryList.get(j).getProduct_no()) {%>
							#<%=categoryList.get(j).getCategory_name() %>
						<% } %>
					<% } %>
					</td>
				</tr>
			</table>
		</div>
	</div>
<% } %>
</div>

<div class="paging">
	<%@ include file="./layout/productPaging.jsp" %>
</div>



