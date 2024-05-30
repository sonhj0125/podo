$(function() {

    const toastLive = document.getElementById('liveToast');
    const toastmsg = document.getElementById('toast-msg');
    let checkName = true;
    let checkEmail = true;
    let checkPhone = true;
    let checkAddress = true;
    let checkAddressDetail = true;


    const sumOneAllPrice = document.querySelectorAll("div.priceOne");
    const myPoint = $("span#myPoint").text();

    let sumPrice = 0; // 총 가격 (NUM)
    let salePrice = 0; // 쿠폰 할인 가격

    sumOneAllPrice.forEach((item)=>{

        const withoutPrice = item.innerText.replace("원", "").replaceAll(",", "");
        
        sumPrice += Number(withoutPrice);

    });

    document.getElementById("sumPrice").innerText = sumPrice.toLocaleString()+"원";

    let finalPrice = sumPrice + 3000; // 최종결제 가격
    
    document.getElementById("totalPrice").innerText = finalPrice.toLocaleString()+"원";

    $("#select-coupon").on('change',function(){

        let selectCoupon = this.value;

        if(selectCoupon != "-- 쿠폰 선택 --"){

            $.ajax({
                url: "checkcoupondate.wine",
                data : {"coname":selectCoupon},
                type : "post",
                async : true,
                dataType : "json",
                success : function(json){
                    if(json.cotype == "1"){
                        salePrice = json.codiscount;
                    }else{
                        salePrice = sumPrice * (Number(json.codiscount)/100);
                    }

                    document.getElementById("couponsale").innerText = "-"+salePrice.toLocaleString()+"원";
                    
                    $("input#pointuse").prop("value","0");
                    document.getElementById("pointsale").innerText = "0원";

                    finalPrice = sumPrice - salePrice + 3000;
                    document.getElementById("totalPrice").innerText = finalPrice.toLocaleString()+"원";

                },
                error: function(request) {
                    alert("code: " + request.status );
                }
            })

        }else{
            $("input#pointuse").prop("value","0");
            document.getElementById("pointsale").innerText = "0원"
            salePrice = 0;
            finalPrice = sumPrice - salePrice + 3000;
            document.getElementById("couponsale").innerText = "0원";
            document.getElementById("totalPrice").innerText = finalPrice.toLocaleString()+"원";
        }

    });




    $("input#pointuse").blur( (e) => {

        const point = $(e.target).val().trim();

        const regExp_point = new RegExp(/^[0-9]{1,10}$/);  
	    const bool = regExp_point.test($(e.target).val());

        if(!bool){
            alert("숫자만 입력할 수 있습니다!");
            $("input#pointuse").prop("value","0");
            document.getElementById("pointsale").innerText = "0원"
        }else{

            let outprice = sumPrice - salePrice;

            if(outprice<point){
                alert(`최대 ${outprice}포인트 까지만 사용가능합니다.`);
                $("input#pointuse").prop("value","0");
                document.getElementById("pointsale").innerText = "0원"
            }else if(point > Number(myPoint)){
                alert("내 보유 포인트보다 높게 설정 할수 없습니다.");
                $("input#pointuse").prop("value","0");
                document.getElementById("pointsale").innerText = "0원"
            }else if(point == "0"){
                document.getElementById("pointsale").innerText = "0원"
            }
            else{
                document.getElementById("pointsale").innerText = "-"+Number(point).toLocaleString()+"원";
            }

        }

        totalSet();

    });

    let indexArr = [];

    $(".indexAll").each(function(){

        let index = $(this).text();
        indexArr.push(index);

    });

    const indexjoinArr = indexArr.join(",");
    $("input#idxArrjoin").val(indexjoinArr);

    $("#btn-doorder").bind("click",function(){

		if(!checkName || !checkEmail || !checkPhone || !checkAddress || !checkAddressDetail){
			if(!checkName) {
	            toastmsg.innerHTML="이름을 올바르게 입력하세요";
	            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	            toastBootstrap.show();
	            return false;
            }
            
            if (!checkEmail) {
	            toastmsg.innerHTML="이메일을 올바르게 입력하세요";
	            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	            toastBootstrap.show();
	            return false;
			}
			
			if (!checkPhone) {
	            toastmsg.innerHTML="연락처를 올바르게 입력하세요";
	            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	            toastBootstrap.show();
	            return false;
			}
			
			if (!checkAddress) {
	            toastmsg.innerHTML="주소를 올바르게 입력하세요";
	            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	            toastBootstrap.show();
	            return false;
			}
			
			if (!checkAddressDetail) {
	            toastmsg.innerHTML="상세주소를 올바르게 입력하세요";
	            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	            toastBootstrap.show();
	            return false;
			}
        }


        const ctxPath = $("div#getCtxPath").text();
        const title = "Payment";

        const width = 1000;
        const height = 600;

        const left = Math.ceil( (window.screen.width - width)/2 );
        const top = Math.ceil( (window.screen.height - height)/2 );
        
        window.open("", title, `left=${left}, top=${top}, width=${width}, height=${height}`);

        const frm = document.orderfrm;
        frm.method = "post";
        frm.target = title;
        frm.action = `${ctxPath}/shop/payment.wine`;
        frm.submit();

    });
    
    		// 주문자 정보 변경시 유효성 검사 ===============================================
	
	// 처음에 값이 들어가 있기 때문에 bool check 를 true 로 해줬다가 blue 처리 되면 false 로 시작 //
	
	
	
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
                document.getElementById("addressDetail").focus();

            }
        }).open();

    });
	
	
		
		    // 이름
    $("input#name").blur( (e) => {

		checkName = false;

        const name = $(e.target).val().trim();
        const tag = $('input#name');

        const regExp_name = new RegExp(/^[가-힣]{2,10}$/); 
        const bool = regExp_name.test($(e.target).val());

        if(name == ""){
            toastmsg.innerText="이름을 입력해주세요";
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

    }); // name 유효성 검사
    
        // 이메일 확인
    $("input#email").blur( (e) => {

		checkEmail = false;

        const email = $(e.target).val().trim();
        const tag = $('input#email');

        const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
        const bool = regExp_email.test($(e.target).val());
		
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

        } else{
            checkEmail = true;
            tag.addClass("status-g");
            tag.removeClass("status-ng");
        }
	});	// email 유효성검사
	
	
	    // 연락처 확인
    $("input#phone").blur( (e) =>{
		
		checkPhone = false;

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

    }); // phone 유효성 검사
    
    
    $("input#address").blur( (e) =>{
		
		checkAddress = false;

		const address = $(e.target).val().trim();
    
	    // 주소 유효성검사
	    if(address == ""){
	        toastmsg.innerText="주소를 입력해주세요";
	        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	        toastBootstrap.show();
	        checkAddress = false;
	        tag.removeClass("status-g");
	        tag.addClass("status-ng");
	    }
	    else {
	        checkAddress = true;
	        tag.addClass("status-g");
	        tag.removeClass("status-ng");
	    }
	});	
	    
	    
	$("input#addressDetail").blur( (e) =>{
		
		checkAddressDetail = false;
		
		const addressDetail = $(e.target).val().trim();
	    
	    if(addressDetail == ""){
	        toastmsg.innerText="상세주소를 입력해주세요";
	        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive);
	        toastBootstrap.show();
	        checkAddressDetail = false;
	        tag.removeClass("status-g");
	        tag.addClass("status-ng");
	    }
	    else {
	        checkAddressDetail = true;
	        tag.addClass("status-g");
	        tag.removeClass("status-ng");
	    }
	    
    });
		// 유효성 검사 끝 // =======================================================
    
    
}); // end of $(function() --------------------------

function totalSet(){
	
    const totalprice = $("#sumPrice").text().replace("원","").replaceAll(",","");
    const couponprice = $("#couponsale").text().replace("-","").replace("원","").replaceAll(",","");
    const pointdiscount = $("#pointsale").text().replace("-","").replace("원","").replaceAll(",","");

    const totalplaceNum = Number(totalprice);
    const couponplaceNum = Number(couponprice);
    const pointdiscountNum = Number(pointdiscount);

    const finalPrice = totalplaceNum - couponplaceNum - pointdiscountNum + 3000;

    $("#totalPrice").text(finalPrice.toLocaleString()+"원");

}

function paymentcomplete(){

    const ctxPath = $("div#getCtxPath").text();

    const frm = document.orderfrm;
    frm.method = "post";
    frm.action = `${ctxPath}/shop/orderend.wine`;
    frm.submit();

}