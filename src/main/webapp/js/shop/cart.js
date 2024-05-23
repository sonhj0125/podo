$(function() {
    // 페이지 로드될 때 체크된 상태 업데이트
    updateCheckedIndexes();

    // 패스 주소 가져오기
    const ctxPath = document.getElementById("getPath").innerText;

    // 총 가격 계산 함수
    function calculateSumPrice() {
        const pricearr = document.querySelectorAll(".priceOne");
        let sumPrice = 0;

        // 각 항목의 가격을 합산
        pricearr.forEach((item) => {
            const priceText = item.innerText.replace("원", "").replaceAll(",", "");
            const priceOne = Number(priceText);

            if (isNaN(priceOne)) {
                const unitPriceElement = item.closest('div.hstack').querySelector('.unitPrice');
                if (unitPriceElement) {
                    const unitPrice = Number(unitPriceElement.innerText.replace("원", "").replaceAll(",", ""));
                    const volumeElement = item.closest('div.hstack').querySelector('.dcntAll');
                    const volume = volumeElement ? Number(volumeElement.innerText.replace("EA", "")) : 1;
                    const calculatedPrice = unitPrice * volume;
                    sumPrice += calculatedPrice;
                    item.innerText = `${calculatedPrice.toLocaleString()}원`;
                }
            } else {
                sumPrice += priceOne;
            }
        });

        // 합산된 가격을 표시
        const sumPriceStr = sumPrice.toLocaleString();
        document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;
    }

    // 초기 총 가격 계산
    calculateSumPrice();

    // 나머지 초기화 코드 및 이벤트 핸들러 설정

    const indexIn = document.querySelectorAll("div.cart-index");
    let Arr = "";
    let firstSet = true;

    // 모든 cart-index 값을 하나의 문자열로 결합
    indexIn.forEach((item) => {
        if (firstSet) {
            firstSet = false;
            Arr += item.innerText;
        } else {
            Arr += "," + item.innerText;
        }
    });
    // 결합된 값을 hidden input 요소에 설정
    $('#setCindex').attr('value', Arr);
    $('#setCindexOne').attr('value', Arr);

    const dcntAll = document.querySelectorAll(".dcntAll");
    let cnt_Arr = "";
    let firstSetcnt = true;

    // 모든 dcntAll 값을 하나의 문자열로 결합
    dcntAll.forEach((item) => {
        if (firstSetcnt) {
            firstSetcnt = false;
            cnt_Arr += item.innerText;
        } else {
            cnt_Arr += "," + item.innerText;
        }
    });
    // 결합된 값을 hidden input 요소에 설정
    $('#setcVolume').attr('value', cnt_Arr);
    $('#setcVolumeOne').attr('value', cnt_Arr);

    // 체크된 항목의 인덱스를 로컬 스토리지에 저장하는 함수
    function saveCheckedIndexes() {
        const checkedIndexes = [];
        // 체크된 항목의 인덱스를 수집
        $('input.cbOne:checked').each(function() {
            const index = $(this).closest('div.hstack').next('div.cart-index').text().trim();
            checkedIndexes.push(index);
        });
        // 로컬 스토리지에 저장
        localStorage.setItem('checkedIndexes', JSON.stringify(checkedIndexes));
    }

    // 로컬 스토리지에서 체크된 항목의 인덱스를 복원하는 함수
    function restoreCheckedIndexes() {
        const savedIndexes = localStorage.getItem('checkedIndexes');
        if (savedIndexes) {
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

    // 페이지 로드 시 체크된 항목 복원
    restoreCheckedIndexes();

    const cbAll = $("input#cbAll");

    // "전체 선택" 체크박스를 초기 상태로 체크
    cbAll.prop('checked', true);

    const cbOne = $('input.cbOne');

    // 각 개별 체크박스를 초기 상태로 체크
    cbOne.each(function() {
        $(this).prop('checked', true);
    });

    // "전체 선택" 체크박스 변경 시 모든 개별 체크박스의 상태를 변경
    cbAll.on('change', function() {
        $('input.cbOne').prop('checked', $(this).prop('checked'));
        saveCheckedIndexes();
        updateCheckedIndexes();
    });

    // 개별 체크박스 변경 시 "전체 선택" 체크박스 상태를 업데이트
    $('input.cbOne').on('change', function() {
        let allChecked = true;
        $('input.cbOne').each(function() {
            if (!$(this).is(':checked')) {
                allChecked = false;
                return false; // 강제 반복문 종료
            }
        });

        // 모든 체크박스가 체크 해제되었으면 "Order-one" 버튼을 비활성화
        if (!allChecked) {
            $('#Order-one').prop('disabled', true);
        } else {
            $('#Order-one').prop('disabled', false);
        }
        
        // "전체 선택" 체크박스 상태를 업데이트
        cbAll.prop('checked', allChecked);
        saveCheckedIndexes();
        updateCheckedIndexes();
    });

    // div-volume 값을 초기화하고 setcVolumeOne을 업데이트하는 함수
    function initializeVolume() {
        const volumes = [];
        $('.dcntAll').each(function() {
            const volume = $(this).text().trim();
            volumes.push(volume);
        });
        $('#setcVolumeOne').val(volumes.join(','));
    }

    // 페이지 로드 시 초기화
    initializeVolume();

    // 장바구니의 가격을 업데이트하는 함수
    function updateCartPrice(volumeDiv) {
        // 개당 가격을 가져옴
        const pricePerItem = parseInt(volumeDiv.closest('div.hstack').find('.unitPrice').text().replace("원", "").replaceAll(",", ""));
        // 수량을 가져옴
        const volume = parseInt(volumeDiv.closest('div.hstack').find('.dcntAll').text().replace("EA", ""));
        // 총 가격 계산
        const totalPrice = pricePerItem * volume;
        // 총 가격을 업데이트
        volumeDiv.closest('div.hstack').find('.priceOne').text(totalPrice.toLocaleString() + "원");
        // 총 합산 가격 계산
        calculateSumPrice();
    }

    // +, - 버튼 클릭 이벤트 핸들러
    $(".plus_btn, .minus_btn").on("click", function() {
        const volumeDiv = $(this).siblings("div#div-volume");
        let volume = parseInt(volumeDiv.text().replace("EA", ""));
        if ($(this).hasClass("plus_btn")) {
            volume++;
        } else {
            if (volume > 1) {
                volume--;
            }
        }
        // 수량을 업데이트
        volumeDiv.text(volume + "EA");
        $(this).siblings("div#dcnt").text(volume);

        // 가격 업데이트
        updateCartPrice(volumeDiv);
        // 볼륨 값 초기화
        initializeVolume();
    });

    // "카트 삭제" 버튼 클릭 이벤트 핸들러
    $("#btn-cartDel").bind('click', function() {
        const frm = document.orderSetOne;
        frm.method = "post";
        frm.action = `${ctxPath}/cart/cartoutarr.wine`;
        frm.submit();
    });

    // "Order-one" 버튼 클릭 이벤트 핸들러 (미사용 코드)
    $("#Order-one").bind('click', function() {
        // const frm = document.orderSetOne;
        // frm.method = "post";
        // frm.action = `${ctxPath}/shop/order.wine`;
        // frm.submit();
    });

    // "Order-all" 버튼 클릭 이벤트 핸들러 (미사용 코드)
    $("#Order-all").bind('click', function() {
        const frm = document.orderSet;
        frm.method = "post";
        frm.action = `${ctxPath}/shop/order.wine`;
        frm.submit();
    });
}); // end of $(function()-------------------------------------------------------

function updateCheckedIndexes() {
    const checkedIndexes = [];
    // 체크된 항목의 인덱스를 수집
    $('input.cbOne:checked').each(function() {
        const index = $(this).closest('div.hstack').next('div.cart-index').text().trim();
        checkedIndexes.push(index);
    });

    if ($("input#cbAll").prop('checked')) {
        const allIndexes = [];
        // 모든 cart-index 값을 수집
        $('div.cart-index').each(function() {
            const index = $(this).text().trim();
            allIndexes.push(index);
        });
             $('#setCindexOne').attr('value', allIndexes.join(','));
      } else {
         $('#setCindexOne').attr('value', checkedIndexes.join(','));
      }

      // 합산된 가격 계산
      const sumPrice = calculateTotalPrice(checkedIndexes);
      const sumPriceStr = sumPrice.toLocaleString();
      document.querySelector("div#sumPrice").innerText = `${sumPriceStr}원`;

      // 체크된 항목들의 총 가격을 계산하는 함수
      function calculateTotalPrice(checkedIndexes) {
      let sumPrice = 0;
      checkedIndexes.forEach(function(index) {
         const priceText = $(`div.cart-index:contains('${index}')`).prev().find('.priceOne').text().replace("원", "").replaceAll(",", "");
         const price = Number(priceText);
         if (!isNaN(price)) {
            sumPrice += price;
         }
      });
         return sumPrice;
      }
   }      