<%@page import="util.ProductPaging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	ProductPaging paging = (ProductPaging) request.getAttribute("paging"); %>
<script type="text/javascript">
$(document).ready(function(){
	
	var queryString = getQueryString()
	
	$('.a').click(function(){
		
		//클릭한 앵커태그의 속성중 URL출력
		console.log($(this).attr("href"))
		var no = $(this).attr("href").split('=')
	
		queryString += "&&curPage="+no[1]
		location.href="/product/search?"+queryString
		
		return false
		
	})
	
	$('.first').click(function(){
	
		queryString += "&&curPage="+1
		location.href="/product/search?"+queryString
		
		return false
		
	})
	
})

function getQueryString(){
	
	var queryString = ""
	
	var check = ["<%=child%>", "<%=woman%>", "<%=man%>", "<%=aged%>", "<%=eye%>", "<%=intestine%>", "<%=vitamin%>", "<%=exercise%>"]
	
	var okcheck = []
	
	for(var i=0; i<check.length; i++){
		if(""!=check[i]){
			okcheck.push(check[i])
		}
	}
	
	for(var i=0; i<okcheck.length-1; i++){
		queryString += ""+okcheck[i]+"="+okcheck[i]+"&&"
	}
	
	var lastcheck = okcheck.length
	
	
	queryString += ""+okcheck[lastcheck-1]+"="+okcheck[lastcheck-1]
	
	
	return queryString
}
</script>

<div class="text-center">

<ul class="pagination">
	
	<%-- 첫 페이지로 이동 --%>
	<%	if( paging.getCurPage() != 1 ) { %>
	<li><a class="first" href="<%=request.getContextPath() %>/product/search">&larr; 처음</a></li>
	<%	} %>


	
	<%-- 이전 페이징 리스트로 이동 --%>
	<%	if( paging.getStartPage() == 1 ) { %>
		<li class="disabled"><a>&laquo;</a></li>
	<%	} else { %>
<%-- 		<li><a id="prePage" href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getStartPage() - paging.getPageCount() %>">&laquo;</a></li> --%>
		<li><a class="a" href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getStartPage() - paging.getPageCount() %>">&laquo;</a></li>
	<%	} %>



	<%-- 이전 페이지로 이동 --%>
<%-- 	<%	if( paging.getCurPage() > 1 ) { %> --%>
<!-- 	<li> -->
<%-- 		<a href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getCurPage()-1 %>"> --%>
<!-- 			&lt; -->
<!-- 		</a> -->
<!-- 	</li> -->
<%-- 	<%	} %> --%>



	<%-- 페이징 번호 리스트 --%> 
	<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<% if( paging.getCurPage() == i ) { %>
			<li class="active">
				<a class="a" href="<%=request.getContextPath() %>/product/result?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } else { %>
			<li>
				<a class="a" href="<%=request.getContextPath() %>/product/result?curPage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } %>
	<% } %>
	
	
	
	<%-- 다음 페이지로 이동 --%>
<%-- 	<%	if( paging.getCurPage() < paging.getTotalPage() ) { %> --%>
<!-- 	<li> -->
<%-- 		<a href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getCurPage()+1 %>"> --%>
<!-- 			&gt; -->
<!-- 		</a> -->
<!-- 	</li> -->
<%-- 	<%	} %> --%>
	
	
	
	<%-- 다음 페이징 리스트로 이동 --%>
	<%	if( paging.getEndPage() == paging.getTotalPage() ) { %>
		<li class="disabled"><a>&raquo;</a></li>
	<%	} else { %>
		<li><a class="a" href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getStartPage() + paging.getPageCount() %>">&raquo;</a></li>
	<%	} %>
	
	
	
	<%-- 마지막 페이지로 이동 --%>
	<%	if( paging.getCurPage() != paging.getTotalPage() ) { %>
	<li>
	<a class="a" href="<%=request.getContextPath() %>/product/result?curPage=<%=paging.getTotalPage() %>">
		끝 &rarr;
	</a>
	</li>
	<%	} %>
	
</ul>

</div>