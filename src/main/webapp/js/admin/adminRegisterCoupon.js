$( function (){
	
	
	
});	
	
	
function registerCouponAd(){
	
	const userid = $("input#userid").val();
	const coname = $("select[name='aconame']").val();
	
	
	 $.ajax({
             
         url: "adminRegisterCoupon.wine",
         type: "post",
         data:{"userid":userid,"coname":coname},
         dataType: "json",
         success: function(json) { 
            
            if(json.admycodtoList == 1) {
               	alert("쿠폰 등록되었습니다.");
				$("select[name='aconame']").val("");
				$("div#adminCouponIn").modal('hide');
				history.go(0);

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
	
	
}
	

	
	
