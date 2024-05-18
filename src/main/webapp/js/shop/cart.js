$(function (){
	
	const pricearr = document.querySelectorAll(".priceOne");
    let sumPrice = 0;

    pricearr.forEach((item)=>{

        const priceOne = Number(item.innerText.replace("원", "").replace(",", ""));

        sumPrice += priceOne;

    });
    
    const sumPriceStr = sumPrice.toLocaleString();

    document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;

    

});