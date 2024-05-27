$(function () {
  

    const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
    let checkName = false;
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
                // document.getElementById("addressDetail").val("");
                document.getElementById("addressDetail").focus();
                

            }
        }).open();

    });


    // 유효성 검사
    // 이름
    $("input#name").bind("change",function (e) {

        const name = $(e.target).val().trim();
        const tag = $('input#name');

        const regExp_name = new RegExp(/^[가-힣]{2,10}$/); 
        const bool = regExp_name.test($(e.target).val());

        if(name == "") {
            toastmsg.innerText="이름은 필수입력사항입니다.";
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


    let isEmailDuplicate; // 이메일 중복 확인용

    // 이메일 확인
    $("input#email").blur( (e) => {

        const email = $(e.target).val().trim();
        const tag = $('input#email');

        const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
	    const bool = regExp_email.test($(e.target).val());

        // === 이메일 중복 확인 ===
        $.ajax({ 
            url: "emailDuplicateCheck2.wine",
            data:{"email":$("input#email").val()
			     ,"userid":$("input:hidden[name='userid']").val()},
            type: "post",
            async : false,
            dataType : "json",
            success : function(json) {
                
                if(json.isExists) {
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
    $("input#phone").bind("change",function (e) {

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

    $("button#memberEdit").bind("click",()=> {
		
        if(checkName && checkEmail && checkPhone) {
            goEdit(toastLive,toastmsg);

        } else {
            toastmsg.innerHTML="올바르게 입력하세요";
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
            toastBootstrap.show();
        }

	});

});

function goEdit(toastLive,toastmsg) {

    const address = $("input#address").val().trim();
    const addressDetail = $("input#addressDetail").val().trim();


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
    }

    const frm = document.MemberEditFrm;
    frm.action = "memberEditEnd.wine";
    frm.method = "post";
    frm.submit();

}