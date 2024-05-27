<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		
		<%-- 리뷰 작성 클릭 시 --%>
		$("button#btnReviewWrite").click(function(e) {
			const oindex = $(e.target).next().val();
			location.href = "<%=ctxPath%>/member/reviewWrite.wine?oindex=" + oindex;
		});
		
		<%-- 리뷰 수정 클릭 시 --%>
		$("button#btnReviewUpdate").click(function(e) {
			const rindex = $(e.target).next().val();
			location.href = "<%=ctxPath%>/member/reviewUpdate.wine?rindex=" + rindex;
		});
		
	});
</script>

<form>
	<div id="container" style="width: 100%; margin-bottom: 5%;">
		<h3 style="width: 100%; text-align: center;">리뷰관리</h3>
		
		<%-- 값이 있을 때 시작 --%>
		<c:if test="${not empty requestScope.reviewPdtList}">
			<div style="width: 60%;">
				<c:forEach var="rdto" items="${requestScope.reviewPdtList}" varStatus="status">
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
							
								<img src="<%=ctxPath%>/images/product/${rdto.odto.pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 120px;">
								<div style="display: flex; width: 90%; margin-left: 2%; justify-content: space-between;">
									<div style="margin: auto 0%; padding-bottom: 1%;">
											<div style="font-weight: bold;">${rdto.odto.pdto.pname}</div>
											<div>${rdto.odto.ototalprice}원</div>
											<div style="font-size: 10pt;">${rdto.odto.ovolume}개 주문</div>
											<div class="mt-1" style="font-size: 10pt; color: #990000;">주문일자 ${rdto.odto.odate}</div>
									</div>
									<c:choose>
										<c:when test="${rdto.rindex == 0}">
											<div style="margin: auto 0;">
												<button id="btnReviewWrite" type="button" class="btn btn-primary">리뷰 작성</button>
												<input type="hidden" name="oindex" value="${rdto.odto.oindex}">
											</div>
										</c:when>
										<c:otherwise>
											<div style="margin: auto 0; text-align: right;">
												
												<div class="mb-2" style="font-size: 10pt;">${rdto.rdate} 작성</div>
												<button id="btnReviewUpdate" type="button" class="btn btn-secondary">리뷰 수정</button>
												<input type="hidden" name="rindex" value="${rdto.rindex}">
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
				</c:forEach>
				
				<hr class="my-hr3" style="background-color: #000000;">
			</div>
		</c:if>
		<%-- 값이 있을 때 끝 --%>
		
		
		<c:if test="${empty requestScope.reviewPdtList}">
			<div style="width: 70%; margin: 0 auto;">
				<hr>
				<div class="m-5" style="text-align: center;">
					<span style="font-size: 18pt;">리뷰를 작성할 수 있는 상품이 없습니다. <i class="fa-solid fa-face-sad-tear"></i></span>
				</div>
				<div style="text-align: center;">
					<button type="button" class="btn btn-secondary m-3" onclick="location.href='<%=ctxPath%>/shop/list.wine'">쇼핑하러 가기</button>
					<button type="button" class="btn btn-secondary m-3" onclick="location.href='<%=ctxPath%>/member/orderList.wine'">주문내역조회</button>
				</div>
			</div>
		</c:if>
		

	</div>

</form>

<jsp:include page="../../footer.jsp" />