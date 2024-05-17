<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<script type="text/javascript">

	const msg = '${requestScope.msg}';

	if(msg != "" || msg != null){
		alert(msg);	
	}
	
	location.href="${requestScope.loc}";

</script>