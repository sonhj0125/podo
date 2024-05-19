$(function (){
	
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


    // 포장
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

    const cbAll = $("input#cbAll");

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

});