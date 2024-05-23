$(function() {

    const sumOneAllPrice = document.querySelectorAll("div.priceOne");

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
                    
                    $("input#pointuse").prop("value","");
                    document.getElementById("pointsale").innerText = "0원";

                    finalPrice = sumPrice - salePrice + 3000;
                    document.getElementById("totalPrice").innerText = finalPrice.toLocaleString()+"원";

                },
                error: function(request) {
                    alert("code: " + request.status );
                }
            })

        }else{
            $("input#pointuse").prop("value","");
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
            alert("숫자만 입력할수 있습니다!");
        }else{

            let outprice = sumPrice - salePrice;

            if(outprice<point){
                alert(`최대 ${outprice}포인트 까지만 사용가능합니다.`);
            }
            else{
                document.getElementById("pointsale").innerText = "-"+Number(point).toLocaleString()+"원";
            }

        }

    });

    let indexArr = [];

    $(".indexAll").each(function(){

        let index = $(this).text();
        indexArr.push(index);

    });

    const indexjoinArr = indexArr.join(",");
    $("input#idxArrjoin").val(indexjoinArr);

    $("#btn-doorder").bind("click",function(){

        const ctxPath = $("div#getctxPath").text();

        const frm = document.orderfrm;
        frm.method = "post";
        frm.action = `${ctxPath}/shop/orderEnd.wine`;
        frm.submit();

    });
    
})