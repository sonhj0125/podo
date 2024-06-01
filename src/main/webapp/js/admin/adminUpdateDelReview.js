$( function (){
	
	
});

// 관리자 회원관리 - 리뷰내역 삭제
function deleteReviewAd(rindex, userid){
	
	console.log(rindex);
	
	$.ajax({
         
        url: "adminUpdateDelReview.wine",
        type: "post",
        data:{"rindex":rindex, "userid":userid},
        dataType: "json",
        success: function(json) { 
			
            console.log(JSON.stringify(json));
            
            if(json.delReviewAd == 1) {
				alert("회원 리뷰 삭제 후 포인트 1000점 차감되었습니다.");
				history.go(0);
			}
			
        },
        error: function(request, status, error) {
            alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
        }
    }); // end of $.ajax
	
	
}	