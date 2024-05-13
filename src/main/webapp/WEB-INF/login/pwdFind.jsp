<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String ctxPath = request.getContextPath();

%>    

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%-- Bootstrap CSS --%>
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-5.3.3-dist/js/bootstrap.min.js" ></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/login/PwdFind.js?ver=1"></script>

<style>
    div#divpwdFindFrm {
        font-family: "Noto Sans KR";
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
</style>

<body>
	
	<form name="pwdFindFrm">

	   <ul style="list-style-type: none;">
	      <li style="margin: 25px 0">
	          <label style="display: inline-block; width: 60px;">아이디</label>
	          <input type="text" name="userid" size="25" autocomplete="off" />
	          <br>
		      <label style="display: inline-block; width: 60px;"></label>
	          <span class="errorid" style="color: red;">아이디를 입력해주세요!</span>  
	      </li>
	      <li style="margin: 25px 0">
	          <label style="display: inline-block; width: 60px;">이메일</label>
	          <input type="text" name="email" size="25" autocomplete="off" />
	          <br>
		      <label style="display: inline-block; width: 60px;"></label>
	          <span class="erroremail" style="color: red;">이메일을 올바르게 입력해주세요!</span>  
	      </li>
	   </ul> 
	
	   <div class="my-3 text-center">
	      <button type="button" class="btn btn-Primary">찾기</button>
	   </div>
   
	</form>
	
</body>
