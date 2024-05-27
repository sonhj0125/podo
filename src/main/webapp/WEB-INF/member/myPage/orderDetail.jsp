<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%
   String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

<form>
   <div id="container" style="width: 100%;">
      
      <div style="width: 60%;">
      
      <div class="orderDetail_1">
      	 <h2 style="text-align: center;">주문상세</h2>
         <hr>
         <div class="hstack gap-3" style="padding: 2% 0;">
           <img src="<%=ctxPath %>/images/product/${requestScope.ddto.odto.pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
           <div style="display: flex; justify-content: space-between; width: 90%;">
	           <div>
	           		<div style="margin-bottom: 1%; font-weight: bold;">${requestScope.ddto.odto.pdto.pname}</div>
	           		<div style="margin-bottom: 1%; font-size: 10pt;">${requestScope.ddto.odto.pdto.pengname}</div>
	           		<div>
	           			<c:if test="${requestScope.ddto.odto.pdto.ptype == '레드'}">
	           				<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
	           			</c:if>
	           			<c:if test="${requestScope.ddto.odto.pdto.ptype == '로제'}">
	           				<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
	           			</c:if>
	           			<c:if test="${requestScope.ddto.odto.pdto.ptype == '화이트'}">
	           				<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
	           			</c:if>
	           			<c:if test="${requestScope.ddto.odto.pdto.ptype == '스파클링'}">
	           				<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
	           			</c:if>
		           		
		                <span class="badge rounded-pill p-2" style="background-color: #9999ff;">${requestScope.ddto.odto.pdto.phometown}</span>
	           		</div>
	           </div>
	
	           <div style="display:flex;">
		            <div style="font-size: 13pt; font-weight: bold;"><fmt:formatNumber value="${requestScope.ddto.odto.pdto.pprice}" pattern="###,###" />원</div>
		            <div class="mx-2">|</div>
		            <div style="font-size: 13pt;">${requestScope.ddto.odto.ovolume}EA</div>
	           </div>
           </div>
         </div>
         
         <hr>
         <br><br><br>
      </div>
      
      <div class="cart_body_2" style="width: 100%; margin-bottom: 10%;">
      
         <div>
            <h3>배송정보</h3>
            
            <hr>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">수령인</label>
               <div class="col-sm-7">
                  <div>${requestScope.ddto.dname}</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">연락처</label>
               <div class="col-sm-7">
                  <div>010-${fn:substring(requestScope.ddto.dphone,3,7)}-${fn:substring(requestScope.ddto.dphone,7,11)}</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">주소</label>
               <div class="col-sm-7">
                  <div>${requestScope.ddto.daddress}&nbsp;${requestScope.ddto.daddressdetail}</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">배송메시지</label>
 				<div class="col-sm-7">
                  <div>${requestScope.ddto.dmsg}</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">배송상태</label>
               <div class="col-sm-7">
               	  <c:if test="${requestScope.ddto.odto.ostatus ==  1}">
	                  <div>주문접수</div>
               	  </c:if>
               	  <c:if test="${requestScope.ddto.odto.ostatus ==  2}">
	                  <div>제품준비</div>
               	  </c:if>
               	  <c:if test="${requestScope.ddto.odto.ostatus ==  3}">
	                  <div>배송중</div>
               	  </c:if>
               	  <c:if test="${requestScope.ddto.odto.ostatus ==  4}">
	                  <div>배송완료</div>
               	  </c:if>
               </div>
            </div>
            <c:if test="${requestScope.ddto.odto.ostatus !=  1}">
	            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
	               <label class="col-2" style="width: 15.5%; font-weight: bold;">송장번호</label>
	               <div class="col-sm-7">
	               	  <div>${requestScope.ddto.dnumber}</div>
	               </div>
	            </div>
            </c:if>
            <c:if test="${requestScope.ddto.odto.ostatus ==  4}">
	            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
	               <label class="col-2" style="width: 15.5%; font-weight: bold;">배송완료일</label>
	               <div class="col-sm-7">
	               	  <div>${requestScope.ddto.odto.oardate}</div>
	               </div>
	            </div>
            </c:if>
            <br><br><br>
         </div>
         
         <div style="border:solid 0px red;">
            
            <h3>결제 내역</h3>
            <hr>
               <div style="width: 90%; display:flex; justify-content: space-between; margin: 0 auto;">
                  <div style="margin-top: 1%;">최종결제금액</div>
                  <div style="font-weight: bold; font-size: 20pt;"><fmt:formatNumber value="${requestScope.ddto.odto.ototalprice}" pattern="###,###" />원</div>
               </div>
         </div>
      </div>
      
      <div style="text-align: center;">
      	 <button type="button" class="btn btn-primary me-3" onclick="location.href='<%=ctxPath%>/member/orderList.wine'">주문 내역 보기</button>
      	 <button type="button" class="btn btn-secondary" onclick="location.href='<%=ctxPath%>/member/reviewWrite.wine?oindex=${requestScope.ddto.odto.oindex}'">리뷰 내역 보기</button>
	  </div>
     </div>
    </div>
    
</form>
<jsp:include page="../../footer.jsp" />