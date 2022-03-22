<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<% String sessionNick = (String) session.getAttribute("usernick"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnResult").click(function() {
		console.log("#btnResult clicked")
		
		console.log("유효성 검사")
		
		if ($("input[type=radio]:checked").size()  < 20){
				alert("모든 문항을 체크 하세요!")
		} else {
			
			//AJAX 처리 
			$.ajax({
				type: "get" //요청 메소드
				, url: "/nutrient/recommend/result" //요청 URL
				, data: { //요청 파라미터
					q_1: $("input[name=q_1]:checked").val()
					, q_2: $("input[name=q_2]:checked").val()
					, q_3: $("input[name=q_3]:checked").val()
					, q_4: $("input[name=q_4]:checked").val()
					, q_5: $("input[name=q_5]:checked").val()
					, q_6: $("input[name=q_6]:checked").val()
					, q_7: $("input[name=q_7]:checked").val()
					, q_8: $("input[name=q_8]:checked").val()
					, q_9: $("input[name=q_9]:checked").val()
					, q_10: $("input[name=q_10]:checked").val()
					, q_11: $("input[name=q_11]:checked").val()
					, q_12: $("input[name=q_12]:checked").val()
					, q_13: $("input[name=q_13]:checked").val()
					, q_14: $("input[name=q_14]:checked").val()
					, q_15: $("input[name=q_15]:checked").val()
					, q_16: $("input[name=q_16]:checked").val()
					, q_17: $("input[name=q_17]:checked").val()
					, q_18: $("input[name=q_18]:checked").val()
					, q_19: $("input[name=q_19]:checked").val()
					, q_20: $("input[name=q_20]:checked").val()
				}     
				, dataType: "html" //응답 데이터의 형식
				, success: function (res) {
					console.log("AJAX 성공")
					
					//응답 데이터 반영하기
					$("#surveyResult").html(res)
					
				}
				,error: function() {
					console.log("AJAX 실패")
				}
				
			}) /* AJAX 처리 end */
		} /* if ~ else end */
	})
})

</script>

<style type="text/css">

#btnDiv {text-align:center;}

#btnResult{
	border: 2px solid #ddd;
	background-color: #eaf3fd;
	padding: 5px;
}

#btnResult:active{
	border: 2px solid #ddd;
	background-color: #709dcf;
	padding: 5px;
}

table {border-collapse: separate;}

thead {
	font-size: 17px;
	background-color: #eaf3fd;
}

.question {width: 71%;}

</style>

<div class="container">


<!-- 나의 몸 자가 진단 -->
<hr>
	<h4 align="center"><%=sessionNick %> 님, 영양을 골고루 섭취하고 있을까요?</h4>
	<h6 align="center">영양상태를 확인 해보세요!</h6>
