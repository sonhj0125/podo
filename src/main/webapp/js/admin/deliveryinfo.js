$(function() {

    const ostatus = $("div#ostatusshow").text();

    $("select[name='ostatus']").val(ostatus);

})

function registerDnumber(){

    const dnumber = $("input:text[name='dnumber']").val();
    const dindex = $("div#dindex").text();
    
    $.ajax({
        url: "updateRegisterDnum.wine",
        data : {"dnumber":dnumber,"dindex":dindex},
        type : "post",
        async : true,
        dataType : "json",
        success : function(json){

            if(json.result == true){
                alert("변경완료");
            }else{
                alert("변경실패");
            }

        },
        error: function(request) {
            alert("code: " + request.status );
        }
    })

}

function registerStatus(){

    const ostatus = $("select[name='ostatus']").val();
    const oindex = $("div#oindex").text();

    $.ajax({
        url: "updateRegisterOstatus.wine",
        data : {"ostatus":ostatus,"oindex":oindex},
        type : "post",
        async : true,
        dataType : "json",
        success : function(json){

            if(json.result == true){
                alert("변경완료");
            }else{
                alert("변경실패");
            }

        },
        error: function(request) {
            alert("code: " + request.status );
        }
    })

}