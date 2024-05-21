$(function () {

    const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
    let checkName = false;
    let checkPwd = false;
    let checkPwdCheck = false;
    let checkEmail = false;
    let checkPhone = false;


    $('input#address').click(function () {

        new daum.Postcode({
            oncomplete: function (data) {

                let addr = '';

                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }

                document.getElementById("address").value = addr;
                document.getElementById("addressDetail").focus();

            }
        }).open();

    });


    // 유효성 검사
    // 이름
    $("input#name").blur( (e) => {

        const regExp_name = new RegExp(/^[가-힣]{2,10}$/); 
        const bool = regExp_name.test($(e.target).val());

        if(name == ""){
            toastmsg.innerText="이름을 입력해주세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkName = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");
        }else if(!bool){
            toastmsg.innerText="올바른 이름이 아닙니다.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkName = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");
        }else{
            checkName = true;
            tag.addClass("status-g");
            tag.removeClass("status-ng");
        }

    });

    // 비밀번호
    $("input#pwd").blur( (e) => {
    
        const pwd = $(e.target).val().trim();
        const tag = $('input#pwd');

        const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
        // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성
        const bool = regExp_pwd.test($(e.target).val());

        if(pwd == "") {
			pwd.val("${sessionScope.loginUser.pwd}")

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
    $("input#pwdCheck").blur( (e) => {
    
        const pwd = $('input#pwd').val();
        const pwdCheck = $(e.target).val().trim();
        const tag = $('input#pwdCheck');
    
        if(pwd != pwdCheck){
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

    let isEmailDuplicate; // 이메일 중복 확인용

    // 이메일 확인
    $("input#email").blur( (e) => {

        const email = $(e.target).val().trim();
        const tag = $('input#email');

        const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
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

        if(email == "") {
            toastmsg.innerText="이메일을 입력해주세요.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkEmail = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");

        } else if(!bool){
            toastmsg.innerText="올바른 이메일 형식이 아닙니다.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkEmail = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");

        } else if(isEmailDuplicate) {
            toastmsg.innerText="중복된 이메일입니다. 다시 입력해주세요!";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkEmail = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");

        } else{
            checkEmail = true;
            tag.addClass("status-g");
            tag.removeClass("status-ng");
        }

    });

    // 연락처 확인
    $("input#phone").blur( (e) =>{

        const phone = $(e.target).val().trim();
        const tag = $('input#phone');

        const regExp_phone = new RegExp(/^01[016789]{1}[0-9]{3,4}[0-9]{4}$/);
        const bool = regExp_phone.test($(e.target).val());

        if(phone == ""){
            toastmsg.innerText="연락처를 입력해주세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkPhone = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");
        }else if(!bool){
            toastmsg.innerHTML="올바른 연락처가 아닙니다.<br>하이폰[-]를 빼고 입력해주세요.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkPhone = false;
            tag.removeClass("status-g");
            tag.addClass("status-ng");
        }else{
            checkPhone = true;
            tag.addClass("status-g");
            tag.removeClass("status-ng");
        }

    });

    $("#register").bind("click",()=>{
		
        if(checkUserid && checkName && checkPwd && checkPwdCheck && checkEmail && checkPhone){
            goRegister(toastLive,toastmsg);
        }else{
            toastmsg.innerHTML="올바르게 입력하세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
        }

	});

});

function goRegister(toastLive,toastmsg) {

    const address = $("input#address").val().trim();
    const addressDetail = $("input#addressDetail").val().trim();
    const birthday = $("input#birthday").val().trim();
    const birthdaySplit = birthday.split("-");

    const today = new Date();
    const birthDate = new Date(birthdaySplit[0], birthdaySplit[1], birthdaySplit[2]);

    let age = today.getFullYear()
            - birthDate.getFullYear()
            + 1;

    if(address == ""){
        toastmsg.innerHTML="주소를 입력하세요";
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
        toastBootstrap.show();
        return;
    }else if(addressDetail == ""){
        toastmsg.innerHTML="상세주소를 입력하세요";
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
        toastBootstrap.show();
        return;
    }else if(birthday == ""){
        toastmsg.innerHTML="생년월일을 입력하세요";
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
        toastBootstrap.show();
        return;
    }else if(age<20){
        toastmsg.innerHTML="20세 미만은 가입할수 없습니다";
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
        toastBootstrap.show();
        return;
    }

    const frm = document.Registerfrm;
    frm.action = "memberEditEnd.wine";
    frm.method = "post";
    frm.submit();

}