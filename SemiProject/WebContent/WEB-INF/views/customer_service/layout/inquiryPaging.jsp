<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Paging paging = (Paging) request.getAttribute("paging"); %>

<div class="text-center">

<ul class="pagination">
	
	<%-- 첫 페이지로 이동 --%>
	<%	if( paging.getCurPage() != 1 ) { %>
	<li><a href="<%=request.getContextPath() %>/inquiry/list">&larr; 처음</a></li>
	<%	} %>


	
	<%-- 이전 페이징 리스트로 이동 --%>
	<%	if( paging.getStartPage() == 1 ) { %>
		<li class="disabled"><a>&laquo;</a></li>
	<%	} else { %>
		<li><a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=paging.getStartPage() - paging.getPageCount() %>">&laquo;</a></li>
	<%	} %>



	<%-- 이전 페이지로 이동 --%>
<%-- 	<%	if( paging.getCurPage() > 1 ) { %> --%>
<!-- 	<li> -->
<%-- 		<a href="<%=request.getContextPath() %>inquiry/list?curPage=<%=paging.getCurPage()-1 %>"> --%>
<!-- 			&lt; -->
<!-- 		</a> -->
<!-- 	</li> -->
<%-- 	<%	} %> --%>



	<%-- 페이징 번호 리스트 --%> 
	<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<% if( paging.getCurPage() == i ) { %>
			<li class="active">
				<a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } else { %>
			<li>
				<a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } %>
	<% } %>
	
	
	
	<%-- 다음 페이지로 이동 --%>
<%-- 	<%	if( paging.getCurPage() < paging.getTotalPage() ) { %> --%>
<!-- 	<li> -->
<%-- 		<a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=paging.getCurPage()+1 %>"> --%>
<!-- 			&gt; -->
<!-- 		</a> -->
<!-- 	</li> -->
<%-- 	<%	} %> --%>
	
	
	
	<%-- 다음 페이징 리스트로 이동 --%>
	<%	if( paging.getEndPage() == paging.getTotalPage() ) { %>
		<li class="disabled"><a>&raquo;</a></li>
	<%	} else { %>
		<li><a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=paging.getStartPage() + paging.getPageCount() %>">&raquo;</a></li>
	<%	} %>
	
	
	
	<%-- 마지막 페이지로 이동 --%>
	<%	if( paging.getCurPage() != paging.getTotalPage() ) { %>
	<li>
	<a href="<%=request.getContextPath() %>/inquiry/list?curPage=<%=paging.getTotalPage() %>">
		끝 &rarr;
	</a>
	</li>
	<%	} %>
	
</ul>

</div>