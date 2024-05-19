<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Bootstrap CSS --%>
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/login/login.js"></script>


<form name="memberRegisterUpdateFrm">
	<div style="margin: 16% 5%;">	
	    <div class="form-floating mb-3">
	        <input type="text" class="form-control rounded-3" id="loginUsername" name="username">
	        <label for="loginUserid">성명</label>
	    </div>
	    <div class="form-floating mb-5">
	        <input type="text" class="form-control rounded-3" id="loginMobile" name="mobile">
	        <label for="loginPwd">전화번호</label>
	    </div>
	    <div class="w-100" style="display: flex; justify-content: space-between;">
	    <button class="mb-2 btn btn-lg  btn-primary" type="button" id="btnSubmit" style="width: 45%;">변경하기</button>
	    <button class="mb-2 btn btn-lg  btn-primary" type="button" id="btnSubmit" style="width: 45%;">취소</button>
	    </div>
   </div>
</form>
