<%@page import="dto.ProductCategory"%>
<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Product> list = (List)request.getAttribute("list");
	List<ProductCategory> categoryList = (List)request.getAttribute("categoryList");
%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

function detail(product_no){
	detailajax(product_no)
}

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

.row{
	height:300px;
	display: flex;
    justify-content: center;
    margin: 30px 0 30px 0;
}

.product{
	width:300px;
	margin:20px 20px;
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

<div class="row">

<%for(int i=0; i<list.size(); i++) {%>

<div class="product" style="border:1px solid #ccc;">
<table>
<tr>
	<th style="text-align:center;"><a onclick="detail(<%=list.get(i).getProduct_no() %>);"><%=list.get(i).getProduct_name() %></a></th>
</tr>


<tr>
	<!-- 여기 썸네일 파일 추가해야함 -->
	<td class="bottomLine"><img src="https://cdn.pixabay.com/photo/2021/12/01/15/00/christmas-background-6838204_960_720.png" width="80%" height="200" onclick="detail(<%=list.get(i).getProduct_no() %>);" /></td>
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

	<% } %>


</div>











