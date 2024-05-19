$(function (){
    
	// 총가격 계산
	const pricearr = document.querySelectorAll(".priceOne");
    let sumPrice = 0;

    pricearr.forEach((item)=>{

        const priceOne = Number(item.innerText.replace("원", "").replaceAll(",", ""));

        sumPrice += priceOne;

    });
    
    const sumPriceStr = sumPrice.toLocaleString();

    document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;

    const indexIn = document.querySelectorAll("div.cart-index");
    let Arr = "";
    let fristSet = true;
    // end

    // Form 포장하기
    indexIn.forEach((item)=>{

        if(fristSet){
            fristSet = false;
            Arr += item.innerText;
        }else{
            Arr += ","+item.innerText;
        }
    })
    $('#setCindex').attr('value', Arr);
    //end

    //전부 check
    const cbAll = $("input#cbAll");

    cbAll.prop('checked', true);

    const cbOne = $('input.cbOne');

    cbOne.each(function(){
        $(this).prop('checked',true);
    })
    //end

    //체크 상태
    cbAll.on('change', function() {

        $('input.cbOne').prop('checked', $(this).prop('checked'));

    });

    $('input.cbOne').on('change',function(){
        
        let allChecked = true;
        $('input.cbOne').each(function() {
        if (!$(this).is(':checked')) {
            allChecked = false;
        }
        });

        if(allChecked){
            cbAll.prop('checked', true);
        }else{
            cbAll.prop('checked', false);
        }

    })
    // end



});