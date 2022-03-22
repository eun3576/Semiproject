<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
   
<% String sessionNick = (String) session.getAttribute("usernick"); %>

<script type="text/javascript">

</script>

<style type="text/css">
table {border-collapse: separate;}

thead {
	font-size: 17px;
	background-color: #eaf3fd;
}

.question {width: 71%;}
</style>


<div class="container">

<!-- 영양소 추천 받기! -->
<hr>
	<h4 align="center"><%=sessionNick %> 님, 영양소를 추천 해드릴게요!</h4>
<hr>

<div>
	<table class="table table-bordered">
	<thead>
	<tr>
		<th class="question" style=" text-align:center; padding-top: 15px; padding-bottom: 15px;">문항</th>
		<th>2일이하<br>(1점)</th>
		<th>3~5일<br>(3점)</th>
		<th>6~7일<br>(5점)</th>
	</tr>
	</thead>
	
	<tbody>
	<tr><td>1. 규칙적인 시간에 3끼 식사를 한다.</td>
		<td><input type="radio" name="q_1" value="1"></td>
		<td><input type="radio" name="q_1" value="3"></td>
		<td><input type="radio" name="q_1" value="5"></td></tr>
	</tbody>
		
	</table>

</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

