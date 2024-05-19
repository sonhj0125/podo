<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<style>
  .my-hr3 {
    border: 0;
    height: 2px;
    opacity: 30%;
  }
</style>

<jsp:include page="../../header.jsp" />    

<form>
	<div id="container" style="width: 100%;">
		
		<div style="width: 60%;">
			<div style="display: flex; justify-content: space-between;">
				<div>2024.04.24</div>
				<div style="cursor: pointer;" onclick="location.href='<%=ctxPath%>/member/orderDetail.wine'">주문상세&nbsp;<i class="fa-solid fa-chevron-right"></i></div>
			</div>
			
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
					<img src="<%=ctxPath %>/images/product/1.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
					<div style="margin-top: 2%; text-align: right;">
						<div style="margin-bottom: 4%;">디아블로데블 카나발카베르네</div>
						<button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/member/reviewWrite.wine'">리뷰 작성</button>
					</div>
				</div>
			</div>
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
					<img src="<%=ctxPath %>/images/product/2.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
					<div style="margin-top: 2%; text-align: right;">
						<div style="margin-bottom: 4%;">알파박스 앤 다이스 타로 프로세코</div>
						<button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/member/reviewWrite.wine'">리뷰 작성</button>
					</div>
				</div>
			</div>
			<hr class="my-hr3" style="background-color: #000000;">
		</div>
		
		
	    <div style="border: solid 0px blue; width: 60%;">
			<div style="display: flex; justify-content: space-between;">
				<div>2024.03.05</div>
				<div>주문상세&nbsp;<i class="fa-solid fa-chevron-right"></i></div>
			</div>
			
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
					<img src="<%=ctxPath %>/images/product/3.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
					<div style="margin-top: 2%; text-align: right;">
						<div style="margin-bottom: 4%;">닥터 린드만 빈25 스파클링 뀌베</div>
						<button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/member/reviewWrite.wine'">리뷰 작성</button>
					</div>
				</div>
			</div>
			<hr class="my-hr3" style="background-color: #000000;">
		</div>

	</div>


</form>

<jsp:include page="../../footer.jsp" />