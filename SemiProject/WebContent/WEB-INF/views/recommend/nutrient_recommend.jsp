<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
   
<% String sessionNick = (String) session.getAttribute("usernick"); %>


<script type="text/javascript">

$(document).ready(function() {
	
	//작성버튼 동작
	$("#nutrientResult").click(function() {
		
		console.log("버튼 눌러짐")
		
		if ($("input[type=radio]:checked").size() < 8) {
			alert("모든 문항을 체크 하세요!!")
		} else {
			$("form").submit();
		}
	})
})	

</script>

<style type="text/css">
#btnDiv {text-align: center;}

button {
	border: 2px solid #ddd;
	background-color: #eaf3fd;
	padding: 5px;
}
button:active {
	border: 2px solid #ddd;
	background-color: #709dcf;
	padding: 5px;
}
.question {width: 100%;}

.answer {
	padding-bottom: 15px;
}
.survey {
	border-radius: 20px 20px;
	background-color: #eaf3fd;
	border: 2px solid #666;
	padding-left: 20px;
	padding-bottom: 20px;
	margin: 8px;
}
.q_a {
	float: left;
	width: 130px;
}

</style>


<div class="container">

<!-- 영양소 추천 받기! -->
<hr>
	<h4 align="center"><%=sessionNick %> 님, 영양소를 추천 해드릴게요!</h4>
<hr>

<div id="nutrientRecommend">
		
		<!-- 점수가 낮은 항목이 필요한 영양소 -->
		<form action="/nutrient/recommend" method="post">
		
		<div class="survey">
			<!-- 종합 비타민, 미네랄 -->
			<div class="question">
				<h3>질문 1. 가공식품을 자주 먹으며 흡연, 음주를 즐긴다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_1" value="4">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_1" value="3">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_1" value="2">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_1" value="1">&nbsp;6~7일</label></li>
			</ul>
			</div>
		</div>
		
		<div class="survey">
			<!-- 칼슘 -->
			<div class="question">
				<h3>질문 2. 케일, 견과류, 해조류, 우유, 요거트를 챙겨 먹는다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_2" value="1">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_2" value="2">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_2" value="3">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_2" value="4">&nbsp;6~7일</label></li>
			</ul>
			</div>
		</div>
		
		<div class="survey">
			<!-- 물 -->
			<div class="question">
				<h3>질문 3. 하루에 물을 8~10잔 마신다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_3" value="1">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_3" value="2">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_3" value="3">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_3" value="4">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
		
		<div class="survey">
			<!-- 비타민C -->
			<div class="question">
				<h3>질문 4. 식사 후 채소, 과일을 챙겨 먹는다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_4" value="1">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_4" value="2">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_4" value="3">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_4" value="4">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
		
		<div class="survey">
			<!-- 비타민D -->
			<div class="question">
				<h3>질문 5. 야외활동을 통해 하루에 15분 이상 햇볕을 쬔다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_5" value="1">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_5" value="2">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_5" value="3">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_5" value="4">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
		
		<div class="survey">
			<!-- 루테인, 오메가3 -->
			<div class="question">
				<h3>질문 6. 하루에 모니터 또는 스마트폰을 8시간 이상 시청한다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_6" value="4">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_6" value="3">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_6" value="2">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_6" value="1">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
	
		<div class="survey">
			<!-- 지방 -->		
			<div class="question">
				<h3>질문 7. 음식을 만들어 먹을 때 식물성 식용유를 사용한다.</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_7" value="1">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_7" value="2">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_7" value="3">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_7" value="4">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
			
		<div class="survey">
			<!-- 철분 -->
			<div class="question">
				<h3>질문 8. 생활 중에 머리가 핑 돈다.(빈혈)</h3>
			</div>
			<div class="answer">
			<ul>
				<li class="q_a"><label><input type="radio" name="q_8" value="4">&nbsp;2일 이하</label></li>
				<li class="q_a"><label><input type="radio" name="q_8" value="3">&nbsp;3일</label></li>
				<li class="q_a"><label><input type="radio" name="q_8" value="2">&nbsp;4~5일</label></li>
				<li class="q_a"><label><input type="radio" name="q_8" value="1">&nbsp;6~7일</label></li>
			</ul>                                                  
			</div>
		</div>
			
		</form>
			
		<div id="btnDiv">
			<button id="nutrientResult"type="button">영양소 추천 받기</button>
		</div>

</div>

<hr>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>

