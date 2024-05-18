$(function (){
	
	const pricearr = document.querySelectorAll(".priceOne");
    let sumPrice = 0;

    pricearr.forEach((item)=>{

        const priceOne = Number(item.innerText.replace("원", "").replace(",", ""));

        sumPrice += priceOne;

    });
    
    const sumPriceStr = sumPrice.toLocaleString();

    document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;

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