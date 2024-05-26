$(function() {

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
            alert("숫자만 입력할수 있습니다!");
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

        const ctxPath = $("div#getctxPath").text();

        const width = 1000;
        const height = 600;

        const left = Math.ceil( (window.screen.width - width)/2 );
        const top = Math.ceil( (window.screen.height - height)/2 );
        
        window.open("", "PODO결제하기", `left=${left}, top=${top}, width=${width}, height=${height}`) ;

        const frm = document.orderfrm;
        frm.method = "post";
        frm.target = "PODO결제하기";
        frm.action = `${ctxPath}/shop/patment.wine`;
        frm.submit();

    });
    
})

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

    const frm = document.orderfrm;
    frm.method = "post";
    frm.action = `${ctxPath}/shop/orderend.wine`;
    frm.submit();

}