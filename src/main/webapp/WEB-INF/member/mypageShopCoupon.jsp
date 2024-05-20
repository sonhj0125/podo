<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" /> 


<style type="text/css">


</style>

<div class="container" style="padding: 3% 0;">
	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;"><img src="<%=ctxPath%>/images/coupon.png" style="width:35px; vertical-align: text-top;">&nbsp;마이페이지</h2>
	<hr>
	
	<table class="table" style="width:50;">
	  <thead>
	    <tr>
	      <th scope="col" style="font-size:16pt"><span style="font-weight:bold; font-size:20pt; color:#ff6666;"><img src="<%=ctxPath%>/images/mypageP.png" style="width:35px; vertical-align: text-top;">&nbsp;손혜정</span>님</th>
	    </tr>
	  </thead>
	  <tbody class="table-group-divider" style="text-align:center;">
	    <tr class="table-secondary">
	      <th scope="row">총 쿠폰 발행 수</th>
	      <th>사용 쿠폰 수</th>
	      <th>가용 쿠폰 수</th>
	    </tr>
	    <tr class="table-group-divider">
	      <td>3</td>
	      <td>2</td>
	      <td>1</td>
	    </tr>
	  </tbody>
	</table>
	
	
	<div class="input-group mb-3" style="width:40%; margin-left:30%; margin-top:5%;">
	  	<input type="text" class="form-control" placeholder="쿠폰번호를 입력해주세요." aria-label="Recipient's username" aria-describedby="button-addon2">
	  	<button class="btn btn-outline-danger" type="button" id="">등록하기</button>
	</div>
	
	
	<div class="vr" style="border:solid 1px blue; height:20px;"></div>
		<span style="font-weight:bold; font-size:16pt;">나의 쿠폰</span>
	<div class="vr" style="border:solid 1px blue; height:20px;"></div>
	
	<hr style="border:solid 1px black; margin-top:5;">
	<div style="display:flex; margin-top:3%;">
		<div style="margin-right:20%; margin-left:10%;">
			<img src="<%=ctxPath%>/images/salecoupon/sale.png" style="width:80px; vertical-align: middle;">&nbsp;
		</div>
		<div>
			<span style="font-weight:bold; font-size:12pt; margin-bottom: 2%;" >신규회원 가입 감사쿠폰</span><br>
			할인율/할인금액 : 5,000원<br>
			최소 주문금액 : 50,000원<br>
			2024-05-19 ~ 2024-08-18
		</div>
		<div style="margin-left:40%; margin-top:3%;">
			<span style="font-weight:bold; font-size:14pt; color:green;">사용가능</span>
		</div>
	</div>
	<hr style="border:solid 1px black; margin-top:3%;">
	
	<hr style="border:solid 1px black; margin-top:5;">
	<div style="display:flex; margin-top:3%;">
		<div style="margin-right:20%; margin-left:10%;">
			<img src="<%=ctxPath%>/images/salecoupon/registersale.png" style="width:80px; vertical-align: middle;">&nbsp;
		</div>
		<div>
			<span style="font-weight:bold; font-size:12pt; margin-bottom: 2%;" >신규회원 가입 감사쿠폰</span><br>
			할인율/할인금액 : 5,000원<br>
			최소 주문금액 : 50,000원<br>
			2024-05-19 ~ 2024-08-18
		</div>
		<div style="margin-left:40%; margin-top:3%;">
			<span style="font-weight:bold; font-size:14pt; color:green;">사용가능</span>
		</div>
	</div>
	<hr style="border:solid 1px black; margin-top:3%;">
	
	









</div>











<jsp:include page="/WEB-INF/footer.jsp" /> 