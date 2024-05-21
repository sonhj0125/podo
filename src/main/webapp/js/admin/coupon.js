$(function() {
    
    const method = document.getElementById("lab-method");
    const ctxPath = document.getElementById("getPath").innerText;

    document.getElementById("saleNat").addEventListener("change",()=>{

        if(document.getElementById("saleNat").checked){
            method.innerText = "할인 금액 (원)";
        }

    });

    document.getElementById("salePer").addEventListener("change",()=>{

        if(document.getElementById("salePer").checked){
            method.innerText = "할인 율 (%)";
        }

    })

    $("#btn-couponRegister").bind("click",function(){

        let isSubmit = true;
        // 여기에 유효성 검사 유효성 검사 틀리면 isSubmit false로 변환하면 됨
        // ex) isSubmit = false;

        if(isSubmit){
            const frm = document.couponRegister;
            frm.method = "post";
            frm.action = `${ctxPath}/coupon/couponregister.wine`;
            frm.submit();
        }

    });
	
})