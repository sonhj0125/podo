$(function () {


    const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
	let checkPwd = false;

   
// 유효성 검사
// 비밀번호
$("input#pwd").blur( (e) => {
    
	const pwd = $(e.target).val().trim();
	const tag = $('input#pwd');

	const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
	// 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성
	const bool = regExp_pwd.test($(e.target).val());

	if(pwd == "") {
		toastmsg.innerText="비밀번호를 입력해주세요";
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
		toastBootstrap.show();
		checkPwd = false;
		tag.removeClass("status-g");
		tag.addClass("status-ng");
	}else if (!bool) {
		toastmsg.innerHTML="비밀번호은 숫자/문자/특수문자 포함하여<br>8~15자리 이내로 작성해주세요.";
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
		toastBootstrap.show();
		checkPwd = false;
		tag.removeClass("status-g");
		tag.addClass("status-ng");
	}else{
		checkPwd = true;
		tag.addClass("status-g");
		tag.removeClass("status-ng");
	}

});

// 비밀번호 확인
$("input#pwd2").blur( (e) => {

	const pwd = $('input#pwd').val();
	const pwd2 = $(e.target).val().trim();
	const tag = $('input#pwd2');

	if(pwd != pwd2){
		toastmsg.innerText="비밀번호가 일치하지 않습니다";
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
		toastBootstrap.show();
		checkPwdCheck = false;
		tag.removeClass("status-g");
		tag.addClass("status-ng");
	}else{
		checkPwdCheck = true;
		tag.addClass("status-g");
		tag.removeClass("status-ng");
	}

});
    

});

function goPwdUpdate() {
	
	const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
	let checkPwd = false;
	
	if($("input#pwd").val().trim() == "") {
		toastmsg.innerText="비밀번호를 입력해주세요";
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
		toastBootstrap.show();
		
	} else if($("input#pwd2").val().trim() == "") {
		toastmsg.innerText="비밀번호를 입력해주세요";
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
		toastBootstrap.show();
		
	} else {
		
		let isNewPwd = true;
		
		$.ajax({
			url: "duplicatePwdCheck.wine",
			data: {"pwd":$("input#pwd").val()
				,"userid":$("input:hidden[name='userid']").val()},
			type: "post",
			async : false,
			dataType : "json",
			success : function(json) {

				if(json.isExists) {
					// 입력한 암호가 이미 사용중이라면
					alert("현재 사용중인 비밀번호로 비밀번호 변경은 불가합니다.");
					isNewPwd = false;
				}
			},
			error: function(request, status, error) {
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}
		});

		if(isNewPwd) { // 변경한 암호가 새로운 암호일 경우
	
			const frm = document.pwdUpdatefrm;
			frm.action = "pwdUpdateEnd2.wine";
			frm.method = "post";
			frm.submit();
			
		}
	}
	
	


}



