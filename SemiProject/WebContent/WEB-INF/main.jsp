<%@page import="dto.Product"%>
<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Product> pList = (List)request.getAttribute("pList"); %>
<% List<Review> rList = (List)request.getAttribute("reviewList"); %>
<% String userid = (String)session.getAttribute("userid"); %>

<!-- header삽입 -->
<%@include file="./views/layout/header.jsp" %>

<style type="text/css">
/*이미지 슬라이드 스타일*/
#sliderbox{
	width:1200px;
	height:364px;
/* 	border:1px solid #ccc; */
	margin:0 auto;
	
	position:relative;
	
	overflow:hidden;
}

#slider{
	padding:0;
	margin:0;
	list-style-type:none;
}

#slider li{
	position:absolute;
}

#slider li img{
	width:1200px;
	height:364px;
}

/*베스트 스타일*/
#bestItem  div{display:inline-block;}

#bestReview .best_content {
    width: 470px;
    font-size: 15px;
    line-height: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

#bestReview .bestBox{
	 clear:both;
	 height:55px;
	 margin-bottom:10px;
}

#bestReview .bestBox div{
	width:500px;
	height:50px;
	border-radius:5px;
	overflow:hidden;
	padding-left:5px;
	padding-top:5px;
	padding-right:5px;
}

#bestReview .bestBox div .best_nick{font-size:14px;}

</style>

<!-- 첫번째 이미지 슬라이드 -->
<script type="text/javascript">
$(document).ready(function(){
	var $slide_list = $("#slider li")
	$slide_list.css("left","1200px")
	
	$slide_list.eq(0).css("left","0")
	
	var curSlide = 0
	
	$("#arrowLeft").on("click",function(){
		var nextSlide = curSlide-1
		
		nextSlide %= $slide_list.length
		console.log("nextSlide의 값",nextSlide %= $slide_list.length)
		console.log(curSlide, nextSlide)
		
		
		$slide_list.eq(curSlide).css("left",$("#sliderbox").css("width"))
		$slide_list.eq(nextSlide).css("left",0)

		curSlide--;
		curSlide %= -$slide_list.length
	})
	
	$("#arrowRight").on("click",function(){
		var nextSlide = curSlide+1
		
		nextSlide %= $slide_list.length;
		console.log("nextSlide의 값",nextSlide %= $slide_list.length)
		
		console.log(curSlide, nextSlide);
		
		
		$slide_list.eq(curSlide).css("left",$("#sliderbox").css("width"));
		$slide_list.eq(nextSlide).css("left",0);

		curSlide++;
		curSlide %= $slide_list.length;
	})

	<!-- 이미지 자동 슬라이드 -->
	
	var $slide_list = $("#slider li");
	$slide_list.css("left","1200px");
	
	$slide_list.eq(0).css("left","0");
	
	//--- 2초마다 이미지 한 장씩 교체하기 ---
	var timer = function(){
		var nextSlide = curSlide+1;
		
		//nextSlide 보정값
		nextSlide %= $slide_list.length;
// 		console.log("nextSlide의 값",nextSlide %= $slide_list.length)
		
// 		console.log(curSlide, nextSlide)

		$slide_list.eq(nextSlide).css("left","1200px");
		
		//현재 이미지 숨기기(왼쪽<-가운데)
		$slide_list.eq(curSlide).animate({"left":"-=1200px"});
		
		//다음 이미지 보여주기(가운데<-오른쪽)
		$slide_list.eq(nextSlide).animate({"left":"-=1200px"});
		
		//순환구조
		curSlide++;
		
		//curSlide 보정값
		curSlide %= $slide_list.length;
	};
	
	slideshow = setInterval(timer,2000);
	
	$("#sliderbox").mouseover(function(){clearInterval(slideshow)});
	$("#sliderbox").mouseout(function(){slideshow = setInterval(timer,2000)});
	
})//$(document)

