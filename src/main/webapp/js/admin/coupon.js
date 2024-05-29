$(function() {
    const method = document.getElementById("lab-method");
    const ctxPath = document.getElementById("getPath").innerText;

    document.getElementById("saleNat").addEventListener("change", () => {
        if (document.getElementById("saleNat").checked) {
            method.innerText = "할인 금액";
        }
    });

    document.getElementById("salePer").addEventListener("change", () => {
        if (document.getElementById("salePer").checked) {
            method.innerText = "할인율";
        }
    });

    $("#btn-couponRegister").bind("click", function(event) {
        event.preventDefault(); // 폼의 기본 동작 중지
        
        let isSubmit = true;

        // 필드 가져오기
        const couponNameElement = document.getElementById("couponname");
        const couponDiscountElement = document.getElementById("discount");
        const couponDateElement = document.getElementById("codate");

/*
        if (!couponNameElement) {
            alert("couponNameElement 필수 입력 요소가 누락되었습니다. 다시 확인해주세요.");
            return;
        }
        if (!couponDiscountElement) {
            alert("couponDiscountElement 필수 입력 요소가 누락되었습니다. 다시 확인해주세요.");
            return;
        }
        if (!minOrderAmountElement) {
            alert("minOrderAmountElement필수 입력 요소가 누락되었습니다. 다시 확인해주세요.");
            return;
        }
        if (!couponDateElement) {
            alert("couponDateElement필수 입력 요소가 누락되었습니다. 다시 확인해주세요.");
            return;
        }
*/

        const couponName = couponNameElement.value.trim(); // 쿠폰이름
        const couponDiscount = couponDiscountElement.value.trim(); // 할인금액
        const couponDate = couponDateElement.value; // 쿠폰기한 (~까지)
		
		
        // 유효성 검사
        if (couponName == "") {
            alert("쿠폰 이름을 입력하세요.");
            isSubmit = false;
        } else if (couponName.length > 20) {
            alert("쿠폰 이름은 20자를 초과할 수 없습니다.");
            isSubmit = false;
        } // 여기


		if(document.getElementById("saleNat").checked) {
	        if (couponDiscount == "" || isNaN(couponDiscount)) {
	            alert("할인 금액을 올바르게 입력하세요.");
	            isSubmit = false;
	        } else if (parseInt(couponDiscount) < 0) {
	            alert("할인 금액은 음수일 수 없습니다.");
	            isSubmit = false;
	        }
	    }

		if(document.getElementById("salePer").checked) {
	        if (couponDiscount == "" || isNaN(couponDiscount)) {
	            alert("할인율을 올바르게 입력하세요.");
	            isSubmit = false;
	        } else if (parseInt(couponDiscount) < 0) {
	            alert("할인율은 음수일 수 없습니다.");
	            isSubmit = false;
	        } else if (parseInt(couponDiscount) > 100) {
	            alert("할인율은 100% 를 넘을 수 없습니다.");
	            isSubmit = false;
			}
		}
        
        // 쿠폰 유효기간 검사
        const today = new Date();
        const couponDateObj = new Date(couponDate);

        if (couponDate == "" || couponDateObj <= today) {
            alert("쿠폰 기한은 최소 내일 이후여야 합니다.");
            isSubmit = false;
        }
        
        

        if (isSubmit) {
            const frm = document.couponRegister;
            frm.method = "post";
            frm.action = `${ctxPath}/coupon/couponregister.wine`;
            frm.submit();
        }
        
    }); // end of $("#btn-couponRegister").bind("click", function()

});
