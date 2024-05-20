$(document).ready(function() {
	
	$("span.error").hide();
	$("input#name").focus();
	
 //	$("input#name").bind("blur", function(e){ alert("name에 있던 포커스를 잃어버렸습니다."); });
 //	$("input#name").blur(function(e){ alert("name에 있던 포커스를 잃어버렸습니다."); });
	
	$("input#name").blur( (e) => {
		
		const name = $(e.target).val().trim();
		if(name == "") {
			// 입력하지 않거나 공백만 입력했을 경우 
			/*	
			   >>>> .prop() 와 .attr() 의 차이 <<<<	         
		            .prop() ==> form 태그내에 사용되어지는 엘리먼트의 disabled, selected, checked 의 속성값 확인 또는 변경하는 경우에 사용함. 
		            .attr() ==> 그 나머지 엘리먼트의 속성값 확인 또는 변경하는 경우에 사용함.
			*/ 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 공백이 아닌 글자를 입력했을 경우
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 name 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	
	$("input#hp2").blur( (e) => {
		
	//	const regExp_hp2 = /^[1-9][0-9]{3}$/;  
	//  또는
	    const regExp_hp2 = new RegExp(/^[1-9][0-9]{3}$/);  
	    // 연락처 국번( 숫자 4자리인데 첫번째 숫자는 1-9 이고 나머지는 0-9) 정규표현식 객체 생성 
	    
	    const bool = regExp_hp2.test($(e.target).val());	
		
		if(!bool) {
			// 연락처 국번이 정규표현식에 위배된 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 연락처 국번이 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 hp2 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	$("input#hp3").blur( (e) => {
		
	//	const regExp_hp3 = /^[0-9]{4}$/;  
	//  또는
	//	const regExp_hp3 = /^\d{4}$/;
	    const regExp_hp3 = new RegExp(/^\d{4}$/);  
	    // 숫자 4자리만 들어오도록 검사해주는 정규표현식 객체 생성 
	    
	    const bool = regExp_hp3.test($(e.target).val());	
		
		if(!bool) {
			// 마지막 전화번호 4자리가 정규표현식에 위배된 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 마지막 전화번호 4자리가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 hp3 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
});// end of $(document).ready(function(){})----------------------


// Function Declaration
// "수정하기" 버튼 클릭시 호출되는 함수 
function goEdit(){
	
// *** 필수입력사항에 모두 입력이 되었는지 검사하기 시작 *** //
	let b_requiredInfo = false;
	
/*	
	$("input.requiredInfo").each(function(index, elmt){
		const data = $(elmt).val().trim();
		if(data == "") {
			alert("*표시된 필수입력사항은 모두 입력하셔야 합니다.");
		    b_requiredInfo = true;
		    return false; // break; 라는 뜻이다.	
		}
	});
*/ 
//  또는
    const requiredInfo_list = document.querySelectorAll("input.requiredInfo"); 
    for(let i=0; i<requiredInfo_list.length; i++){
		const val = requiredInfo_list[i].value.trim();
		if(val == "") {
			alert("*표시된 필수입력사항은 모두 입력하셔야 합니다.");
		    b_requiredInfo = true;
		    break; 
		}
	}// end of for-----------------------------
     	
	
	if(b_requiredInfo) {
		return; // goRegister() 함수를 종료한다.
	}
	// *** 필수입력사항에 모두 입력이 되었는지 검사하기 끝 *** //
	
	
	// *** 이메일값을 수정한 다음에 "이메일중복확인" 을 클릭했는지 검사하기 시작 *** //
	if(b_email_change && !b_emailcheck_click) {
		// 이메일값을 수정한 다음에 "이메일중복확인" 을 클릭 안 했을 경우
		alert("이메일 중복확인을 클릭하셔야 합니다.");
		return; // goEdit() 함수를 종료한다.
	}
	// *** 이메일값을 수정한 다음에 "이메일중복확인" 을 클릭했는지 검사하기 끝 *** //
	
	
	// *** 우편번호 및 주소에 값을 입력했는지 검사하기 시작 *** //
	const postcode = $("input#postcode").val().trim();
	const address = $("input#address").val().trim();
	const detailAddress = $("input#detailAddress").val().trim();
	const extraAddress = $("input#extraAddress").val().trim();
	
	if(postcode == "" || address == "" || detailAddress == "" || extraAddress == "") {
		alert("우편번호 및 주소를 입력하셔야 합니다.");
		return; // goRegister() 함수를 종료한다.
	}
	// *** 우편번호 및 주소에 값을 입력했는지 검사하기 끝 *** //	
	
	
	//////////////////////////////////////////////////////////
	
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

	
}// end of function goEdit()-----------------------








