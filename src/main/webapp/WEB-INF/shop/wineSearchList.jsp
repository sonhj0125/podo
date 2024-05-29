<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

<style>
div.container {
	
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-style: normal;
}
</style>

<div class="container">
	<div class="mt-4">
		<h1 style="font-weight: bold; margin-bottom: 2%; text-align: center;">WINE</h1>
	</div>
	<hr>
	<div style="display: flex;">
		<div style="width: 50%;">
			<p style="font-size: 16pt;">검색 결과 : <span style="font-weight: bold; color: #990000;">${requestScope.searchWord}</span></p>
		</div>
		<c:if test="${not empty requestScope.wineList}">
			<div style="width: 50%; text-align: right;">
				<p style="font-size: 16pt;"><span style="font-weight: bold;">${requestScope.count}</span>개의 상품 검색</p>
			</div>
		</c:if>
	</div>

	<c:if test="${empty requestScope.wineList}">
		<div class="m-5" style="text-align: center; height: 500px;">
			<span font-size: 20pt;">검색 결과가 없습니다. <i class="fa-solid fa-face-sad-tear"></i></span>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.wineList}">
		<div class="row row-cols-1 row-cols-md-4 g-4 mb-5 mt-3">

			<c:forEach var="pdto" items="${requestScope.wineList}">
				<div class="col">
					<div class="card h-100 curpointer" onclick="showProduct('${pdto.pindex}')">
						<%-- product image --%>
						<img src="../images/product/${pdto.pimg}" class="card-img-top" alt="...">
						<%-- sale badge --%>
						<c:if test="${pdto.pstock == '0'}">
							<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sold-out</div>
						</c:if>
						<c:if test="${pdto.pstock != '0'}">
							<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
						</c:if>
						<%-- product explain --%>
						<div class="card-body">
							<h6 class="card-title text-center" style="font-weight: bold;">
								${pdto.pname}
							</h6>
							<p class="card-text text-center">
								<c:choose>
									<c:when test="${fn:length(pdto.pengname) gt 28}">
										${fn:substring(pdto.pengname, 0, 24)}...
									</c:when>
									<c:otherwise>
										${pdto.pengname}
									</c:otherwise>
								</c:choose>
							</p>
							<%-- <p class="card-text">
								<c:choose>
									<c:when test="${fn:length(pdto.pdetail) gt 60}">
										${fn:substring(pdto.pdetail, 0, 57)}...
									</c:when>
									<c:otherwise>
										${pdto.pdetail}
									</c:otherwise>
								</c:choose>
							</p> --%>
							<div class="mb-3 text-center">
								<c:if test="${pdto.ptype == '레드'}">
                              		<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
                              	</c:if>
                              	<c:if test="${pdto.ptype == '화이트'}">
                              		<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
                              	</c:if>
                              	<c:if test="${pdto.ptype == '로제'}">
                              		<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
                              	</c:if>
                              	<c:if test="${pdto.ptype == '스파클링'}">
                              		<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
                              	</c:if>
								<span class="badge rounded-pill p-2" style="background-color: #9999ff;">${pdto.phometown}</span>
							</div>
							<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">
								${pdto.pprice}원
							</p>
						</div>
					</div>
				</div>

			</c:forEach>

		</div>
	</c:if>



</div>

<jsp:include page="../footer.jsp" />