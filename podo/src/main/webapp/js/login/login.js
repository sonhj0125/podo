
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


// function Declaration
 
// === 로그인 처리 함수 === //
function goLogin() {

    // alert("확인용 로그인 처리하러 간다");

    if( $("input#loginUserid").val().trim() == "" ) {
        alert("아이디를 입력하세요!!");
        $("input#loginUserid").val("").focus();
        return; // goLogin() 함수 종료
    }

    if( $("input#loginPwd").val().trim() == "" ) {
        alert("암호를 입력하세요!!");
        $("input#loginPwd").val("").focus();
        return; // goLogin() 함수 종료
    }


    if($("input:checkbox[id='saveid']").prop("checked")) { // ==> input:checkbox#saveid
        // alert("아이디 저장 체크를 하셨네요~");
        localStorage.setItem('saveid', $("input#loginUserid").val());
                            //  key                value
        
    } else {
        // alert("아이디 저장 체크를 해제하셨네요~");
        localStorage.removeItem('saveid');
    }

    const frm = document.loginFrm;
    frm.submit();

} // end of function goLogin()

function doregister(){

    window.parent.closeModal();

}