<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

<script type="text/javascript">
	function goReviewWrite() {
		const oindex = $("input[name='oindex']").val();
		location.href = "<%=ctxPath%>/member/reviewWrite.wine?oindex=" + oindex;
	}
</script>


<jsp:include page="../../header.jsp" />    

<form>
	<div id="container" style="width: 100%; margin-bottom: 5%;">
		<h3 style="width: 100%; text-align: center;">리뷰관리</h3>
		
		<%-- 값이 있을 때 시작 --%>
		<c:if test="${not empty requestScope.reviewPdtList}">
			<div style="width: 60%;">
				<c:forEach var="rvo" items="${requestScope.reviewPdtList}" varStatus="status">
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
							
								<img src="<%=ctxPath%>/images/product/${rvo.odto.pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
									<div style="display: flex; width: 90%; justify-content: space-between;">
									<div style="margin: auto 0%;">
											<div>${rvo.odto.pdto.pname}</div>
											<div>${rvo.odto.pdto.pprice}원</div>
											<div class="mt-2" style="font-size: 10pt;">주문일자 ${rvo.odto.odate}</div>
									</div>
									<c:choose>
										<c:when test="${rvo.rindex == ''}">
											<div style="margin: auto 0;">
												<input type="hidden" name="oindex" value="${rvo.odto.oindex}">
												<button type="button" class="btn btn-primary" onclick="goReviewWrite()">리뷰 작성</button>
											</div>
										</c:when>
										<c:otherwise>
											<div style="margin: auto 0; text-align: right;">
												<input type="hidden" name="rindex" value="${rvo.rindex}">
												<div class="mb-2" style="font-size: 10pt;">${rvo.rdate} 작성</div>
												<button type="button" class="btn btn-secondary" onclick="location.href='<%=ctxPath%>/member/reviewUpdate.wine'">리뷰 수정</button>
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