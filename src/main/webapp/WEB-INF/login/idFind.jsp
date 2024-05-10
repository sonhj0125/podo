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
<script type="text/javascript" src="<%= ctxPath%>/js/login/IdFind.js"></script>

<style>
    div#divIdFindFrm {
        font-family: "Noto Sans KR";
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
</style>
     
<body>
	
	
	<div id="divIdFindFrm">
		<form name="idFindFrm">
	
		   <ul style="list-style-type: none;">
		      <li style="margin: 25px 0">
		          <label style="display: inline-block; width: 60px;">성명</label>
		          <input type="text" name="name" size="25" autocomplete="off" />
		          <br>
		          <label style="display: inline-block; width: 60px;"></label>
		          <span class="errorid" style="color: red;">성명을 입력해주세요!</span> 
		      </li>
		      <li style="margin: 0;">
		          <label style="display: inline-block; width: 60px;">이메일</label>
		          <input type="text" name="email" size="25" autocomplete="off" />
		          <br>
		          <label style="display: inline-block; width: 60px;"></label>
		          <span class="errorEmail" style="color: red;">이메일을 올바르게 입력하세요!</span>  
		      </li>
		   </ul> 
		   	
		   <div class="my-3" style="text-align: center;">
		    	<button type="button" id="btn-success" class="btn btn-Primary">찾기</button>
		   </div>
	   
		</form>
	</div>
	
</body>
