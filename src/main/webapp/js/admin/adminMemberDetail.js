$( function (){

	// 관리자 회원관리 - 쿠폰넣기 버튼 클릭시
	$("input:button[id='btn-couponRegister']").click( e => {
		
		$("input:button[name='statusOn']").hide();

		const userid = $("input#userid").val();
		const coname = $("select[name='aconame']").val();
		//console.log(userid);
		//console.log(coname);

		 $.ajax({
             
             url: "/podo/member/admin/adminMemberDetail.wine",
             type: "post",
             data:{"userid":userid,
            	   "coname":coname
                  },
             dataType: "json",
             success: function(json) { 
                
                if(json.admycodtoList == 1) {
                   	alert("쿠폰 등록되었습니다.");
					$("select[name='aconame']").val("");
					$("div#adminCouponIn").modal('hide');

                }
                else {
                	alert("쿠폰 등록 실패");
					$("div#adminCouponIn").modal('hide');
                }
             },
             error: function(request, status, error) {
                   alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
             }
          }); // end of $.ajax
		
	}); // end of $("input:button[id='btn-couponRegister']").click



	// 관리자 회원관리 - 회원 정지버튼 클릭시
	$("input:button[name='statusOff']").click( e => {
		
		const userid = $("input#stUserid").val();
		const memberidx = $("input#stMemberidx").val();

		$.ajax({
             
            url: "/podo/member/admin/adminMemberDetail.wine",
            type: "post",
            data:{"userid":userid,
				  "memberidx":memberidx},
            dataType: "json",
            success: function(json) { 
                
                if(json.memberidx == 1) {
                   	alert("회원 활동 정지처리되었습니다.");
				}
				else {
					alert("회원 정지 해제되었습니다.");
				}
				
					
            },
            error: function(request, status, error) {
                alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
            }
        }); // end of $.ajax
		
	}); // end of $("input:button[name='statusOff']").click


	

}); // $(function (){});
