<%@page import="dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int curNotice_no = 0;
%>

<% 	
	Notice notice = (Notice)request.getAttribute("notice");
	List<Attachment> atlist = (List)request.getAttribute("attachment");
%>

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
			<% for(int i=0; i<atlist.size(); i++) { %>
			<img src="<%=request.getContextPath() %>/upload/<%=atlist.get(i).getStored_img() %>" alt=" " width="200" height="200" />
			<% } %>
			<p id="content"></p>
		</div>
	</div>
</div>