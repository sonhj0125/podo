$( function (){
	
	
});

// 관리자 회원관리 - 회원 정지버튼 클릭시
function statusOff(){
	
	const userid = $("input#stUserid").val();
	const memberidx = $("input#stMemberidx").val();

	$.ajax({
         
        url: "adminUpdateStatusOff.wine",
        type: "post",
        data:{"userid":userid, "memberidx":memberidx},
        dataType: "json",
        success: function(json) { 
            
            if(json.memberidx == 3) {
				alert("이미 정지된 회원입니다.");
				history.go(0);
			}
            else if(json.adstatusOff == 1) {
               	alert("회원 활동 정지되었습니다.");
               	history.go(0);
			}
			
        },
        error: function(request, status, error) {
            alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
        }
    }); // end of $.ajax
	
	
}	
	
	
	
	
	
