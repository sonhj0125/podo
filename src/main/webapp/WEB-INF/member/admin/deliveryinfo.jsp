<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript" src="<%=ctxPath%>/js/admin/deliveryinfo.js"></script>
    
<div class="modal-body p-5 pt-0">

	<form name="delinfoRegister">
	    
	    <div>
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
            <hr>
            <br><br><br>
         </div>
	    
	</form>
	
</div>