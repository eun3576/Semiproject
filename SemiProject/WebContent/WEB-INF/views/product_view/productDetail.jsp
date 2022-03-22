<%@page import="dto.Attachment"%>
<%@page import="dto.ProductCategory"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Product product = (Product)request.getAttribute("product");
	List<ProductCategory> list = (List)request.getAttribute("list");
	List<Attachment> atlist = (List)request.getAttribute("atlist");
	System.out.println(atlist.size());
%>

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
	<% for(int i=0; i<list.size(); i++){ %>
		#<%=list.get(i).getCategory_name() %>
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
<%for(int i=0; i<atlist.size(); i++){ %>
	<img src="<%=request.getContextPath() %>/upload/<%=atlist.get(i).getStored_img() %>" alt=" " width="200" height="200" />
<% } %>
<%=product.getProduct_content() %>
</div>

</div>