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

<%-- IdFind JavaScript --%>
<script type="text/javascript">

$(function () {
	
	const method = "${requestScope.method}";
	const userid = "${requestScope.userid}";

    $("span.errorname-idFind").hide();
    $("span.erroremail-idFind").hide();
    $("input#name-idFind").focus();
    
    if(method == "POST"){
    	
    	const msg = $("div#result-msg");
    	
    	if(userid == "" || userid == null){
    		
    		msg.text(`없는아이디 입니다.`);
    		msg.removeClass("gSearch")
    		msg.addClass("ngSearch");
    		
    	}else{

    		$("#btn-success-idFind").hide();
    		msg.text(`회원님의 아이디는 : ${userid}`);
    		msg.removeClass("ngSearch")
    		msg.addClass("gSearch");
    	}
    }
    
    $("#btn-undo-idFind").click(function(){
    	
        location.href = "<%=ctxPath%>/login/login.wine";
        
    })

    $("input#name-idFind").bind("change", function(){
        $("span.errorname-idFind").hide(); 
    });

    $("input#email-idFind").bind("change", function(){
    	$("span.erroremail-idFind").hide(); 
    });
    
    if(method == "POST") {
       $("input#name-idFind").val("${requestScope.name}"); 
       $("input#email-idFind").val("${requestScope.email}");
    }
    
    // 버튼클릭 또는 엔터
    $("button#btn-success-idFind").click(function(){
		goFind();
    });
    
    $("input#email-idFind").bind("keyup", function(e){
       if(e.keyCode == 13) {
			goFind();
       }
    });
    
});
 
function goFind() {
    
    const name = $("input#name-idFind").val().trim();
    const email = $("input#email-idFind").val();
    
    if(name == "") {

        $("span.errorname-idFind").show();
        $("input#name-idFind").focus();
        
        return;
        
    }
    const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i)
    const bool = regExp_email.test(email);

    if(!bool) {
        $("span.erroremail-idFind").show();
        $("input#email-idFind").val("").focus();
        return;
    }
        
    const frm = document.idFindFrm;
    frm.method = "post";
    frm.action = `<%=ctxPath%>/login/idFind.wine`;
    frm.submit();

}// end of function goFind()

function undoBtn(type){
	
	if(type == "1"){
		
		location.href="javascript:history.go(-1)";
		
	}else if(type == "2"){
		
		location.href="javascript:history.go(-2)";
		
	}else{
		
		location.href="javascript:history.go(-1)";
		
	}
	
}

</script>

<style>
    div#divIdFindFrm {
        font-family: "Noto Sans KR";
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
    
    div.gSearch {
        font-style: normal;
        color : blue;
    }
    
    div.ngSearch {
        font-style: normal;
        color : red;
    }
    
    div#result-msg{
    	text-align: center;
    	margin-top: 50px;
    }
    
    
</style>
     
<body>
	
	
	<div id="divIdFindFrm">
		<form name="idFindFrm">
		<div style="text-align: center">
			<h3>아이디 찾기</h3>
		</div>
		   <ul style="list-style-type: none;">
		      <li style="margin: 25px 0">
		          <label style="display: inline-block; width: 60px;">성명</label>
		          <input type="text" id="name-idFind" name="name" size="25" autocomplete="off" />
		          <br>
		          <label style="display: inline-block; width: 60px;"></label>
		          <span class="errorname-idFind" style="color: red;">성명을 입력해주세요!</span> 
		      </li>
		      <li style="margin: 0;">
		          <label style="display: inline-block; width: 60px;">이메일</label>
		          <input type="text" id="email-idFind" name="email" size="25" autocomplete="off" />
		          <br>
		          <label style="display: inline-block; width: 60px;"></label>
		          <span class="erroremail-idFind" style="color: red;">이메일을 올바르게 입력하세요!</span>  
		      </li>
		   </ul> 
		   	
		   <div class="my-3" style="text-align: center;">
		    	<button type="button" id="btn-success-idFind" class="btn btn-Primary">찾기</button>
		    	<button id ="btn-undo-idFind" type="button" class="btn btn-secondary">이전</button>
		   </div>
	   
	   		<div id="result-msg"></div>
	   		
	   
		</form>
		
		
	</div>
	
</body>
