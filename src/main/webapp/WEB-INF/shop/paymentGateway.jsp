<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String ctxPath = request.getContextPath();
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>

<script type="text/javascript">

window.onload = () => {
   var IMP = window.IMP;
   IMP.init('imp83065850');  // 중요!!  아임포트에 가입시 부여받은 "가맹점 식별코드". 
   IMP.request_pay({
       pg : 'html5_inicis',
       pay_method : 'card',
       merchant_uid : 'merchant_' + new Date().getTime(),
       name : '${requestScope.productName}',
       amount : 100, //${requestScope.price},
       buyer_email : '${requestScope.email}',
       buyer_name : '${requestScope.name}',
       buyer_tel : '${requestScope.mobile}',
       buyer_addr : '${requestScope.address}',
   }, function(rsp) {
		if ( rsp.success ) {
			
		    self.close();
		    opener.location.href = "javascript:paymentcomplete();";
		
        } else {
        	
            self.close();
            
       }

   });

};

</script>
</head>	
<body>
</body>
</html>