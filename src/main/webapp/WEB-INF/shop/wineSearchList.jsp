<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

<div class="container">
	<div class="mt-4">
		<h1 style="font-weight: bold; margin-bottom: 2%; text-align: center;">WINE</h1>
	</div>
	<hr>
	<div>
		<p style="font-size: 16pt;">검색 결과 : <span style="font-weight: bold; color: #990000;">${requestScope.searchWord}</span></p>
	</div>

	<c:if test="${empty requestScope.wineList}">
		<div class="m-5" style="text-align: center;">
			<span style="font-size: 20pt;">검색 결과가 없습니다. <i class="fa-solid fa-face-sad-tear"></i></span>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.wineList}">
		<div class="row row-cols-1 row-cols-md-4 g-4 mb-5 mt-3">

			<c:forEach var="pvo" items="${requestScope.wineList}">
				<div class="col curpointer" onclick="showProduct('${pvo.pindex}')">
					<div class="card h-100">
						<%-- product image --%>
						<img src="../images/product/${pvo.pimg}" class="card-img-top" alt="...">
						<%-- sale badge --%>
						<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
						<%-- product explain --%>
						<div class="card-body">
							<h5 class="card-title text-center" style="font-weight: bold;">
								${pvo.pname}
							</h5>
							<p class="card-text text-center" style="font-weight: bold;">
								${pvo.pengname}
							</p>
							<p class="card-text">${pvo.pdetail}</p>
							<div class="mb-3 text-center">
								<span class="badge rounded-pill p-2" style="background-color: #ff3333;">${pvo.ptype}</span>
								<span class="badge rounded-pill p-2" style="background-color: #9999ff;">${pvo.phometown}</span>
							</div>
							<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">
								${pvo.pprice}원
							</p>
						</div>
					</div>
				</div>

			</c:forEach>

		</div>
	</c:if>



</div>

<jsp:include page="../footer.jsp" />