<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

	<div id="container" style="text-align: center; height: 600px;">
		
		<div class="cart_header" style="text-align: center; border: solid 0px red; margin-top: 2%;">
		
			 <h2 style="margin-bottom: 1%;">주문완료</h2>
		     <div style="text-align: center; margin:0 5%;">
			 <div style="display: flex; width: 16.5%; margin:0 auto;">
				
				<div style="margin-right: 8%;">
					<i class="fa-solid fa-cart-shopping" style="color: #bcbcbc;"></i>
					<br>
					<div style="font-weight: bold; color: #bcbcbc;">장바구니</div>
				</div>
				<div style="margin-right: 5%;">
					<br>
					<i class="fa-solid fa-chevron-right" style="color: #c0c0c1;"></i>
				</div>
				<div style="margin-right: 5%;">
					<i class="fa-regular fa-credit-card" style="color: #bcbcbc;"></i>
					<br>
					<div style="font-weight: bold; color: #bcbcbc;">주문결제</div>
				</div>
				<div style="margin-right: 5%;">
					<br>
					<i class="fa-solid fa-chevron-right" style="color: #c0c0c1;"></i>
				</div>
				<div>
					<i class="fa-regular fa-circle-check"></i>
					<br>
					<div style="font-weight: bold;">주문완료</div>
				</div>
			
			</div>
			</div>
			
			<img src="<%=ctxPath %>/images/orderEnd_wine.png" style="margin-top: 5.5%;"/>
			<div style="margin-top: 1%;">주문해주셔서 감사합니다<br>빠른 시일 내로 배송해 드리겠습니다</div>
			
		</div>
		
		<div style="width: 85%;">

		
		<div class="cart_body_2" style="margin: 0 auto; display: flex;">
		

			
		</div>

		
		<div class="cart_footer" style="text-align: center;">
			<button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/index.wine';">메인 화면으로</button>
		</div>

		</div>
    </div>

<jsp:include page="../footer.jsp" />