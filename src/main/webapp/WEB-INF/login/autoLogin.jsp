<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>

<script type="text/javascript">
	window.onload = function(){
		const frm = document.loginFrm;
		frm.action = "<%= ctxPath%>/login/signin.wine";
		frm.method = "post";
		frm.submit();
	}
</script>

<body>
	<form name="loginFrm">
		<input type="hidden" name="userid" value="${requestScope.userid}" /> 
		<input type="hidden" name="pwd"  value="${requestScope.pwd}" />
		<input type="hidden" name="auto" value="auto">
	</form>
</body>

</html>