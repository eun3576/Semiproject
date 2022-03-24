<%@ page import="dto.Product"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<% List<Product> productList = (List) request.getAttribute("productList"); %>

<style type="text/css">

.bestProduct{
    padding: 20px;
    margin: 10px;
 	border: 2px solid #666; 
 	background-color: #eaf3fd;
 	border-radius: 20px 20px;
 	overflow: auto;
}

.productImg {
	float: left;
	width: 200px;
	height: 200px;
	border: 1px solid black;
	margin-right: 40px;
}

img{
width: 100%;
height: 100%;
}


</style>

<div class="container">
<hr>


<% for(int i=0; i<productList.size(); i++) { %>
<div class="bestProduct">

	<div><h2 align="center" style="color: #00db58a8; margin-top: 0px;">BEST<%=i+1 %>&nbsp;&nbsp;조회 수(<%=productList.get(i).getProduct_views() %>)</h2></div>
	
	<div class="productImg">
	<img alt="상품 이미지" src="<%=request.getContextPath() %>
	/upload/<%=productList.get(i).getProduct_img() %>">
	</div>
	<div class="productContent">
	<small>
	제품명:&nbsp;<%=productList.get(i).getProduct_name() %>&nbsp;&nbsp;
	카테고리:&nbsp;<%=productList.get(i).getCategory_name() %><br>
	제품 설명:&nbsp;<%=productList.get(i).getProduct_content() %><br>
	</small>
	</div>
	
</div>
<% } %>

<hr>
</div><!-- .container end -->


<%@ include file="../layout/footer.jsp" %>

