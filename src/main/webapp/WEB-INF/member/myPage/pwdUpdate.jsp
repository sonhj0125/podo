<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" /> 

<!-- Main css -->
<link rel="stylesheet" href="<%=ctxPath %>/css/member/myPage/pwdUpdate.css" type="text/css">

<!-- Jquery JS -->
<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>

<!-- Main JS-->
<script src="<%=ctxPath %>/js/member/pwdUpdate.js"></script>

<style>

    div#PwdUpdatefrm {
        font-family: "Noto Sans KR", serif;
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
    
</style>

<script type="text/javascript">

$(document).ready(function(){

	$("button#btnClose").bind('click',()=>{
		
		$("input#pwd").val("");
		$("input#pwd2").val("");
		
		location.href="<%=ctxPath%>/index.wine";
	});
	

	$("button#btnSubmit").click(function(){
		goPwdUpdate();

	});

	$("input#pwd2").bind("keydown", function(e){
	        if(e.keyCode == 13) { 
	        	goPwdUpdate(); 
	        }
	});

	

});

</script>
     
 <div class="wrapper wrapper--w680" style="margin-bottom: 80px">
    <div class="card card-4">
        <div class="card-body">
        	<div style="text-align: center">
            	<h2 class="title">비밀번호 변경</h2>
            </div>
            <form name="pwdUpdatefrm">
            
	            <div class="container" style="margin-left: 16%;">
				  <div class="row g-2">
				    <div class="col-8">
				      <div class="input-group">
                            <label class="label">비밀번호</label>
                            <input class="input--style-4" type="password" id="pwd" name="pwd">
	                  </div>
				    </div>
				    <div class="col-8">
				      <div class="input-group">
                           <label class="label">비밀번호 확인</label>
                           <input class="input--style-4" type="password" id="pwd2">
                           <input type="hidden" name="userid" value="${sessionScope.loginUser.userid}" />
	                  </div>
				    </div>
				  </div>
				</div>

			    <div class="w-100" style="display: flex; justify-content: center;">
				   	<button class="mt-2 btn btn-lg  btn-secondary" type="button" id="btnClose" style="width: 30%; margin-right: 1%;">취소</button>
				    <button class="mt-2 btn btn-lg  btn-secondary" type="button" id="btnSubmit" style="width: 30%; margin-left: 1%;" onclick="goPwdUpdate()">변경하기</button>
			    </div>
			    
            </form>
        </div>
    </div>
    
</div>

<div class="toast-container position-fixed end-0 p-5 top-0 start-50 translate-middle-x">
	<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
		<div class="toast-header text-bg-warning">
			<strong class="me-auto">알림</strong>
   			<small>비밀번호 변경</small>
		</div>
		<div class="toast-body fw-bold" id="toast-msg">
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/footer.jsp" /> 