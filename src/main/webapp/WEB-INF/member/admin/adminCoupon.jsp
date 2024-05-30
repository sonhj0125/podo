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


<script type="text/javascript" src="<%= ctxPath %>/js/admin/coupon.js"></script>

<style>
div.form-group {
	margin-top: 5%;
}

input.form-control, div#saleMethod {
	margin-top: 3%;
}

.widy{
	width: 90%;
	padding: 0;
	margin: 0 auto;
}

div#saleprice {
	width: 20%;
}


div#salepercent {
	width: 20%;
}

</style>

<div class="col-md-9 widy">
	<form name="couponRegister">
		<div class="form-group">
			<div class="row">
				<div class="col-md-3">
					<label for="coname" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;쿠폰 이름</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="couponname" name="coname" placeholder="신규회원 가입 감사쿠폰" autocomplete="off" /> 
				</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="row">	
				<div class="col-md-3">
					<label for="content" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;쿠폰 내용</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="content" name="codetail" placeholder="신규회원 가입 감사쿠폰" />
				</div>
			</div>	
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-3">
					<label for="" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;할인 방법</label>
				</div>
				<div id="saleMethod" style="display:flex;" >
					<div class="form-check" id="saleprice" >
					  <input class="form-check-input" type="radio" name="cotype" value="1" id="saleNat" checked>
					  <label class="form-check-label" for="saleNat">
					    할인금액
				  	  </label>
					</div>
					
					<div class="form-check" id="salepercent">
					  <input class="form-check-input" type="radio" name="cotype" value="2" id="salePer">
					  <label class="form-check-label" for="salePer">
					    할인율
					  </label>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-md-3">
					<label id="lab-method" for="discount" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;할인금액</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="text" id="discount" name="codiscount" autocomplete="off" />
				</div>
			</div>	
		</div>
		
		<div class="form-group">
		    <div class="row">
				<div class="col-md-3">
					<label for="" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;쿠폰 기한 (~까지)</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" type="date" name="codate" id="codate" />
				</div>
			</div>	
		</div>
		
		<div class="form-group">
		    <div class="row">
				<div class="col-md-3">
					<label for="cocode" style="font-weight:bold; font-size:14pt;"><img src="<%=ctxPath %>/images/1.png">&nbsp;&nbsp;쿠폰 코드</label>
				</div>
				<div class="col-md-9">
					<input class="form-control" id="cocode" type="text" name="cocode" placeholder="쿠폰 코드(16글자)"/>
				</div>
			</div>	
		</div>
		
		
		<div class="form-group custom-submit d-grid gap-2 col-6 mx-auto">
			<input id="btn-couponRegister" class="btn btn-danger mt-3" type="button" value="전송" />
		</div>
	</form>
	
	<div id="getPath" style="display: none;"><%=ctxPath %></div>
	
</div>
			



