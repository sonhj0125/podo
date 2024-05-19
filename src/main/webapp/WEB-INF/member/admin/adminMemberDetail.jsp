<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

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
	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;"><img src="<%=ctxPath%>/images/info.png" style="width:35px; vertical-align: sub;">&nbsp;상세정보</h2>
	
	<div style="width:70%; margin:3% auto 0 auto;">
	
	<span class="badge text-bg-dark text-wrap fs-5" style="width: 30%;">Infomation</span>
	<hr style="border:solid 2px purple;">

	<div class="shadow p-3 mb-5 bg-body-tertiary rounded">
		<table class="table-light table table-striped-columns table table-bordered" style="text-align:center;">
	         <tr>
	            <td>아이디</td>
	            <td>leess</td>
	         </tr>
	         <tr>
	            <td>회원명</td>
	            <td>이순신</td>
	         </tr>
	         <tr>
	            <td>이메일</td>
	            <td>leess@naver.com</td>
	         </tr>
	         <tr>
	            <td>휴대폰</td>
	            <td>010-5489-1584</td>
	         </tr>
	         <tr>
	            <td>우편번호</td>
	            <td>05486</td>
	         </tr>
	         <tr>
	            <td>주소</td>
	            <td>제주특별자치도 제주시 제주대학로 21-1</td>
	         </tr>
	         <tr>
	            <td>성별</td>
	            <td>남</td>
	         </tr>
	         <tr>
	            <td>생년월일</td>
	            <td>1973-05-17</td>
	         </tr>
	         
	         <tr>
	            <td>포인트</td>
	            <td>100,000 POINT</td>
	         </tr>
	         <tr>
	            <td>가입일자</td>
	            <td>2024-05-14</td>
	         </tr>
	         <tr>
	            <td>회원상태</td>
	            <td>1(정상)</td>
	         </tr>
	         
		</table>
	</div>	
</div>
	
	<div class="text-center mb-5">
       <button type="button" class="btn btn-primary" style="margin-right:50%;"onclick="">회원목록</button> 
       <button type="button" class="btn btn-secondary" onclick="">뒤로가기</button>
	</div>
	
</div>

<jsp:include page="../../footer.jsp" />