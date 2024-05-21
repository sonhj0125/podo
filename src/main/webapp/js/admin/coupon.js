$(function() {
    
    const method = document.getElementById("lab-method");

    document.getElementById("saleNat").addEventListener("change",()=>{

        if(document.getElementById("saleNat").checked){
            method.innerText = "할인 금액";
        }

    });

    document.getElementById("salePer").addEventListener("change",()=>{

        if(document.getElementById("salePer").checked){
            method.innerText = "할인율";
        }

    })
	
})

// 쿠폰 유효성 검사
function couponRegister(ctxPath){

    const frm = document.couponRegister;
    frm.method = "post";
    frm.action = ctxPath+"/coupon/couponregister.wine";
    frm.submit();

}