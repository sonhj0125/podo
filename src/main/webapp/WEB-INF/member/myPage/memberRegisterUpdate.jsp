<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Bootstrap CSS --%>
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/login/login.js"></script>


<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/member/memberRegisterUpdate.js"></script>


<form name="memberRegisterUpdateFrm">

	<div style="margin: 16% 5%;">	
	    <div class="form-floating mb-3">
	        <input type="text" class="form-control rounded-3" id="loginUsername" name="username" value="${sessionScope.loginuser.name}">
	        <label for="loginUserid">성명</label>
	        <span class="error">성명은 필수입력 사항입니다.</span>
	    </div>
	    
	    <div class="form-floating mb-5">
	        <input type="text" name="hp1" id="hp1" size="6" maxlength="3" value="010" readonly /> 
	        <input type="text" name="hp2" id="hp2" size="6" maxlength="4" value="${fn:substring(sessionScope.loginuser.phone, 3, 7)}" />
	        <input type="text" name="hp3" id="hp3" size="6" maxlength="4" value="${fn:substring(sessionScope.loginuser.phone, 7, 11)}" />    
	        <label for="loginPwd">전화번호</label>
	        <span class="error">휴대폰 형식이 아닙니다.</span>
	    </div>
	    
	    <div class="w-100" style="display: flex; justify-content: space-between;">
		    <button class="mb-2 btn btn-lg  btn-primary" type="button" id="btn-close" style="width: 45%;">취소</button>
		    <button class="mb-2 btn btn-lg  btn-primary" type="button" id="btnSubmit" style="width: 45%;" onclick="goEdit()">변경하기</button>
	    </div>
   </div>
   
</form>
