<%@page import="util.ProductPaging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 페이징 정보 가져오기 -->
<%	ProductPaging paging = (ProductPaging) request.getAttribute("paging"); %>

<script type="text/javascript">
$(document).ready(function(){
	
	//쿼리스트링으로 온 데이터를 문자열로 변환하여 저장
	var queryString = getQueryString()
	
	//페이징 링크 클릭시 이벤트 발생 (처음 링크 제외)
	$('.a').click(function(){
		
		//클릭한 a태그의 속성중 URL출력
		console.log($(this).attr("href"))
		
		//이동하려는 url에서 '='를 기준으로 문자열 추출
		var no = $(this).attr("href").split('=')
		
		//쿼리스트링 문자열 마지막에 선택한 페이지 번호(curPage값)를 전달
		queryString += "&&curPage="+no[1]
		
		//제품조회 처음 서블릿에 쿼리스트링을 담아서 전달
		//->선택된 체크박스들을 유지하기 위해서
		location.href="/product/search?"+queryString
		
		return false
		
	})
	
	//처음 링크 클릭시 이벤트 발생
	$('.first').click(function(){
		
		//쿼리스트링에 1번 페이지 저장
		queryString += "&&curPage="+1
		
		//제품조회 처음 서블릿에 쿼리스트링을 담아서 전달
		//->선택된 체크박스들을 유지하기 위해서
		location.href="/product/search?"+queryString
		
		return false
		
	})
	
})

//productList.jsp에서 받아온 데이터 문자열로 변환하여 반환
function getQueryString(){
	
	var queryString = ""
	
	//선언된 모든 변수를 배열로 저장
	<%-- var check = ["<%=child%>", "<%=woman%>", "<%=man%>", "<%=aged%>", "<%=eye%>", "<%=intestine%>", "<%=vitamin%>", "<%=exercise%>"] --%>
	
	//값이 존재하는 데이터만 저장할 배열
	var okcheck = []
	
	//존재하는 값만 okcheck배열에 추가
	for(var i=0; i<check.length; i++){
		if(""!=check[i]){
			okcheck.push(check[i])
		}
	}
	
	//존재하는 데이터를 문자열로 변환하여 저장
	for(var i=0; i<okcheck.length-1; i++){
		queryString += ""+okcheck[i]+"="+okcheck[i]+"&&"
	}
	
	//마지막 "&&"를 안붙이기 위한 작업
	var lastcheck = okcheck.length
	
	queryString += ""+okcheck[lastcheck-1]+"="+okcheck[lastcheck-1]
	
	//변환된 문자열 반환
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