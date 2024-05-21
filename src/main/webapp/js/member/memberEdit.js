
let b_emailcheck_click = false;
// "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도

let b_email_change = false;
// 이메일값을 변경했는지 여부를 알아오기 위한 용도


$(document).ready(function() {

	const toastLive = document.getElementById('liveToast');
	const toastmsg = document.getElementById('toast-msg');		
	
	$("input#name").blur( (e) => {

		const tag = $('input#name');
		
		const name = $(e.target).val().trim();
		if(name == "") {
			// 입력하지 않거나 공백만 입력했을 경우 
			/*	
			   >>>> .prop() 와 .attr() 의 차이 <<<<	         
		            .prop() ==> form 태그내에 사용되어지는 엘리먼트의 disabled, selected, checked 의 속성값 확인 또는 변경하는 경우에 사용함. 
		            .attr() ==> 그 나머지 엘리먼트의 속성값 확인 또는 변경하는 경우에 사용함.
			*/ 
			$("div#card-body :input").prop("disabled", true); 
			$(e.target).prop("disabled", false); 
			
			toastmsg.innerText="이름은 필수입력 사항입니다.";
			const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
			toastBootstrap.show();
			tag.removeClass("status-g");
            tag.addClass("status-ng");
		}
		else {

		$("div#card-body :input").prop("disabled", false);

		}
		
	});// 아이디가 name 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	$("input#pwd").blur( (e) => {

		const tag = $('input#pwd');
		
	//	const regExp_pwd = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 
	//  또는
	    const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
	    // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
	    
	    const bool = regExp_pwd.test($(e.target).val());	
		
		if(!bool) {
			// 암호가 정규표현식에 위배된 경우 
			
			toastmsg.innerText="암호는 영문자,숫자,특수기호가 혼합된 8~15 글자로 입력하세요.";
		    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
			toastBootstrap.show();
			tag.removeClass("status-g");
            tag.addClass("status-ng");
			
			$(e.target).val("").focus(); 
		}
		else {
			$("div#card-body :input").prop("disabled", false);		
		}
		
	});// 아이디가 pwd 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	$("input#pwdCheck").blur( (e) => {

		const tag = $('input#pwdCheck');
		
		if( $("input#pwd").val() != $(e.target).val() ) {
			// 암호와 암호확인값이 틀린 경우 
			
			$("div#card-body :input").prop("disabled", true);   
			$("input#pwd").prop("disabled", false);
			$(e.target).prop("disabled", false); 
			
			toastmsg.innerText="암호가 일치하지 않습니다.";
			const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
			toastBootstrap.show();
			tag.removeClass("status-g");
            tag.addClass("status-ng");
		    
			$("input#pwd").val("").focus();
		}
		else {
			$("div#card-body :input").prop("disabled", false);
		}
		
	});// 아이디가 pwdCheck 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.	
	
	
	$("input#email").blur( (e) => {

		const tag = $('input#email');
		
	//	const regExp_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
	//  또는
	    const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
	    // 이메일 정규표현식 객체 생성 
	    
	    const bool = regExp_email.test($(e.target).val());
		
		// === 이메일 중복 확인 ===
        $.ajax({
            url: "emailDuplicateCheck.wine",
            data: {"email":$(e.target).val()},
            type: "post",
            async : false,
            dataType : "json",
            success : function(json) {
                
                if(json.isExist) {
                    // 입력한 email이 이미 사용 중인 경우 (중복 O)
                    isEmailDuplicate = true;
                    
                } else {
                    isEmailDuplicate = false;
                }
            },
            error: function(request, status, error) {
                alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
            }
        });
		
		if(!bool) {
			// 이메일이 정규표현식에 위배된 경우 
			
			$("div#card-body :input").prop("disabled", true); 
			$(e.target).prop("disabled", false); 
			
			toastmsg.innerText="이메일 형식에 맞지 않습니다.";
			const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
			toastBootstrap.show();
			tag.removeClass("status-g");
            tag.addClass("status-ng");
		     	
			$(e.target).val("").focus(); 
		}
		else {
			$("div#card-body :input").prop("disabled", false);
		}
		
	});// 아이디가 email 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	$("input#phone").blur( (e) => {

		const tag = $('input#phone');

	    const regExp_phone = new RegExp(/^01[016789]{1}[0-9]{3,4}[0-9]{4}$/); 
	     
	    const bool = regExp_phone.test($(e.target).val());	
		
		if(!bool) {
			// 연락처 국번이 정규표현식에 위배된 경우 
			
			$("div#card-body :input").prop("disabled", true); 
			$(e.target).prop("disabled", false); 

			toastmsg.innerText="연락처 형식이 맞지 않습니다.";
			const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
			toastBootstrap.show();
			// tag.removeClass("status-g");
            tag.addClass("status-ng");

		     	
			$(e.target).val("").focus(); 
		}
		else {
			
			$("div#card-body :input").prop("disabled", false);

		}
		
	});// 아이디가 phone 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
});// end of $(document).ready(function(){})----------------------


// Function Declaration
// "수정하기" 버튼 클릭시 호출되는 함수 
function goEdit(){
	
// *** 필수입력사항에 모두 입력이 되었는지 검사하기 시작 *** //
	let b_requiredInfo = false;

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
	
	if(isNewPwd) { // 변경한 암호가 새로운 암호일 경우
 
		const frm = document.editFrm;
		frm.action = "memberEditEnd.up";
		frm.method = "post";
		frm.submit();
        
	}
	
}// end of function goEdit()-----------------------








