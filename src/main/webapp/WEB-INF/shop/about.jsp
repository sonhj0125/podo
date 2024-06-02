<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />
	
	<style>
	
	.setsizing{
		width : 60% !important;
	}
	
	</style>
	
	<div id="container">
	
				<div id="carouselExample" class="carousel slide setsizing">
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img src="<%=ctxPath %>/images/intro/intro_sdh.png" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="<%=ctxPath %>/images/intro/intro_kmj.png" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="<%=ctxPath %>/images/intro/intro_kdy.png" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="<%=ctxPath %>/images/intro/intro_shj.png" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="<%=ctxPath %>/images/intro/intro_ksj.png" class="d-block w-100" alt="...">
				    </div>
				  </div>
				  
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
	
	</div>
	
<jsp:include page="../footer.jsp" />	