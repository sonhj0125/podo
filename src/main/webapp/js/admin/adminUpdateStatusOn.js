$( function (){


	
});



// 관리자 회원관리 - 회원 정지해제버튼 클릭시
function statusOn(){
	
	const userid = $("input#stUserid").val();
	const memberidx = $("input#stMemberidx").val();

	$.ajax({
         
        url: "adminUpdateStatusOn.wine",
        type: "post",
        data:{"userid":userid,
			  "memberidx":memberidx},
        dataType: "json",
        success: function(json) { 
            
            if(json.memberidx == 1) {
				alert("이미 해제된 회원입니다.");
				history.go(0);
			}
            else if (json.adstatusOn == 1) {
               	alert("회원 활동 해제되었습니다.");
               	history.go(0);
			}
			
        },
        error: function(request, status, error) {
            alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
        }
    }); // end of $.ajax
	
}
	