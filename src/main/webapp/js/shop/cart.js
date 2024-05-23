$(function() {

    // 패스 주소 가져오기
    const ctxPath = document.getElementById("getPath").innerText;

    // 초기 세팅 시작

    const pindexAll = [];

    $('div.cart-index').each(function(){
        pindexAll.push($(this).text());
    });

    $('input#setCindex').val(pindexAll.join(","));

    const cbAll = $("input#cbAll");
    cbAll.prop('checked', true);

    const cbOne = $('input.cbOne');

    cbOne.each(function() {
        $(this).prop('checked', true);
    });

    // 초기 세팅 완료

    //"전체 선택" 체크박스 변경 시 모든 개별 체크박스의 상태를 변경
    cbAll.on('change', function() {
        $('input.cbOne').prop('checked', $(this).prop('checked'));
    });

    // 개별 체크박스 변경 시 "전체 선택" 체크박스 상태를 업데이트
    $('input.cbOne').on('change', function() {

        let allChecked = true;

        $('input.cbOne').each(function() {

            if (!$(this).is(':checked')) {
                allChecked = false;
            }
        });

        if(allChecked){
            cbAll.prop("checked",true);
        }else{
            cbAll.prop("checked",false);
        }

        updateone();

    });

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
        volumeDiv.text(volume + "EA");
        $(this).siblings("div#dcnt").text(volume);

        updateone();
        sumOne();

    });

    // "카트 삭제" 버튼 클릭 이벤트 핸들러
    $("#btn-cartDel").bind('click', function() {
        updateone();
        const frm = document.orderSetOne;
        frm.method = "post";
        frm.action = `${ctxPath}/cart/cartoutarr.wine`;
        frm.submit();
    });

    // "Order-one" 버튼 클릭
    $("#Order-one").bind('click', function() {
        updateone();
        const frm = document.orderSetOne;
        frm.method = "post";
        frm.action = `${ctxPath}/shop/order.wine`;
        frm.submit();
    });

    // "Order-all" 버튼 클릭
    $("#Order-all").bind('click', function() {
        const frm = document.orderSet;
        frm.method = "post";
        frm.action = `${ctxPath}/shop/order.wine`;
        frm.submit();
    });

    // 순차 데이터 삽입
    updateone();
    sumAll();

});

function updateone(){

    let pindexArr = [];
    let pvolumeArr = [];
    let pvolumeAllArr = [];

    $("input.cbOne").each(function() {

        const volumeAll = $(this).parent().find("div#dcnt").text();
        pvolumeAllArr.push(volumeAll);

        if($(this).is(':checked')){
            const volumeOne = $(this).parent().find("div#dcnt").text();
            pvolumeArr.push(volumeOne);
            const indexOne = $(this).parent().find("div#didx").text();
            pindexArr.push(indexOne);
        }

    })

    const pindexStr = pindexArr.join(",");
    const pvolumeStr = pvolumeArr.join(",");
    const pvolumeAllStr = pvolumeAllArr.join(",");

    $("input#setcVolume").val(pvolumeAllStr);
    $("input#setCindexOne").val(pindexStr);
    $("input#setcVolumeOne").val(pvolumeStr);

}

function sumOne(){

    $("input.cbOne").each(function() {

        const volume = $(this).parent().find("div#dcnt").text();
        const priceNat = $(this).parent().find("div#dprice").text();
        const priceNum = priceNat.replace("원","").replaceAll(",","");

        const sumone = Number(volume) * Number(priceNum);

        $(this).parent().find("div#dpriceSum").text(sumone.toLocaleString()+"원");

        sumAll();

    })

}

function sumAll(){

    let sumAll = 0;

    $("input.cbOne").each(function() {
    
        const sumoneNat = $(this).parent().find("div#dpriceSum").text();

        const sumone = sumoneNat.replace("원","").replaceAll(",","");
    
        sumAll += Number(sumone);

    })

    $("div#sumPrice").text(sumAll.toLocaleString()+"원");

}