function chatOpen(){
<%if(userid!=null&&!userid.equals("")){%>
	window.open('/chat/user','_blank','width=655, height=805')
<%} else{%>
	alert("로그인 후 이용 가능합니다")
	return false;
<%}%>
}
</script>

<!-- top, bottom 화살표 버튼 -->
<a href="#header" style="cursor:pointer;background:url('../resources/img/arrows_circle_top_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:46%;margin-top:-20px;z-index:100;"></a>
<a href="#footer" style="cursor:pointer;background:url('../resources/img/arrows_bottom_circle_icon.png');background-size:40px;width:40px;height:40px;position:fixed;right:20px;top:54%;margin-top:-20px;z-index:100;"></a>
<div id="sliderbox" style="position:relative;">
	<img src="../resources/img/arrow_left_icon.png" id="arrowLeft" style="position:absolute;left:0;top:50%;z-index:10;margin-top:-32px;cursor:pointer;"/>
<ul id="slider">
	<li style="cursor:pointer;" onclick="location.href='<%request.getContextPath();%>/product/search'"><img src="../resources/img/sildeImg1.jpg"/></li>
	<li style="cursor:pointer;" onclick="location.href='<%request.getContextPath();%>/review/list'"><img src="../resources/img/sildeImg2.jpg"/></li>
</ul>
	<img src="../resources/img/arrow_right_icon.png" id="arrowRight" style="position:absolute;right:0;top:50%;z-index:10;margin-top:-32px;cursor:pointer;"/>
</div>

<!-- 베스트 아이템 -->
<div id="bestItem" style="width:900px;margin:50px auto;text-align: center;">
<h1>BEST ITEM</h1>
<% for(int i=0; i<pList.size();i++){ %>
	<div onclick="location.href='/product/detail?product_no=<%=pList.get(i).getProduct_no() %>'" style="cursor:pointer;">
	<!-- 이미지가 없을시 대체 이미지 -->
	<img alt="상품이미지" src="/upload/<%=pList.get(i).getProduct_img()%>" width="200" height="200" onerror="this.src='../resources/img/best_temp.jpg'">
	<p>제품명: <%=pList.get(i).getProduct_name() %></p>
	<p>조회수: <%=pList.get(i).getProduct_views()%></p>
	</div>
<% } %>
</div>
<hr>

<!-- 베스트 리뷰 -->
<div id="bestReview" style="width:800px;margin:50px auto 100px;">
<h1 style="width:250px;margin:20px auto;">BEST REVIEW</h1>
<%--리뷰가 있을 때 for문 실행--%>
<% if(rList.size()>0){%>
<% for(int i=0; i<rList.size();i++){ %>
	<%if(i%2==0){%>
<div class="bestBox">
<img alt="사용자" src="../resources/img/person_round_user_icon.png" width=40 height=40 style="float:left;margin-top:5px;margin-right:10px;">
<div style="background:rgb(254,239,144);float:left;">
<span class="best_content"  style="float:left;"><%=rList.get(i).getContent()%></span>
<strong class="best_nick"  style="float:left;"><%=rList.get(i).getNickname()%></strong>
</div>
</div>
	<% } else{%>
<div class="bestBox">
<img alt="사용자" src="../resources/img/person_round_user_icon.png" width=40 height=40 style="float:right;margin-top:5px;margin-left:10px;">
<div style="background:rgb(180,218,254);float:right;text-align:right">
<span class="best_content"  style="float:right;"><%=rList.get(i).getContent()%></span>
<strong class="best_nick" style="float:right;"><%=rList.get(i).getNickname()%></strong>
</div>
</div>
	<%} %>
<%} %>

<%} %>
</div>
<div style="width:70px;height:70px;background:url('../../resources/img/chat_icon.png') no-repeat center white;background-size:50px;border:1px solid black;border-radius:50px;cursor:pointer;position:fixed;bottom:100px;right:60px;z-index:1000;"
onclick="chatOpen();">
</div>

<!-- footer삽입 -->
<%@include file="./views/layout/footer.jsp" %>
