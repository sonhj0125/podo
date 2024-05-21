<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="header.jsp" />
<div class="text-center">
	<img src="<%=ctxPath %>/images/main.png" class="img-fluid rounded" alt="...">
</div> 
    <div id="container">
        <div>
            <h1 class="py-5 px-lg-5 text-center">
                NEW ARRIVALS
            </h1>
            <!-- Section-->
	        <section class="py-3">
	            <div class="container px-4 px-lg-5 mt-5">
	                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	                    
	                    <c:forEach var="newList" items="${requestScope.newProductList}" end="3">
	                    	
	                    	<div class="col mb-5">
		                        <div class="card h-100 curpointer" onclick="showProduct('${newList.pindex}')">
		                            <!-- Sale badge-->
		                            <c:if test="${newList.pstock == '0'}">
		                            	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sold-out</div>
		                            </c:if>
		                            <c:if test="${newList.pstock != '0'}">
		                            	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
		                            </c:if>
		                            <!-- Product image-->
		                            <img class="card-img-top" src="<%=ctxPath %>/images/product/${newList.pimg}" alt="..." />
		                            <div class="card-body p-4">
		                                <div class="text-center fw-bolder">
		                                    <!-- Product name-->
		                                    <h6 class="fw-bolder">${newList.pname}</h6>
		                                    <!-- Product price-->
		                                    ${newList.pprice}원
		                                </div>
		                            </div>
		                            <!-- Product details-->
		                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
		                                <div class="text-center">
		                                	<c:if test="${newList.ptype == '레드'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
		                                	</c:if>
		                                	<c:if test="${newList.ptype == '화이트'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
		                                	</c:if>
		                                	<c:if test="${newList.ptype == '로제'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
		                                	</c:if>
		                                	<c:if test="${newList.ptype == '스파클링'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
		                                	</c:if>
		                                	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">${newList.phometown}</span>
		                                </div>
		                            </div>
		                        </div>
	                    	</div>
	                    
	                    </c:forEach>
	                    
	                </div>
	            </div>
	        </section>
        </div>
        <div>
            <h1 class="px-lg-5 text-center">
                MOST POPULAR
            </h1>
            <!-- Section-->
	        <section class="py-5">
	            <div class="container px-4 px-lg-5 mt-5">
	                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	                    
	                    <c:forEach var="popList" items="${requestScope.popProductList}" end="3">
	                    	
	                    	<div class="col mb-5">
		                        <div class="card h-100 curpointer" onclick="showProduct('${popList.pindex}')">
		                            <!-- Sale badge-->
		                            <c:if test="${popList.pstock == '0'}">
		                            	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sold-out</div>
		                            </c:if>
		                            <c:if test="${popList.pstock != '0'}">
		                            	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
		                            </c:if>
		                            <!-- Product image-->
		                            <img class="card-img-top" src="<%=ctxPath %>/images/product/${popList.pimg}" alt="..." />
		                            <div class="card-body p-4">
		                                <div class="text-center fw-bolder">
		                                    <!-- Product name-->
		                                    <h6 class="fw-bolder">${popList.pname}</h6>
		                                    <!-- Product price-->
		                                    ${popList.pprice}원
		                                </div>
		                            </div>
		                            <!-- Product details-->
		                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
		                                <div class="text-center">
		                                	<c:if test="${popList.ptype == '레드'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
		                                	</c:if>
		                                	<c:if test="${popList.ptype == '화이트'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
		                                	</c:if>
		                                	<c:if test="${popList.ptype == '로제'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
		                                	</c:if>
		                                	<c:if test="${popList.ptype == '스파클링'}">
		                                		<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
		                                	</c:if>
		                                	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">${popList.phometown}</span>
		                                </div>
		                            </div>
		                        </div>
	                    	</div>
	                    
	                    </c:forEach>
	                    
	                </div>
	            </div>
	        </section>
        </div>
	</div>
<jsp:include page="footer.jsp" />