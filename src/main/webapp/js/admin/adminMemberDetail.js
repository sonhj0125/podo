$( function (){
	
	/*
	// 관리자 회원정지 //
	$("input#submit").click(function({
		
	
	
	$.ajax({
		url:"adminMemberDetail.jsp",
		type:"get",
	    data:dataObj,
	    dataType:"json",
		success:function(json){
			if(json.success_count == 1){
				$("input#submit").html("<span style='color:red; font-weight:bold;'>성공되었습니다.</span>");
			}
			else if(json.error_count != 0) {
				$("input#submit").html("<span style='color:red; font-weight:bold;'>실패되었습니다.</span>");
			}
		}
		},
		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
		
	});
	*/
	
	/*
	<div class="modal-body pt-0" id="modal-body">
          	<div>
            	<div class="input-group mb-3">
					<label class="input-group-text" for="pHomeTown">쿠폰</label>
					<form>
					<select class="form-select" name="aconame">
						<option selected>선택하세요.</option>
						<c:forEach var="acolist" items="${requestScope.codtoList}">
							<option value="${acolist.coname}">${acolist.coname}</option>
						</c:forEach>
					</select>
					<input type="hidden" value="${requestScope.mdto.userid}" id ="userid"/>
					</form> 
					<div class="form-group" style="margin-left:5%;">
						<input id="btn-couponRegister" class="btn btn-danger ml-4" type="button" value="등록" />
					</div>
				</div>
           	</div>
	</div>
	*/
	// 관리자 회원관리 - 쿠폰넣기 버튼 클릭시
	$("input:button[id='btn-couponRegister']").click( e => {
		
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
		console.log(userid);
		console.log(memberidx);

		 $.ajax({
             
             url: "/podo/member/admin/adminMemberDetail.wine",
             type: "post",
             data:{"userid":userid,
				   "memberidx":memberidx},
             dataType: "json",
             success: function(json) { 
                
                if(json.memberidx == 1) {
                   	alert("회원 정지되었습니다.");
                }
                else {
                	alert("회원이 이미 정지상태입니다.");
					
                }
             },
             error: function(request, status, error) {
                   alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
             }
          }); // end of $.ajax
		
	}); // end of $("input:button[id='btn-couponRegister']").click
	
	
	
}); // $(function (){});
