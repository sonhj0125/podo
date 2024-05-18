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
<%-- <script type="text/javascript" src="<%= ctxPath%>/js/login/pwdFindPhone.js?ver=1"></script> --%>

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
		
		/* const method = "${requestScope.method}";
		
		if(method == "GET") {
			$("div#div_findResult").hide();
		}
		
		if(method == "POST") {
			$("input#name").val("${requestScope.name}");
 			$("input#userid").val("${requestScope.userid}");
			$("input#phone").val("${requestScope.phone}");
			
			if(${requestScope.isUserExist == true && requestScope.sendMailSuccess == true}) {
				$("button.btn-success").hide();
			}
		} */
		
		///////////////////////////////////////
	
	    $("input#name").focus();
	    
	    $("span.errorname-pwdFindPhone").hide();
	    $("span.errorid-pwdFindPhone").hide();
	    $("span.errorphone-pwdFindPhone").hide();
	
	    $("input#name").bind("change", function() {
	        $("span.errorname-pwdFindPhone").hide(); 
	    });
	
	    $("input#userid").bind("change", function() {
	        $("span.errorid-pwdFindPhone").hide(); 
	    });
	
	    $("input#phone").bind("change", function() {
	        $("span.errorphone-pwdFindPhone").hide(); 
	    });
	    
	    $("button.btn-Primary").click(function() {
	       goPwdFindPhone();
	    });
	    
	    $("input#phone").bind("keyup", function(e) {
	       if(e.keyCode == 13) { 
	    	   goPwdFindPhone();
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
	function goPwdFindPhone() {
		
		// 성명을 입력했는지 검사
		const name = $("input#name").val().trim();
		
		if(name == "") {
	        $("span.errorname-pwdFindPhone").show();
	        $("input#name").focus();
	        return;
		}
		
		// 아이디를 입력했는지 검사
		const userid = $("input#userid").val().trim();
		
		if(userid == "") {
	        $("span.errorid-pwdFindPhone").show();
	        $("input#userid").focus();
	        return;
		}
		
		// 연락처 검사
		const phone = $("input#phone").val().trim();
		
		const regExp_phone = new RegExp(/^01[016789]{1}[0-9]{3,4}[0-9]{4}$/);
        const bool = regExp_phone.test(phone);

	    if(!bool) {
	        // 연락처가 정규표현식에 위배된 경우
	        $("span.infophone-pwdFindPhone").hide();
	        $("span.errorphone-pwdFindPhone").show();
	        $("input#phone").val("").focus();
	        return;
	    }
	    
	    const frm = document.pwdFindPhoneFrm;
	    frm.action = "<%=ctxPath%>/login/pwdFindPhone.wine";
	    frm.method = "post";
//	    frm.submit();
	    
	} // end of function goPwdFindPhone() ----------------------------------------

</script>

<body>
	
	<form name="pwdFindPhoneFrm">
		<div style="text-align: center">
			<h3>비밀번호 찾기</h3>
		</div>
		
		<ul style="list-style-type: none;">
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">성명</label>
		       <input type="text" id="name" name="name" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="errorname-pwdFindPhone" style="color: red;">성명을 입력해주세요!</span>  
		   </li>
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">아이디</label>
		       <input type="text" id="userid" name="userid" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="errorid-pwdFindPhone" style="color: red;">아이디를 입력해주세요!</span>  
		   </li>
		   <li style="margin: 25px 0">
		       <label style="display: inline-block; width: 60px;">연락처</label>
		       <input type="text" id="phone" name="phone" size="25" autocomplete="off" />
		       <br>
		    <label style="display: inline-block; width: 60px;"></label>
		       <span class="infophone-pwdFindPhone" style="font-size: 10pt; color: blue;">하이픈[-]을 빼고 입력하세요.</span>
		       <span class="errorphone-pwdFindPhone" style="color: red;">연락처를 올바르게 입력해주세요!</span>  
		   </li>
		</ul>
		
		<div class="my-3 text-center">
		   <button type="button" class="btn btn-Primary">찾기</button>
		   <button type="button" class="btn btn-secondary" onclick="history.back()">이전</button>
		</div>
	</form>
	
</body>