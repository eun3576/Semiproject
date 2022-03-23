<%@ page import="dto.Nutrient" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<% String sessionNick = (String) session.getAttribute("usernick"); %>
<% List<Nutrient> nutrientList = (List) request.getAttribute("nutrientList"); %>

<style type="text/css">

.nutrientExplain {
	border-radius: 20px 20px;
	background-color: #eaf3fd;
	border: 2px solid #666;
	padding: 20px;
	margin: 8px;
}

</style>

<div class="container">

<hr>
<h1 align="center"><%=sessionNick %>님에게 필요한 영양소</h1>
<hr>

<div class="nutrientInfo">

<% for(int i=0; i<nutrientList.size(); i++) { %>
	<div class="nutrientExplain">
	<h2><%=(i+1)%>.&nbsp;<%=nutrientList.get(i).getNutrient() %></h2>
	<div class="content">
	<%=nutrientList.get(i).getExplain() %>
	</div>
	</div>
<% } %>

</div>

</div> <!-- .container end -->
<hr>

<%@ include file="../layout/footer.jsp"%>
