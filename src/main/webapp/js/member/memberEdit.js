
let b_emailcheck_click = false;
// "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도

let b_email_change = false;
// 이메일값을 변경했는지 여부를 알아오기 위한 용도

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
	
	
	$("input#email").blur( (e) => {
		
	//	const regExp_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
	//  또는
	    const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
	    // 이메일 정규표현식 객체 생성 
	    
	    const bool = regExp_email.test($(e.target).val());	
		
		if(!bool) {
			// 이메일이 정규표현식에 위배된 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 이메일이 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 email 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
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
	
	
	$("input#postcode").blur( (e) => {
		
	//	const regExp_postcode = /^[0-9]{5}$/;  
	//  또는
	//	const regExp_postcode = /^\d{5}$/;
	    const regExp_postcode = new RegExp(/^\d{5}$/);  
	    // 숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
	    
	    const bool = regExp_postcode.test($(e.target).val());	
		
		if(!bool) {
			// 우편번호가 정규표현식에 위배된 경우 
			
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
			
		//	$(e.target).next().next().show();
		//  또는
		    $(e.target).parent().find("span.error").show();
		     	
			$(e.target).val("").focus(); 
		}
		else {
			// 우편번호가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
			
			//	$(e.target).next().next().hide();
		    //  또는
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 postcode 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
	
	
	// === "우편번호찾기"를 클릭했을 때 이벤트 처리하기 === //
	$("img#zipcodeSearch").click(function(){
		
		// 주소를 쓰기가능 으로 만들기
		$("input#address").removeAttr("readonly");
        
        // 참고항목을 쓰기가능 으로 만들기
        $("input#extraAddress").removeAttr("readonly");
        
        // 주소를 활성화 시키기
	//	$("input#address").removeAttr("disabled");
        
        // 참고항목을 활성화 시키기
    //  $("input#extraAddress").removeAttr("disabled");
		
		new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
        
        // 주소를 읽기전용(readonly) 로 만들기
        $("input#address").attr("readonly", true);
        
        // 참고항목을 읽기전용(readonly) 로 만들기
        $("input#extraAddress").attr("readonly", true);
        
        // 주소를 비활성화 로 만들기
    //  $("input#address").attr("disabled", true);
        
        // 참고항목을 비활성화 로 만들기
    //  $("input#extraAddress").attr("disabled", true);
        
	});// end of $("img#zipcodeSearch").click()------------
		 
	 
	// "이메일중복확인" 을 클릭했을 때 이벤트 처리하기
	$("span#emailcheck").click(function() {

		 
		 b_emailcheck_click = true;
		 // "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도 
		 
		 $.ajax({
			 url:"emailDuplicateCheck2.up",
			 data:{"email":$("input#email").val()
			      ,"userid":$("input:hidden[name='userid']").val()}, // data 속성은 http://localhost:9090/MyMVC/member/emailDuplicateCheck.up 로 전송해야할 데이터를 말한다. 
			 type:"post",  //  type 을 생략하면 type:"get" 이다.
			 
			 async:true,   // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
         		           // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다.   
			 
			 dataType:"json", // Javascript Standard Object Notation.  dataType은 /MyMVC/member/emailDuplicateCheck.up 로 부터 실행되어진 결과물을 받아오는 데이터타입을 말한다. 
         		              // 만약에 dataType:"xml" 으로 해주면 /MyMVC/member/emailDuplicateCheck.up 로 부터 받아오는 결과물은 xml 형식이어야 한다. 
         		              // 만약에 dataType:"json" 으로 해주면 /MyMVC/member/emailDuplicateCheck.up 로 부터 받아오는 결과물은 json 형식이어야 한다. 
			  
			 success:function(json){
				 
				 if(json.isExists) {
					 // 입력한 email 이 이미 사용중이라면
					 $("span#emailCheckResult").html($("input#email").val() + " 은 이미 사용중 이므로 다른 이메일을 입력하세요").css({"color":"red"}); 
					 $("input#email").val(""); 
				 }
				 else {
					 // 입력한 email 이 존재하지 않는 경우라면 
					 $("span#emailCheckResult").html($("input#email").val() + " 은 사용가능 합니다").css({"color":"navy"});  
				 }
				 
			 },
			 
			 error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
		 });
		 
	 });	
	
	
	 // 이메일값이 변경되면 수정하기 버튼을 클릭시 "이메일중복확인" 을 클릭했는지 클릭안했는지를 알아보기위한 용도 초기화 시키기  
	 $("input#email").bind("change", function(){
		 
		 b_emailcheck_click = false;
		 // "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도  
		 
		 b_email_change = true;
         // 이메일값을 변경했는지 여부를 알아오기 위한 용도
	 });
	
	
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
	
	if(isNewPwd) { // 변경한 암호가 새로운 암호일 경우
 
		const frm = document.editFrm;
		frm.action = "memberEditEnd.up";
		frm.method = "post";
		frm.submit();
        
	}
	
}// end of function goEdit()-----------------------








