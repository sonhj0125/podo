
$(document).ready(function(){

    $("button#btnSubmit").click(function(){
        goLogin();

    });

    $("input#loginPwd").bind("keydown", function(e){
        if(e.keyCode == 13) { //  암호입력란에 엔터를 했을 경우
            goLogin(); //  로그인 시도한다.
        }
    });

});// end of $(document).ready(function(){})------------------------------------

function goLogin() {


    if( $("input#loginUserid").val().trim() == "" ) {
        alert("아이디를 입력하세요!!");
        $("input#loginUserid").val("").focus();
        return; 
    }

    if( $("input#loginPwd").val().trim() == "" ) {
        alert("암호를 입력하세요!!");
        $("input#loginPwd").val("").focus();
        return; 
    }

    const staff = "login";

    const frm = document.loginFrm;
    frm.method = "POST"
    frm.action = "signin.wine"
    frm.submit();
    window.parent.closeModal(staff);
    
}

function doregister(){

    const staff = "register";

    window.parent.closeModal(staff);

}

function goIdFind(ctxPath) {
	
	location.href=ctxPath+"/login/idFind.wine";
}

function goPwdFind(ctxPath) {
	
	location.href=ctxPath+"/login/pwdFind.wine";

}