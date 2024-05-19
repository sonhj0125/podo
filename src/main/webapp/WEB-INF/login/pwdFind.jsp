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
		
		$("button#pwdFind").bind("click", function() {
			goPwdFind();
		});
	});
	
	function goPwdFind() {
		
		// 인증 방식을 선택했는지 검사하기
	    const radio_checked_length = $("input:radio[name='findType']:checked").length;
	
	    if (radio_checked_length == 0) {
	    	
	    	alert("인증 방식을 선택하세요!");
	        return; // goPwdFind() 함수 종료
	        
	    }
	    
    	const findType = $("input:radio[name='findType']:checked").val();
    	
    	if(findType == "phone") {
    		location.href = "<%=ctxPath%>/login/pwdFindPhone.wine";
    		
    	} else {
    		location.href = "<%=ctxPath%>/login/pwdFindEmail.wine";
    	}
	} // end of function goPwdFind() -------------------------------------
	
</script>

<body>
	
	<form name="pwdFindFrm">
		<div style="text-align: center">
			<h3>비밀번호 찾기</h3>
		</div>
		
		<div class="m-5 text-center">
			<input type="radio" name="findType" value="phone" id="phone" /><label for="phone" style="margin-left: 1.5%;">휴대폰 인증</label>
            <input type="radio" name="findType" value="email" id="email" style="margin-left: 10%;" /><label for="email" style="margin-left: 1.5%;">이메일 인증</label>
		</div>
		
		<div class="my-3 text-center">
		   <button type="button" class="btn btn-Primary" id="pwdFind">찾기</button>
		   <button type="button" class="btn btn-secondary" onclick="history.back()">이전</button>
		</div>
	</form>
	
</body>
