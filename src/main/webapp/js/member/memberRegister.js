$(function(){

    const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
    let checkUserid = false;
    let checkPwd = false;
    let checkPwdCheck = false;
    let checkEmail = false;

    $('input#address').click(function(){

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

    $('input#birthday').keyup((e)=>{

        $(e.target).val("").next().show();

    });

    // 유효성 검사

    // UserID
    $("input#userid").blur( (e) => {
    
        const userid = $(e.target).val().trim();

        if(userid == "") {
            toastmsg.innerText="아이디를 입력해주세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkUserid = false;
        }else if(userid.length<6) {
            toastmsg.innerText="아이디는 6글자 이상으로 입력해주세요.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkUserid = false;
        }else{
			checkUserid = true;
		}
    
    });

    // 비밀번호
    $("input#pwd").blur( (e) => {
    
        const pwd = $(e.target).val().trim();

        const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
        // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성
        const bool = regExp_pwd.test($(e.target).val());

        if(pwd == "") {
            toastmsg.innerText="비밀번호를 입력해주세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkPwd = false;
        }else if (!bool) {
            toastmsg.innerText="비밀번호은 숫자/문자/특수문자 포함하여 8~15자리 이내로 작성해주세요.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkPwd = fasle;
        }else{
			checkPwd = true;
		}
    
    });

    // 비밀번호 확인
    $("input#pwdCheck").blur( (e) => {
    
        const pwd = $('input#pwd').val();
        const pwdCheck = $(e.target).val().trim();
    
        if(pwd != pwdCheck){
            toastmsg.innerText="비밀번호가 일치하지 않습니다";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkPwdCheck = false;
        }else{
            checkPwdCheck = true;
        }

    });

    // 이메일 확인
    $("input#email").blur( (e) => {

        const email = $(e.target).val().trim();

        const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
	    const bool = regExp_email.test($(e.target).val());

        if(email == ""){
            toastmsg.innerText="이메일 입력해주세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkEmail = false;
        }else if(!bool){
            toastmsg.innerText="올바른 이메일형식이 아닙니다.";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
            checkEmail = false;
        }else{
            checkEmail = true;
        }

    });

});

function goRegister(){

}