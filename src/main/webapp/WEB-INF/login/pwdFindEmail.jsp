<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%-- <script type="text/javascript" src="<%= ctxPath%>/js/login/pwdFindEmail.js?ver=1"></script> --%>

<style>
    div#divpwdFindFrm {
        font-family: "Noto Sans KR";
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		const method = "${requestScope.method}";
		
		if(method == "GET") {
			$("div#div_findResult").hide();
		}
		
		if(method == "POST") {
			$("input#name").val("${requestScope.name}");
 			$("input#userid").val("${requestScope.userid}");
			$("input#email").val("${requestScope.email}");
			
			if(${requestScope.isUserExist == true && requestScope.sendMailSuccess == true}) {
				$("button.btn-success").hide();
			}
		}
		
		///////////////////////////////////////
	
	    $("input#name").focus();
	    
	    $("span.errorname-pwdFindEmail").hide();
	    $("span.errorid-pwdFindEmail").hide();
	    $("span.erroremail-pwdFindEmail").hide();
	
	    $("input#name").bind("change", function() {
	        $("span.errorname-pwdFindEmail").hide(); 
	    });
	
	    $("input#userid").bind("change", function() {
	        $("span.errorid-pwdFindEmail").hide(); 
	    });
	
	    $("input#email").bind("change", function() {
	        $("span.erroremail-pwdFindEmail").hide(); 
	    });
	    
	    $("button.btn-Primary").click(function() {
	       goPwdFindEmail();
	    });
	    
	    $("input#email").bind("keyup", function(e) {
	       if(e.keyCode == 13) { 
	          goPwdFindEmail();
	       }
	    });
	    
	    
		// === 인증하기 버튼 클릭 시 이벤트 처리해주기 시작 ===
		$("button.btn-info").click(function() {
			const input_confirmCode = $("input:text[name='input_confirmCode']").val().trim();
			
			if(input_confirmCode == "") {
				alert("인증코드를 입력하세요!");
				return; // 함수 종료
			}
			
			const frm = document.verifyCertificationFrm;
			frm.userCertificationCode.value = input_confirmCode; // 입력받은 인증코드 넣기
			frm.userid.value = $("input:text[name='userid']").val();
			
			frm.action = "<%=ctxPath%>/login/verifyCertification.wine";
			frm.method = "post";
			frm.submit();
			
		}); // $("button.btn-info").click(function() {}) ------------------
		// === 인증하기 버튼 클릭 시 이벤트 처리해주기 끝 ===
	    
	 });// end of $(document).ready(function(){})-------------
	
	
	// Function Declaration
	function goPwdFindEmail() {
		
		// 성명을 입력했는지 검사
		const name = $("input:text[name='name']").val().trim();
		
		if(name == "") {
	        $("span.errorname-pwdFindEmail").show();
	        $("input:text[name='name']").focus();
	        return;
		}
		
		// 아이디를 입력했는지 검사
		const userid = $("input:text[name='userid']").val().trim();
		
		if(userid == "") {
	        $("span.errorid-pwdFindEmail").show();
	        $("input:text[name='userid']").focus();
	        return;
		}
		
		// 이메일 검사
		const email = $("input:text[name='email']").val();
		
		const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
		// 이메일 정규표현식 객체 생성 
	    
	    const bool = regExp_email.test(email);
	
	    if(!bool) {
	        // 이메일이 정규표현식에 위배된 경우
	        
	        $("span.erroremail-pwdFindEmail").show();
	        $("input:text[name='email']").val("").focus();
	        return;
	    }
	    
	    const frm = document.pwdFindEmailFrm;
	    frm.action = "<%=ctxPath%>/login/pwdFindEmail.wine";
	    frm.method = "post";
	    frm.submit();
	    
	} // end of function goFind()----------------------------------------

</script>

<body>
	
	<form name="pwdFindEmailFrm">
		<div style="text-align: center">
			<h3>비밀번호 찾기</h3>
		</div>
		
		<ul style="list-style-type: none;">
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">성명</label>
		       <input type="text" id="name" name="name" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="errorname-pwdFindEmail" style="color: red;">성명을 입력해주세요!</span>  
		   </li>
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">아이디</label>
		       <input type="text" id="userid" name="userid" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="errorid-pwdFindEmail" style="color: red;">아이디를 입력해주세요!</span>  
		   </li>
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">이메일</label>
		       <input type="text" id="email" name="email" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="erroremail-pwdFindEmail" style="color: red;">이메일을 올바르게 입력해주세요!</span>  
		   </li>
		</ul>
		
		<div class="my-3 text-center">
		   <button type="button" class="btn btn-Primary">찾기</button>
		   <button type="button" class="btn btn-secondary" onclick="history.back()">이전</button>
		</div>
	</form>
	
	
	<div class="my-3 text-center" id="div_findResult">

		<c:if test="${requestScope.isUserExist == false}">
			<span style="color: red;">사용자 정보가 없습니다.</span>
		</c:if>
		
		
		<c:if test="${requestScope.isUserExist == true && requestScope.sendMailSuccess == true}">
			<span style="font-size: 10pt;">
				인증코드가 ${requestScope.email}로 발송되었습니다.<br>
				인증코드를 입력해주세요.
			</span>
			<br>
			<input type="text" name="input_confirmCode" />
			<br><br>
			<button type="button" class="btn btn-info">인증하기</button>
		</c:if>
		
		
		<c:if test="${requestScope.isUserExist == true && requestScope.sendMailSuccess == false}">
			<span style="color: red;">메일 발송에 실패했습니다.</span>
		</c:if>
		
	</div>

<%-- 인증하기 form --%>
<form name="verifyCertificationFrm">
	<input type="hidden" name="userCertificationCode" />
	<input type="hidden" name="userid" />
</form>
	
</body>
