$(function() {
    // 페이지 로드될 때 체크된 상태 업데이트
    updateCheckedIndexes();

    // 패스 주소 가져오기
    const ctxPath = document.getElementById("getPath").innerText;

    // 총가격 계산
    const pricearr = document.querySelectorAll(".priceOne");
    let sumPrice = 0;

    pricearr.forEach((item) => {
        const priceOne = Number(item.innerText.replace("원", "").replaceAll(",", ""));
        sumPrice += priceOne;
    });

    const sumPriceStr = sumPrice.toLocaleString();
    document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;

    const indexIn = document.querySelectorAll("div.cart-index");
    let Arr = "";
    let fristSet = true;

    // Form 포장하기
    indexIn.forEach((item) => {
        if(fristSet) {
            fristSet = false;
            Arr += item.innerText;
        } else {
            Arr += "," + item.innerText;
        }
    });
    $('#setCindex').attr('value', Arr);
    $('#setCindexOne').attr('value', Arr);
    
    // 처음에는 #setCindex 값을 넣어주고 변경사항이 있을 경우 #setCindexOne 의 값을 넣어준다.
    
    // 체크된 항목의 인덱스를 로컬 스토리지에 저장하는 함수
    function saveCheckedIndexes() {
        const checkedIndexes = [];
        $('input.cbOne:checked').each(function() {
            const index = $(this).closest('div.hstack').next('div.cart-index').text().trim();
            checkedIndexes.push(index);
        });
		// 로컬에 현재 체크된 인덱스 값 저장 
		// JSON 형식의 문자열로 변환
        localStorage.setItem('checkedIndexes', JSON.stringify(checkedIndexes));
    }

    // 이전에 저장한 체크된 항목의 인덱스를 로컬 스토리지에서 복원하는 함수
    function restoreCheckedIndexes() {
		// 체크박스 체크될 때마다 로컬에 저장되는 checkedIndexes 가져오기
        const savedIndexes = localStorage.getItem('checkedIndexes');
        if (savedIndexes) { 
			// 체크된 것이 있을 경우
			// JavaScript 배열로 변환
            const checkedIndexes = JSON.parse(savedIndexes);
            checkedIndexes.forEach((index) => {
                $('input.cbOne').each(function() {
                    const cbIndex = $(this).closest('div.hstack').next('div.cart-index').text().trim();
                    if (index == cbIndex) {
                        $(this).prop('checked', true);
                    }
                });
            });
        }
    }

    // 페이지 로드 시 이전에 체크한 항목을 복원
    restoreCheckedIndexes();

    // 체크 박스 변경 시 동작
    const cbAll = $("input#cbAll");

    cbAll.prop('checked', true);

    const cbOne = $('input.cbOne');

    cbOne.each(function() {
        $(this).prop('checked', true);
    });

	// 전체선택 체크박스가 변경될 때마다
    cbAll.on('change', function() {
		// 변경된 체크박스에 체크해주고
        $('input.cbOne').prop('checked', $(this).prop('checked'));
        // 변경된 체크박스의 인덱스값 저장한 후
        saveCheckedIndexes();
        // 결제 예정금액에 값을 넣어준다
        updateCheckedIndexes();
    });

	// 체크박스 낱개가 변경될 때마다
    $('input.cbOne').on('change', function() {
        let allChecked = true;
        $('input.cbOne').each(function() {
            if (!$(this).is(':checked')) {
                allChecked = false;
            }
        });

        if(allChecked) {
            cbAll.prop('checked', true);
        } else {
            cbAll.prop('checked', false);
        }
		// 변경된 체크박스의 인덱스값 저장한 후
        saveCheckedIndexes();
        // 결제 예정금액에 값을 넣어준다
        updateCheckedIndexes();
    });


    // Link

    $("#btn-cartDel").bind('click',function(){
		
		const frm = document.orderSetOne;
		frm.method = "post";
		frm.action = `${ctxPath}/cart/cartoutarr.wine`;
		frm.submit();
		
	});

});

// cbAll이 변경되었을 때 결제 예정금액을 가져오는 함수
function updateCheckedIndexes() {
    const checkedIndexes = [];
    $('input.cbOne:checked').each(function() {
        const index = $(this).closest('div.hstack').next('div.cart-index').text().trim();
        checkedIndexes.push(index);
    });

    // cbAll이 체크되어 있으면 setCindexOne에 모든 인덱스 값을 설정
    if ($("input#cbAll").prop('checked')) {
        const allIndexes = [];
        $('div.cart-index').each(function() {
            const index = $(this).text().trim();
            allIndexes.push(index);
        });
        $('#setCindexOne').attr('value', allIndexes.join(','));
    } else {
        // cbAll이 체크되어 있지 않다면 체크된 항목의 인덱스를 설정
        $('#setCindexOne').attr('value', checkedIndexes.join(','));
    }

    // 결제 예정금액 가져오기
    const sumPrice = calculateTotalPrice(checkedIndexes);
    const sumPriceStr = sumPrice.toLocaleString();
    document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;
}

// 체크된 상품들의 총 가격을 계산하는 함수
function calculateTotalPrice(checkedIndexes) {
    let sumPrice = 0;
    checkedIndexes.forEach(function(index) {
        const priceText = $(`div.cart-index:contains('${index}')`).prev().find('.priceOne').text().replace("원", "").replaceAll(",", "");
        const price = Number(priceText);
        sumPrice += price;
    });
    return sumPrice;
}