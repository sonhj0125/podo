<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String ctxPath = request.getContextPath();
%>

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- Bootstrap CSS --%>
<link rel="stylesheet" href="<%=ctxPath%>/bootstrap-5.3.3-dist/css/bootstrap.min.css"> 

<%-- Font Awesome 6 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>

<style type="text/css">
  div.div_pwd {
     width: 70%;
   height: 15%;
   margin-bottom: 5%;
   margin-left: 10%;
  }
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		$("button.btn-success").click(function() {
			
			const pwd = $("input:password[name='pwd']").val();
			const pwd2 =  $("input:password#pwd2").val(); // input:password[id='pwd2']
			
			if(pwd != pwd2) {
				alert("암호가 일치하지 않습니다.");
				$("input:password[name='pwd']").val("");
				$("input:password#pwd2").val("");
				return; // 함수 종료
				
			} else {
				const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
	            // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
	             
	        	const bool = regExp_pwd.test(pwd);   
	            
	            if(!bool) {
	            	// 암호가 정규표현식에 위배된 경우
	                alert("암호는 8글자 이상 15글자 이하 영문자, 숫자, 특수기호가 혼합되어야 합니다.");
	                $("input:password[name='pwd']").val("");
	                $("input:password[id='pwd2']").val("");
	                return; // 함수 종료
	                
	            } else {
	        		// 암호가 정규표현식에 맞는 경우
	        		const frm = document.pwdUpdateEndFrm;
	        		frm.action = "<%=ctxPath%>/login/pwdUpdateEnd.wine";
	        		frm.method = "post";
	        		frm.submit();
	        	}
			}
			
		}); // end of $("button.btn-success").click(function() {}) ------------
		
	}); // end of $(document).ready(function() {}) --------------
</script>

<c:if test="${requestScope.method == 'GET'}">
	<form name="pwdUpdateEndFrm">
		<div class="div_pwd" style="text-align: center;">
			<span style="color: blue; font-size: 12pt;">새 비밀번호</span><br>
			<input type="password" name="pwd" size="25" />
		</div>
	
		<div class="div_pwd" style="text-align: center;">
			<span style="color: blue; font-size: 12pt;">새 비밀번호확인</span><br>
			<input type="password" id="pwd2" size="25" />
		</div>
		
		<input type="hidden" name="userid" value="${requestScope.userid}">
	
		<div style="text-align: center;">
			<button type="button" class="btn btn-success">암호 변경하기</button>
		</div>
	</form>
</c:if>


<c:if test="${requestScope.method == 'POST'}">
	<div style="text-align: center; font-size: 14pt; color: navy;">
		<c:if test="${requestScope.n == 1}">
	         <p class="mt-5">사용자 ID ${requestScope.userid}님의 비밀번호가 변경되었습니다.</p>
	    </c:if>

		<c:if test="${requestScope.n == 0}">
	         SQL 구문 오류가 발생되어 비밀번호 변경을 할 수 없습니다.
	    </c:if>
	</div>
</c:if>
