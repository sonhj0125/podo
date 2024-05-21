$(document).ready(function() {
	
	$("span.error").hide();
	$("input#pwd").focus();
	
 //	$("input#name").bind("blur", function(e){ alert("name에 있던 포커스를 잃어버렸습니다."); });
 //	$("input#name").blur(function(e){ alert("name에 있던 포커스를 잃어버렸습니다."); });
	
	
	$("input#pwd").blur( (e) => {
		
	//	const regExp_pwd = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 
	//  또는
	    const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
	    // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
	    
	    const bool = regExp_pwd.test($(e.target).val());	
		
		if(!bool) {
			// 암호가 정규표현식에 위배된 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 암호가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 pwd 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	$("input#pwdcheck").blur( (e) => {
		
		if( $("input#pwd").val() != $(e.target).val() ) {
			// 암호와 암호확인값이 틀린 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$("input#pwd").prop("disabled", false);
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		    
			$("input#pwd").focus();
		}
		else {
			// 암호와 암호확인값이 같은 경우
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 pwdcheck 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.	
	
	
	
});// end of $(document).ready(function(){})----------------------


// Function Declaration
// "수정하기" 버튼 클릭시 호출되는 함수 
function goEdit(){
	
	// 변경된 암호가 현재 사용중인 암호이라면 현재 사용중인 암호가 아닌 새로운 암호로 입력해야 한다.!!! 
	let isNewPwd = true;
	
	$.ajax({
			 url:"duplicatePwdCheck.up",
			 data:{"new_pwd":$("input:password[name='pwd']").val()
			      ,"userid":$("input:hidden[name='userid']").val()}, // data 속성은 http://localhost:9090/MyMVC/member/emailDuplicateCheck.up 로 전송해야할 데이터를 말한다. 
			 type:"post",  //  type 을 생략하면 type:"get" 이다.
			 
			 async:false,  // !!!!! 반드시 동기방식 이어야 한다 !!!!! 
			               // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
         		           // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다.   
			 
			 dataType:"json", // Javascript Standard Object Notation.  dataType은 /MyMVC/member/emailDuplicateCheck.up 로 부터 실행되어진 결과물을 받아오는 데이터타입을 말한다. 
         		              // 만약에 dataType:"xml" 으로 해주면 /MyMVC/member/emailDuplicateCheck.up 로 부터 받아오는 결과물은 xml 형식이어야 한다. 
         		              // 만약에 dataType:"json" 으로 해주면 /MyMVC/member/emailDuplicateCheck.up 로 부터 받아오는 결과물은 json 형식이어야 한다. 
			  
			 success:function(json){
				 
				 if(json.isExists) {
					 // 입력한 암호가 이미 사용중이라면
					 $("span#duplicate_pwd").html("현재 사용중인 비밀번호로 비밀번호 변경은 불가합니다."); 
					 isNewPwd = false;
				 }
				 
			 },
			 
			 error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
		 }); 
	
	//////////////////////////////////////////////////////////
	
	if(isNewPwd) { // 변경한 암호가 새로운 암호일 경우
 
		const frm = document.editFrm;
		frm.action = "memberEditEnd.up";
		frm.method = "post";
		frm.submit();
        
	}
	
}// end of function goEdit()-----------------------