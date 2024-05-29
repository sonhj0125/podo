<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String ctxPath = request.getContextPath();
%>

<%-- bootstrap --%>
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>

<%-- jQuery --%>
<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>
	
<script type="text/javascript" src="<%=ctxPath%>/js/admin/deliveryinfo.js"></script>

<div id="ctxPath" style="display: none"><%=ctxPath %></div>
<div id="ostatusshow" style="display: none">${requestScope.ddto.odto.ostatus}</div>
<div id="oindex" style="display: none">${requestScope.oindex}</div>
<div id="dindex" style="display: none">${requestScope.ddto.dindex }</div>

<div class="modal-body p-5 pt-0">

	<form name="delinfoRegister">
	     <hr>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label style="font-weight: bold;">수령인</label>
               <div class="col-sm-7">
                  <div>${requestScope.ddto.dname}</div>
               </div>
            </div>
            
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label style="font-weight: bold;">연락처</label>
               <div class="col-sm-7">
                  <div>010-${fn:substring(requestScope.ddto.dphone,3,7)}-${fn:substring(requestScope.ddto.dphone,7,11)}</div>
               </div>
            </div>
            
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label style="font-weight: bold;">주소</label>
               <div class="col-sm-7">
                  <div>${requestScope.ddto.daddress}&nbsp;${requestScope.ddto.daddressdetail}</div>
               </div>
            </div>
            
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label style="font-weight: bold;">배송메시지</label>
 				<div class="col-sm-7">
                  <div>${requestScope.ddto.dmsg}</div>
               </div>
            </div>
            
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label style="font-weight: bold;">송장번호</label>
               <div class="input-group mb-3">
				  <input type="text" name="dnumber" class="form-control" value="${requestScope.ddto.dnumber}" aria-label="Recipient's username" aria-describedby="button-addon2">
				  <button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="registerDnumber()">등록</button>
				</div>
            </div>
            
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
                <label style="font-weight: bold;">주문상태</label>
                
                <div class="input-group">
				  <select name="ostatus" class="form-select" id="inputGroupSelect04" aria-label="Example select with button addon">
				    <option value="1">주문접수</option>
              		<option value="2">제품준비</option>
              		<option value="3">배송중</option>
              		<option value="4">배송완료</option>
				  </select>
				  <button class="btn btn-outline-secondary" type="button" onclick="registerStatus()">변경</button>
				</div>
                
            </div>
            
            <c:if test="${requestScope.ddto.odto.ostatus ==  4}">
	            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
	               <label style="font-weight: bold;">배송완료일</label>
	               <div class="col-sm-7">
	               	  <div>${requestScope.ddto.odto.oardate}</div>
	               </div>
	            </div>
            </c:if>
            <hr>
            
	    
	</form>
	
</div>