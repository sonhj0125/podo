$(function(){


    $('input#address').click(function(){

        new daum.Postcode({
            oncomplete: function (data) {

                let addr = '';

                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }

                document.getElementById("address").value = addr;
                document.getElementById("addressDetail").focus();

            }
        }).open();

    });

    $('input#birthday').keyup((e)=>{

        $(e.target).val("").next().show();

    });

});