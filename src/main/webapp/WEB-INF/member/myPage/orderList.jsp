<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
   String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

<style>
  .my-hr3 {
    border: 0;
    height: 2px;
    opacity: 30%;
  }
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		// 주문상세 클릭 시
		$("div#orderDetail").click(function(e) {
			const oindex = $(e.target).next().val();
			location.href = "<%=ctxPath%>/member/orderDetail.wine?oindex=" + oindex;
		});
		
	});
</script>

<form>
	<div id="container" style="width: 100%;">
		<h2 style="text-align: center;">주문내역조회</h2>
		
		<c:if test="${not empty requestScope.orderList}">
			<div style="width: 60%;">
				<c:forEach var="odto" items="${requestScope.orderList}" varStatus="status">
					<div>
						<c:choose>
							<c:when test="${status.index == 0}">
								<hr class="my-hr3" style="background-color: #000000;">
							</c:when>
							<c:otherwise>
								<hr>
							</c:otherwise>
						</c:choose>
						<div style="display: flex; justify-content: space-between;">
							<img src="<%=ctxPath %>/images/product/${odto.pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 120px;">
							<div style="display: flex; width: 90%; margin-left: 2%; justify-content: space-between;">
								<div style="margin: auto 0%; padding-bottom: 1%;">
										<div style="font-weight: bold;">${odto.pdto.pname}</div>
										<div><fmt:formatNumber value="${odto.ototalprice}" pattern="###,###" />원</div>
										<div style="font-size: 10pt;">${odto.ovolume}개 주문</div>
										<div class="mt-1" style="font-size: 10pt; color: #990000;">주문일자 ${odto.odate}</div>
								</div>
								
								<div style="margin: auto 0;">
									<div id="orderDetail" style="cursor: pointer; font-weight: bold;">주문상세&nbsp;<i class="fa-solid fa-chevron-right"></i></div>
									<input type="hidden" name="oindex" value="${odto.oindex}">
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<hr class="my-hr3" style="background-color: #000000;">
			</div>
		</c:if>
		
		
		<c:if test="${empty requestScope.orderList}">
			<div style="width: 70%; margin: 0 auto;">
				<hr>
				<div class="m-5" style="text-align: center; height: 500px;">
					<span style="font-size: 18pt;">주문하신 상품이 없습니다. <i class="fa-solid fa-face-sad-tear"></i></span>
				</div>
				<div style="text-align: center;">
					<button type="button" class="btn btn-secondary m-3" onclick="location.href='<%=ctxPath%>/shop/list.wine'">쇼핑하러 가기</button>
				</div>
			</div>
		</c:if>

	</div>


</form>

<jsp:include page="../../footer.jsp" />