<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int curNotice_no = 0;
%>

<% 	Notice notice = (Notice)request.getAttribute("notice"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	title.innerHTML = "<%=notice.getTitle() %>"
	write_date.innerHTML = "<%=notice.getWrite_date() %>"
	content.innerHTML = "<%=notice.getContent() %>"
	
});
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
			<h3 id="title" align="left"></h3>
			<h6 id="write_date" align="left"></h6><hr>
			<img alt="Bootstrap Image Preview" src="https://www.layoutit.com/img/sports-q-c-140-140-3.jpg" />
			<p id="content"></p>
			<div class="btn-group btn-group-md" role="group">
				 
			</div>
		</div>
	</div>
</div>