<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<!-- bootstrap -->
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>

    
<!-- jQuery -->
<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>
   
<!-- Font -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">   
   
<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-ui.min.js"></script>


<table class="table">
	<thead>
		<tr>
		  	<th scope="col">회원아이디</th>
		  	<th scope="col">변동포인트</th>
		  	<th scope="col">변동내역</th>
		  	<th scope="col">변동일자</th>
		</tr>
	</thead>
	
  	<tbody>
  	<c:forEach var="list" items="${requestScope.ldtoList}">
		<tr>
			<th scope="row">${list.userid}</th>
			<td>${list.logindate}</td>
			<td>${list.ipaddress}</td>
			<td>${list.ipaddress}</td>
    	</tr>
    </c:forEach>
 	</tbody>
</table>