<hr>


	<div id="selfDia">
	
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
	<tr><td>2. 매끼 골고루 식사를 하며 편식을 하지 않는다.</td>
		<td><input type="radio" name="q_2" value="1"></td>
		<td><input type="radio" name="q_2" value="3"></td>
		<td><input type="radio" name="q_2" value="5"></td></tr>
	<tr><td>3. 아침 식사는 꼭 먹는다.</td>
		<td><input type="radio" name="q_3" value="1"></td>
		<td><input type="radio" name="q_3" value="3"></td>
		<td><input type="radio" name="q_3" value="5"></td></tr>
	<tr><td>4. 식사량은 언제나 적당히 한다.</td>
		<td><input type="radio" name="q_4" value="1"></td>
		<td><input type="radio" name="q_4" value="3"></td>
		<td><input type="radio" name="q_4" value="5"></td></tr>
	<tr><td>5. 즐거운 마음으로 여유있게 식사를 한다.</td>
		<td><input type="radio" name="q_5" value="1"></td>
		<td><input type="radio" name="q_5" value="3"></td>
		<td><input type="radio" name="q_5" value="5"></td></tr>
	<tr><td>6. 1일 2끼이상 고기, 생선, 달걀, 콩, 두부 중 하나라도 섭취한다.</td>
		<td><input type="radio" name="q_6" value="1"></td>
		<td><input type="radio" name="q_6" value="3"></td>
		<td><input type="radio" name="q_6" value="5"></td></tr>
	<tr><td>7. 녹황색 채소(당근, 시금치 등)를 섭취한다. </td>
		<td><input type="radio" name="q_7" value="1"></td>
		<td><input type="radio" name="q_7" value="3"></td>
		<td><input type="radio" name="q_7" value="5"></td></tr>
	<tr><td>8. 식물성 기름(들기름, 식용유 등)이 첨가된 음식(나물, 볶음, 튀김)을 섭취한다.</td>
		<td><input type="radio" name="q_8" value="1"></td>
		<td><input type="radio" name="q_8" value="3"></td>
		<td><input type="radio" name="q_8" value="5"></td></tr>
	<tr><td>9. 우유나 유제품(요구르트, 요플레)을 먹는다.</td>
		<td><input type="radio" name="q_9" value="1"></td>
		<td><input type="radio" name="q_9" value="3"></td>
		<td><input type="radio" name="q_9" value="5"></td></tr>
	<tr><td>10. 과일이나 과일주소(무가당)를 섭취한다. </td>
		<td><input type="radio" name="q_10" value="1"></td>
		<td><input type="radio" name="q_10" value="3"></td>
		<td><input type="radio" name="q_10" value="5"></td></tr>
	<tr><td>11. 해조류(미역, 김, 다시마 등)를 섭취한다.</td>
		<td><input type="radio" name="q_11" value="1"></td>
		<td><input type="radio" name="q_11" value="3"></td>
		<td><input type="radio" name="q_11" value="5"></td></tr>
	</tbody>
	
	</table>
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
	<tr><td>1. 거의 매일 외식을 한다. </td>
		<td><input type="radio" name="q_12" value="1"></td>
		<td><input type="radio" name="q_12" value="3"></td>
		<td><input type="radio" name="q_12" value="5"></td></tr>
	<tr><td>2. 매일 가공식품(라면, 과자 등)을 먹는다.</td>
		<td><input type="radio" name="q_13" value="1"></td>
		<td><input type="radio" name="q_13" value="3"></td>
		<td><input type="radio" name="q_13" value="5"></td></tr>
	<tr><td>3. 매일 동물성 기름이나 콜레스테롤이 많은 음식을 먹는다.</td>
		<td><input type="radio" name="q_14" value="1"></td>
		<td><input type="radio" name="q_14" value="3"></td>
		<td><input type="radio" name="q_14" value="5"></td></tr>
	<tr><td>4. 매일 짠 음식(젓갈, 장아찌 등)이나 화학조미료를 섭취한다.</td>
		<td><input type="radio" name="q_15" value="1"></td>
		<td><input type="radio" name="q_15" value="3"></td>
		<td><input type="radio" name="q_15" value="5"></td></tr>
	<tr><td>5. 매일 단 음식(설탕, 꿀, 엿, 콜라, 단빵 등)을 섭취 한다.</td>
		<td><input type="radio" name="q_16" value="1"></td>
		<td><input type="radio" name="q_16" value="3"></td>
		<td><input type="radio" name="q_16" value="5"></td></tr>
	<tr><td>6. 매일 카페인(커피, 차류 등)이 든 음식을 하루 3잔 이상 마신다.</td>
		<td><input type="radio" name="q_17" value="1"></td>
		<td><input type="radio" name="q_17" value="3"></td>
		<td><input type="radio" name="q_17" value="5"></td></tr>
	<tr><td>7. 매일 과음 및 잦은 음주를 한다.</td>
		<td><input type="radio" name="q_18" value="1"></td>
		<td><input type="radio" name="q_18" value="3"></td>
		<td><input type="radio" name="q_18" value="5"></td></tr>
	<tr><td>8. 매일 담배를 피운다. </td>
		<td><input type="radio" name="q_19" value="1"></td>
		<td><input type="radio" name="q_19" value="3"></td>
		<td><input type="radio" name="q_19" value="5"></td></tr>
	<tr><td>9. 규칙적인 운동을 거의 하지 않는다. </td>
		<td><input type="radio" name="q_20" value="1"></td>
		<td><input type="radio" name="q_20" value="3"></td>
		<td><input type="radio" name="q_20" value="5"></td></tr>
	</tbody>
	</table>
	<div id="btnDiv">
		<button id="btnResult"> 검사 결과</button>
	</div>
	
	</div>

<div id="surveyResult"></div>

<hr>

</div><!-- .container -->


<%@ include file="../layout/footer.jsp" %>
