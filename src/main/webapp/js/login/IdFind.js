$(document).ready(function() {

    $("input:text[name='name']").focus();
    $("span.errorid").hide();
    $("span.erroremail").hide();

    $("input:text[name='name']").bind("change", function(){
        $("span.errorid").hide(); 
    });

    $("input:text[name='email']").bind("change", function(){
        $("span.erroremail").hide(); 
    });
	    
    const method = "${requestScope.method}";
    // console.log("~~ 확인용 method => " + method);
    /*
       ~~ 확인용 method => GET
       ~~ 확인용 method => POST
    */
    
    if(method == "GET") {
       $("div#div_findResult").hide();
    }
    
    // 찾기 눌렀을 때 성명, 이메일이 사라지지 않고 입력칸에 그대로 남아있게 하기 위해 (GET 일때는 감추고, POST 일 때만 보인다)
    if(method == "POST") {
       $("input:text[name='name']").val("${requestScope.name}"); 
       $("input:text[name='email']").val("${requestScope.email}");
    }
    
    
    $("button#btn-success").click(function(){
       goFind();
    });
    
    $("input:text[name='email']").bind("keyup", function(e){
       if(e.keyCode == 13) {
          goFind();
       }
    });
    
 });// end of $(document).ready(function(){})-------------
	
	
	// Function Declaration
	function goFind() {
		
		const name = $("input:text[name='name']").val().trim();
		
		if(name == "") {

            $("span.errorid").show();
			// alert("성명을 입력하세요!!");
            $("input:text[name='name']").focus();
			return; //  종료
        }
        
		const email = $("input:text[name='email']").val();
		
	    //  const regExp_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
		//  또는
			const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
			// 이메일 정규표현식 객체 생성 
		    
	        const bool = regExp_email.test(email);
	    
	        if(!bool) {
	            // 이메일이 정규표현식에 위배된 경우
	            
                $("span.erroremail").show();
                $("input:text[name='email']").val("").focus();
	            return; // 종료
	        }
          
	        
	        const frm = document.idFindFrm;
	        frm.action = "<%= ctxPath %>/login/idFind.up";
	        frm.method = "post";
	        frm.submit();
	}// end of function goFind()
