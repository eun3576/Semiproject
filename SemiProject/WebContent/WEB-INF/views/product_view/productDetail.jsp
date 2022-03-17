<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Product product = (Product)request.getAttribute("product");%>

<style type="text/css">

.detailpage{
	width:100%;
}

.title{
	display: flex;
    justify-content: center;
    margin: 30px 0 30px 0;
}

</style>

<div class="detailpage">

<div class="title">

<img src="https://cdn.pixabay.com/photo/2021/12/01/15/00/christmas-background-6838204_960_720.png" width="200" height="200">

<div style="margin:0 100px;">
<h4><%=product.getProduct_name() %></h4>
<div>
	<% if("child".equals(product.getCategory_name())) { %>
	#어린이 건강
	<% }else if("woman".equals(product.getCategory_name())) { %>
	#여성 건강
	<% }else if("man".equals(product.getCategory_name())) { %>
	#남성 건강
	<% }else if("aged".equals(product.getCategory_name())) { %>
	#노인 건강
	<% }else if("eye".equals(product.getCategory_name())) { %>
	#눈 건강
	<% }else if("intestine".equals(product.getCategory_name())) { %>
	#장 건강
	<% }else if("vitamin".equals(product.getCategory_name())) { %>
	#비타민
	<% }else if("exercise".equals(product.getCategory_name())) { %>
	#운동
	<% } %>
</div>
<br><br><br>
<button class="btn btn-primary" onclick="alert('준비중입니다');">구매하러가기</button>
</div>
</div>

<hr>

<div class="content">

<h2>제품 설명</h2>
<br>
<%=product.getProduct_content() %>
</div>

</div>