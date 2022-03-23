<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<% List<Product> pList = (List)request.getAttribute("pList"); %>
<!-- header삽입 -->
<%@include file="../layout/header.jsp" %>
<div class="wrapper" style="width:830px;height:1000px;margin:80px auto;clear:left;">
<% if(pList!=null&&pList.size()!=0){ %>
<% for(int i=0;i<pList.size();i++){ %>
<div style="width:250px;height:250px;box-shadow:3px 3px 3px #eee;box-sizing: border-box;float:left;margin-right:20px;margin-bottom:20px;cursor:pointer" onclick="location.href='/product/detail?product_no=<%=pList.get(i).getProduct_no() %>'">
<img alt="상품이미지" src="/upload/<%=pList.get(i).getProduct_img()%>" onerror="this.src='../resources/img/best_temp.jpg'" width="250" height="200">
<%-- <img alt="상품이미지" src="../resources/img/best_temp.jpg" onerror="this.src='../resources/img/best_temp.jpg'"> --%>
<p><%=pList.get(i).getProduct_name() %></p>
<p>조회수: <%=pList.get(i).getProduct_views() %></p>
</div>
<%} %>
<%} else{ %>
<h3 style="width:230px;margin:0 auto;">검색결과가 없습니다</h3>
<%} %>
</div>

<!-- footer삽입 -->
<%@include file="../layout/footer.jsp" %>
