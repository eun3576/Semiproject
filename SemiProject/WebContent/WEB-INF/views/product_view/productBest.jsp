<%@ page import="dto.Product"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<% List<Product> productList = (List) request.getAttribute("productList"); %>

<style type="text/css">

img {
	width: 180px;
	height: 180px;
}
.productImg {
	float: left;
}
.bestProduct {
	border-radius: 20px 20px;
	background-color: #eaf3fd;
	border: 2px solid #666;
	padding: 20px;
	margin: 8px;
	height: 220px;
}

.productContent {
	text-align: center;
}

</style>

<div class="container">
<hr>


<% for(int i=0; i<productList.size(); i++) { %>
<div class="bestProduct">

	<div class="productImg">
	<img alt="상품 이미지" src="<%=request.getContextPath() %>
	/upload/<%=productList.get(i).getProduct_img() %>">
	</div>
	<div class="productContent">
	<h2 style="color: #00db58a8;">조회 수 BEST<%=i+1 %></h2>
	<small>제품명:&nbsp;<%=productList.get(i).getProduct_name() %><br>
	제품 설명:&nbsp;<%=productList.get(i).getProduct_content() %><br>
	카테고리:&nbsp;<%=productList.get(i).getCategory_name() %></small>
	</div>
	
</div>
<% } %>

<hr>
</div><!-- .container end -->


<%@ include file="../layout/footer.jsp" %>

