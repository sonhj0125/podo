	$(document).ready(function() {

        $("input:text[name='userid']").focus();
        $("span.errorid").hide();
        $("span.erroremail").hide();

        $("input:text[name='userid']").bind("change", function(){
            $("span.errorid").hide(); 
        });
    
        $("input:text[name='email']").bind("change", function(){
            $("span.erroremail").hide(); 
        });
	    
	    $("button.btn-Primary").click(function(){
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
		
		const userid = $("input:text[name='userid']").val().trim();
		
		if(userid == "") {
            $("span.errorid").show();
            $("input:text[name='userid']").focus();
            return;
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
	            return;
	        }
	        
	        const frm = document.pwdFindFrm;
	        frm.action = "<%= ctxPath %>/login/pwdFind.up";
	        frm.method = "post";
	        frm.submit();
	}// end of function goFind()----------------------------------------
