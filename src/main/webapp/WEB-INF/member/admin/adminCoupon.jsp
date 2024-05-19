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


<style>
div.form-group {
	margin-top: 5%;
}

input.form-control, textarea.form-control {
	margin-top: 5%;
}

input#content {
	height: 80px;
}

form#coupon {
	width: 90%;
}

</style>
     

<div class="col-md-9">
	<form id="coupon">
		<div class="form-group">
			<div class="row">
				<div class="col-md-3">
					<label for="">▶ 쿠폰 이름</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="" placeholder="신규회원 가입 감사쿠폰" autocomplete="off" required /> 
				</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="row">	
				<div class="col-md-3">
					<label for="">▶ 쿠폰 내용</label>
				</div>
				<div class="col-md-9">
					<textarea class="form-control" id="content" placeholder="신규회원 가입 감사쿠폰" required /></textarea>
				</div>
			</div>	
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-3">
					<label for="">▶ 할인율/할인금액</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="" placeholder="5,000원" autocomplete="off" required />
				</div>
			</div>	
		</div>
		<div class="form-group">
		    <div class="row">
				<div class="col-md-3">
					<label for="">▶ 최소 주문금액</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="" placeholder="10,000원"/>
				</div>
			</div>	
		</div>
		<div class="form-group">
		    <div class="row">
				<div class="col-md-3">
					<label for="">▶ 쿠폰기한</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="date" id="" placeholder="2024-05-19"/>
				</div>
			</div>	
		</div>
		<div class="form-group custom-submit d-grid gap-2 col-6 mx-auto">
			<input class="btn btn-danger mt-3" type="submit" value="전송">
		</div>
	</form>
</div>
			



