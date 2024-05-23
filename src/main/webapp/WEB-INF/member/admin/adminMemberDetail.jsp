<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" />

<style type="text/css">
   
	<%-- 페이지 이동 --%>
	.page-link {
	  color: #000; 
	  background-color: #fff;
	  border: 1px solid #ccc; 
	}
	
	.page-item.active .page-link {
	 z-index: 1;
	 color: #555;
	 font-weight:bold;
	 background-color: #f1f1f1;
	 border-color: #ccc;
	 
	}
	
	.page-link:focus, .page-link:hover {
	  color: #000;
	  background-color: #fafafa; 
	  border-color: #ccc;
	}
 
 
</style>


<div class="container" style="padding: 3% 0;">

	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;"><img src="<%=ctxPath %>/images/info.png" style="width:35px; vertical-align: sub;">&nbsp;상세정보</h2>
	
	<div style="width:70%; text-align:center; margin:3% auto 0 auto;">
	
	<span class="badge text-bg-dark text-wrap fs-5" style="width: 30%;">Infomation</span>
	<hr style="border:solid 2px purple;">

	<div class="shadow p-3 mb-5 bg-body-tertiary rounded">
		<c:if test="${empty requestScope.mdto}">
			존재하지 않는 회원입니다.<br>
		</c:if>
		<c:if test="${not empty requestScope.mdto}">
			<c:set var="phone" value="${requestScope.mdto.phone}" />
			<table class="table-light table table-striped-columns table table-bordered table align-middle" style="text-align:center;">
		         <tr>
		            <td>아이디</td>
		            <td>${requestScope.mdto.userid}</td>
		         </tr>
		         <tr>
		            <td>회원명</td>
		            <td>${requestScope.mdto.name} <button type="button" class="btn btn-warning btn-sm" onclick="">리뷰내역</button></td>
		         </tr>
		         <tr>
		            <td>이메일</td>
		            <td>${requestScope.mdto.email}</td>
		         </tr>
		         <tr>
		            <td>휴대폰</td>
		            <td>${fn:substring(phone, 0, 3)}-${fn:substring(phone, 3, 7)}-${fn:substring(phone, 7, 11)}</td>
		         </tr>
		         <tr>
		            <td>주소</td>
		            <td>${requestScope.mdto.address}&nbsp;
		            	${requestScope.mdto.addressDetail}&nbsp;
		            </td>
		         </tr>
		         <tr>
		            <td>성별</td>
		            <td>
		            	<c:choose>
	            			<c:when test="${requestScope.mdto.gender == '1'}">남</c:when>
	            			<c:otherwise>여</c:otherwise>
	            		</c:choose>
	            	</td>
		         </tr>
		         <tr>
		            <td>생년월일</td>
		            <td>${requestScope.mdto.birthday}</td>
		         </tr>
		         
		         <tr>
		         	<td>포인트/쿠폰</td>
		            <td>
               			<fmt:formatNumber value="${requestScope.mdto.point}" pattern="###,###" />&nbsp;POINT
               			<button type="button" class="btn btn-warning btn-sm" onclick="">포인트내역</button>
               			<button type="button" class="btn btn-warning btn-sm" onclick="">쿠폰넣기</button>
               		</td>
		         </tr>
		         <tr>
		            <td>가입일자</td>
		            <td>${requestScope.mdto.registerDay}</td>
		         </tr>
		         <tr>
		            <td>회원상태</td>
		            <td>
		            	${requestScope.mdto.status}
		            	<button type="button" class="btn btn-warning btn-sm" onclick="">정지</button>
		            </td>
		         </tr>
			</table>
		
		<div class="input-group mb-3">
		  	<label class="input-group-text" for="userCoupon">보유 쿠폰</label>
		  	<select class="form-select" id="userCoupon">
			<c:if test="${not empty requestScope.mycodtoList}">
			  	<c:forEach var="list" items="${requestScope.mycodtoList}">
					<option value="${list.coindex}">${list.codto.coname}</option>
		      	</c:forEach>
		    </c:if>
		  	</select>
		</div>
		
		
	<table class="table">
		<thead>
			<tr>
			  	<th scope="col">LogIndex</th>
			  	<th scope="col">UserId</th>
			  	<th scope="col">LoginDate</th>
			  	<th scope="col">IdAddress</th>
			</tr>
		</thead>
	  	<tbody>
			<tr>
				<th scope="row">LogIndex</th>
				<td>UserId</td>
				<td>LoginDate</td>
				<td>IdAddress</td>
	    	</tr>
	  	</tbody>
	</table>
		
	</c:if>
		
	</div>	
</div>  

	
	<div class="text-center mb-5">
       <button type="button" class="btn btn-primary" style="margin-right:50%;" onclick="javascript:location.href='adminMember.wine'">회원목록</button> 
       <button type="button" class="btn btn-danger" onclick="javascript:location.href='${pageContext.request.contextPath}${requestScope.goBackURL}'">뒤로가기</button>
	</div>
	
</div>

<jsp:include page="/WEB-INF/footer.jsp" />  