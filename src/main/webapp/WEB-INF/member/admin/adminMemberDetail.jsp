<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" />

<script type="text/javascript" src="<%= ctxPath%>/js/admin/adminRegisterCoupon.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/admin/adminUpdateStatusOff.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/admin/adminUpdateStatusOn.js"></script>


<style type="text/css">
   
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
	
	
	div#modal-body {
		width: 90%;
		align: center;
		margin-left : 3%;
	}
	
	
	input#stopstatus, input#gostatus {
		background-color: #ccc;
	}
	
	
	input#stopstatus:hover, input#gostatus:hover {
		background-color: #ff4d4d;
		
	}
	
	
	
</style>


<%-- 관리자 포인트 내역 클릭시 나오는 Modal --%>
<div class="modal fade" id="adminPoint" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
      <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
              <h1 class="fw-bold mb-0 fs-3"><img src="<%=ctxPath%>/images/point.png" style="width:35px; vertical-align: text-top;">&nbsp;포인트 내역</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          	<div>
            	<table class="table" style="text-align:center;">
					<thead>
						<tr>
						  	<th scope="col">회원아이디</th>
						  	<th scope="col">변동포인트</th>
						  	<th scope="col">변동내역</th>
						  	<th scope="col">변동일자</th>
						</tr>
					</thead>
					
				  	<tbody>
				  	<c:forEach var="pointList" items="${requestScope.podtoList}">
						<tr>
							<th scope="row">${pointList.userID}</th>
							<td>${pointList.poIncome}</td>
							<td>${pointList.poDetail}</td>
							<td>${pointList.poDate}</td>
				    	</tr>
				    </c:forEach> 
				 	</tbody>
				</table>
           	</div>
            </div>
        </div>
    </div>
</div>
<%-- 관리자 포인트 내역 클릭시 나오는 Modal 끝 --%>


<%-- 관리자 리뷰 내역 클릭시 나오는 Modal --%>
<div class="modal fade" id="adminReview" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
      <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
              <h1 class="fw-bold mb-0 fs-3"><img src="<%=ctxPath%>/images/adminreview.png" style="width:35px; vertical-align: text-top;">&nbsp;리뷰 내역</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <div class="modal-body pt-0">
          	<div>
            	<table class="table" style="text-align:center;">
					<thead>
						<tr>
						  	<th scope="col">리뷰번호</th>
						  	<th scope="col">별점</th>
						  	<th scope="col">리뷰내용</th>
						  	<th scope="col">작성일자</th>
						</tr>
					</thead>
					
				  	<tbody>
				  	<c:forEach var="reviewList" items="${requestScope.adminReviewList}">
						<tr>
							<th scope="row">${reviewList.rindex}</th>
							<td>${reviewList.rstar}</td>
							<td>${reviewList.rdetail}</td>
							<td>${reviewList.rdate}</td>
							<td><button type="button" class="btn btn-outline-danger btn-sm">삭제</button></td>
				    	</tr>
				    </c:forEach> 
				 	</tbody>
				</table>
           		</div>
            </div>
        </div>
    </div>
</div>
<%-- 관리자 리뷰 내역 클릭시 나오는 Modal 끝 --%>


<%-- 관리자 쿠폰 넣기 클릭시 나오는 Modal --%>
<div class="modal fade" id="adminCouponIn" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
      <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
              <h1 class="fw-bold mb-0 fs-3"><img src="<%=ctxPath%>/images/salecoupon/registersale.png" style="width:35px; vertical-align: text-top;">&nbsp;쿠폰 넣기</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <div class="modal-body" id="modal-body">
          	<div>
            	<div class="input-group mb-3">
					<label class="input-group-text" for="pHomeTown">쿠폰</label>
			
					<select class="form-select" name="aconame">
						<option selected>선택하세요.</option>
						<c:forEach var="acolist" items="${requestScope.codtoList}">
							<option value="${acolist.coname}">${acolist.coname}</option>
						</c:forEach>
					</select>
					<input type="hidden" value="${requestScope.mdto.userid}" id ="userid" />
		
					<div class="form-group" style="margin-left:5%;">
						<input id="btn-couponRegister" class="btn btn-danger ml-4" type="button" value="등록" onclick="registerCouponAd()"/>
					</div>
				</div>
           	</div>
          </div>
        </div>
    </div>
</div>
<%-- 관리자 쿠폰 넣기 클릭시 나오는 Modal 끝 --%>



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
		            <td>${requestScope.mdto.name} <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#adminReview" >리뷰내역</button></td>
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
               			<button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#adminPoint">포인트내역</button>
               			<button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#adminCouponIn">쿠폰넣기</button>
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
		            	<input type="button" id="stopstatus" class="statusOff" style=" border:none; border-radius: 5px;" value="정지" onclick="statusOff()"/>
		            	<input type="button" id="gostatus" class="statusOn" style=" border:none; border-radius: 5px;" value="해제" onclick="statusOn()"/>
		            </td>
		         </tr>
			</table>
		<input type="hidden" id="stUserid" value="${requestScope.mdto.userid}"/>
		<input type="hidden" id="stMemberidx" value="${requestScope.mdto.memberIdx}"/>
		
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
			  	<th scope="col">UserId</th>
			  	<th scope="col">LoginDate</th>
			  	<th scope="col">IdAddress</th>
			</tr>
		</thead>
	  	<tbody>
		  	<c:forEach var="list" items="${requestScope.ldtoList}">
				<tr>
					<th scope="row">${list.userid}</th>
					<td>${list.logindate}</td>
					<td>${list.ipaddress}</td>
		    	</tr>
		    </c:forEach>
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