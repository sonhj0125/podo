<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctxPath = request.getContextPath();
	
	String url = "";
	url += request.getAttribute("javax.servlet.forward.request_uri");
	if(request.getQueryString() != null){
		url = url + "?" + request.getQueryString();
	}
	
   String uri = request.getRequestURI();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포도</title>
    
    <%-- bootstrap --%>
    <link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
    
    <%-- jQuery --%>
	<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>
	
	<%-- Font --%>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">	
	
	<%-- jQueryUI CSS 및 JS --%>
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/jquery-ui.min.css" />
	<script type="text/javascript" src="<%= ctxPath%>/js/jquery-ui.min.js"></script>
	
	<%-- 직접만든 CSS --%>
    <link rel="stylesheet" href="<%=ctxPath %>/css/index/index.css">
    
    <style>
    
    div.login_sub_btn {
        border: solid 0px red;
        display: flex;
        margin: 3% auto;
    }

    button#idFind,
    button#pwdFind {
        width: 48%;
    }

    button#pwdFind {
        margin-left: 5%;
    }
    
    footer {
       padding : 0 2%;
       
    }
    
    div#loginModal{
       height: 80rem;
       
    }
    
    header{
       font-family: "PT Serif";
    }
    div.offcanvas{
       font-family: "Noto Sans KR";
    }
    
	</style>
	
	<script type="text/javascript">
	
	window.onload = ()=> {
		
		<%-- keycode == 27 == Esc--%>
		document.addEventListener("keydown", function(e) {
		  let code = (e.keyCode ? e.keyCode : e.which);
		  
		  if (code == 27) {
		    let nextState = toggleSwitch.getAttribute('aria-label') == 'unchecked' ? 'checked' : 'unchecked';
		    toggleSwitch.setAttribute('aria-label', nextState);
		  }
		})
		
		<%-- Header Home --%>
		$("label#navTitle").bind('click',()=>{
			location.href="<%=ctxPath%>/index.wine";
		});
		
		<%-- Footer Home --%>
		$("label#btnHome").bind('click',()=>{
			location.href="<%=ctxPath%>/index.wine";
		})
		
		<%-- Header Shop Click --%>
		$("label#btnShop").bind('click',()=>{
			location.href="<%=ctxPath%>/shop/list.wine";
		});
		
		<%-- Header Shop Click --%>
		$("label#btnEvent").bind('click',()=>{
			location.href="<%=ctxPath%>/shop/event.wine";
		});
		
		<%-- Header Shop Click --%>
		$("label#btnAbout").bind('click',()=>{
			location.href="<%=ctxPath%>/shop/about.wine";
		});
		
		<%-- Header Sign out Click --%>
		$("label#btnSignout").bind('click',()=>{
			const frm = document.passFrm;
		    frm.action = "<%=ctxPath%>/login/signout.wine";
		    frm.submit();
		});
		
		<%-- Header Shop Click --%>
		$("label#btnCart").bind('click',()=>{
			location.href="<%=ctxPath%>/cart/cart.wine";
		});
		
		<%-- MyPage Click --%>
		$("div#MyInfoView").bind('click',()=>{
			location.href="<%=ctxPath%>/member/myinfoView.wine";
		});
		
		<%-- MyPage Click --%>
		$("div#MemberEdit").bind('click',()=>{
			location.href="<%=ctxPath%>/member/memberEdit.wine?userid=${sessionScope.loginUser.userid}";
		});
		
		<%-- MyPage Click --%>
		$("div#PwdUpdate").bind('click',()=>{
			location.href="<%=ctxPath%>/member/pwdUpdate.wine?userid=${sessionScope.loginUser.userid}";
		});
		
		<%-- MyPage Click --%>
		$("div#OrderList").bind('click',()=>{
			location.href="<%=ctxPath%>/member/orderList.wine";
		});
		
		<%-- MyPage Click --%>
		$("div.reviewList").bind('click',()=>{
			location.href="<%=ctxPath%>/member/reviewList.wine";
		});
		
		<%-- MyPage Click --%>
		$("div#PwdUpdate").bind('click',()=>{
			location.href="<%=ctxPath%>/member/pwdUpdate.wine?userid=${sessionScope.loginUser.userid}";
		});
		
		<%-- 모달창을 끄면 모달창을 새로고침--%>
		$("button#btn-close").click(function(){
			javascript:history.go(0);
		
		});
		
		
	
		$("input:text[name='memo']").hide();
		
		$('#order_msg').change(function() {
		
			const order_msg = $("select[name='order_msg']").val();
		       if(order_msg == "직접입력") { 
					$("input:text[name='memo']").show();
					return false;
		       }
		       else {
		    	   $("input:text[name='memo']").hide();
		       }
	
		});
		
		<%-- search 클릭 시 검색창 자동 focus --%>
		$("#searchModal").on('shown.bs.modal', function () {
		  $("input#searchWord").trigger('focus');
		});
		
		<%-- 와인 검색 (input 창에서 엔터 시) --%>
		$("input#searchWord").bind("keydown", function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				goSearch();
			}
		});
		
		<%-- 와인 검색 (버튼 클릭 시) --%>
		$("button#wineSearch").click(function() {
			goSearch();
		});
		
		
		<%-- 마이페이지 쇼핑정보 쿠폰 클릭시 --%>
		$("div#memberCoupon").bind('click',()=>{
			location.href="<%=ctxPath%>/member/mypageShopCoupon.wine";
		});
		$("div#memberCoupon2").bind('click',()=>{
			location.href="<%=ctxPath%>/member/mypageShopCoupon.wine";
		});
		
		<%-- 마이페이지 쇼핑정보 포인트 클릭시 --%>
		$("div#memberPoint").bind('click',()=>{
			location.href="<%=ctxPath%>/member/mypageShopPoint.wine";
		});
		<%-- 마이페이지 쇼핑정보 포인트 클릭시 --%>
		$("div#memberPoint2").bind('click',()=>{
			location.href="<%=ctxPath%>/member/mypageShopPoint.wine";
		});
		
		
		<%-- 관리자 회원관리 클릭시 --%>
        $("div#adminMember").bind('click',()=>{
         	location.href="<%=ctxPath%>/member/admin/adminMember.wine";
        });
      
		<%-- 관리자 제품등록 클릭시 --%>
		$("div#adminProduct").bind('click',()=>{
		   location.href="<%=ctxPath%>/member/admin/adminProduct.wine";
		});
		
		<%-- 관리자 주문관리 클릭시 --%>
		$("div#adminOrder").bind('click',()=>{
		   location.href="<%=ctxPath%>/member/admin/adminOrder.wine";
		});
		
	} // end of window.onload
	
	window.closeModal = function(login) {
		
	    $('#loginModal').modal('hide');
	    
	    if(!login){ // 회원가입일 경우
	    	location.href="<%=ctxPath%>/member/memberRegister.wine";
	    }else{ // 로그인일 경우
	    	$('#spinner').show();
	    	setTimeout(function() {
	    		$('#spinner').hide();
			    javascript:history.go(0);
			}, 1200);
	    }
	    
	}// end of window.closeModal
	
	// product 보기
	function showProduct(idx){
		
		location.href = "<%=ctxPath %>/shop/product.wine?pindex="+idx;
		
	}
	
	<%-- 와인 검색 --%>
	function goSearch() {
		
		const searchWord = $("input#searchWord").val();
		
		if(searchWord == "") {
			alert("와인 이름을 입력해주세요!");
			
		} else {
			const frm = document.searchFrm;
			frm.action = "<%=ctxPath%>/shop/search.wine";
			frm.submit();
		}
	}
	
</script>
    
</head>
<body>
    <nav class="py-2 bg-dark" style="background-color: #800000;">
        <div class="container d-flex flex-wrap justify-content-center">
            <div class="nav">
                <label id="navTitle" class="text-align-center curpointer" style="color: white;"><img src="<%=ctxPath %>/images/title.png" class="img-fluid mx-auto d-block" style="max-width: 40%;"></label>
            </div>
            <div id="spinner" class="spinner-border text-light z-1 position-absolute p-2 top-0 start-0" role="status" style="display: none; margin-left: 3%; margin-top: 30px;">
			  <span class="visually-hidden">Loading...</span>
			</div>
			<c:if test="${not empty sessionScope.loginUser}">
				<div class="position-absolute z-1 position-absolute p-2 top-0 start-0" style="margin-left: 1%; margin-top: 50px;">
  						<span class="btn badge text-bg-light" data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">${sessionScope.loginUser.userid}</span>
				</div>
			</c:if>
        </div>
    </nav>
    <header class="py-1 mb-2 d-flex flex-wrap ">
            <div class="p-2 me-2 d-flex me-auto">
                <ul class="nav">
                    <li class="nav-item fw-bold"><label id="btnShop" class="nav-link link-body-emphasis px-2 curpointer">Shop</label></li>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnEvent" class="nav-link link-body-emphasis px-2 curpointer">Event</label></li>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnAbout" class="nav-link link-body-emphasis px-2 curpointer">About</label></li>
                </ul>
            </div>
            <div class="p-2 me-2 d-flex">
                <ul class="nav">
                   <c:if test="${empty sessionScope.loginUser}">
                       <li class="nav-item fw-bold"><label id="btnSignup" class="nav-link link-body-emphasis px-2 curpointer" data-bs-toggle="modal" data-bs-target="#loginModal">Sign in</label></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.loginUser}">
                       <li class="nav-item fw-bold"><label id="btnSignout" class="nav-link link-body-emphasis px-2 curpointer">Sign out</label></li>
                    </c:if>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnCart" class="nav-link link-body-emphasis px-2 curpointer">Cart</label></li>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnSearch" class="nav-link link-body-emphasis px-2 curpointer" data-bs-toggle="modal" data-bs-target="#searchModal">Search</label></li>
                </ul>
            </div>
            
            <%-- Sign in Modal --%>
            <div class="modal fade" id="loginModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content rounded-4 shadow">
                      <div class="modal-header p-5 pb-4 border-bottom-0">
                          <h1 class="fw-bold mb-0 fs-2">PODO</h1>
                          <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
      
                      <div class="modal-body p-5 pt-0">
                          <div id="login">
	                        <iframe id="iframe_login" style="border: none; width: 100%; height: 420px;" src="<%=ctxPath%>/login/login.wine">
	                        </iframe>
                     	  </div>
                      </div>
                  </div>
              </div>
          </div>
          <%-- Sign in Modal 끝 --%>
          
           <%-- 검색 Modal --%>
             <form name="searchFrm">
               <div class="modal fade" id="searchModal" aria-labelledby="exampleModalLabel" aria-hidden="true">
                 <div class="modal-dialog modal-lg">
                   <div class="modal-content">
                     <div class="modal-body">
                     <div class="input-group">
                          <div class="form-outline" data-mdb-input-init style="padding-left: 8.5%;">
                            <input type="search" id="searchWord" name="searchWord" class="form-control" style=" width: 600px; maxlength=20; height: 50px;" placeholder="와인을 검색하세요"/>                             
                          </div> 
                          <button type="button" class="btn btn-primary" id="wineSearch" style="height: 50px;" data-mdb-ripple-init>
                                  <i class="fas fa-search"></i>
                          </button>
                     </div>
                     </div>
                   </div>
                 </div>
               </div>
            </form>
           <%-- 검색 Modal --%>
           
           <%-- 관리자 쿠폰 등록 클릭시 나오는 Modal --%>
            <div class="modal fade" id="adminCoupon" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog modal-lg">
                  <div class="modal-content rounded-4 shadow">
                      <div class="modal-header p-5 pb-4 border-bottom-0">
                          <h1 class="fw-bold mb-0 fs-3"><img src="<%=ctxPath%>/images/coupon.png" style="width:35px; vertical-align: text-top;">&nbsp;쿠폰 등록</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
      
                      <div class="modal-body pt-0">
                      	<div>
	                       	<iframe id="iframe_coupon" style="border: none; width: 100%; height: 600px; margin:0 auto;" src="<%=ctxPath%>/member/admin/adminCoupon.wine">
	                       	</iframe>
                     	</div>
                      </div>
                  </div>
              </div>
          </div>
    	<%-- 관리자 쿠폰 등록 클릭시 나오는 Modal 끝 --%>
      
    </header>
    
    <%-- 로그인 후 상단에 아이디 버튼 클릭 시 마이페이지 오프캔버스 나오기 --%>
   <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop" aria-labelledby="staticBackdropLabel">
   
     <div class="offcanvas-header">
       <h3 style="font-weight: bold;">마이페이지</h3>
       <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
     </div>
     
     <div class="offcanvas-body">

            <hr style="width: 90%; color: purple; border: solid 2px;">       
            <div style="font-size: 15pt; margin:3% 0;"><span style="font-weight: bold; color: purple;">${sessionScope.loginUser.name}</span>님 안녕하세요!</div>
           <div style="display:flex; width: 90%; justify-content: space-between; text-align: center;">
            <div id="reviewBtn" class="reviewList" class="position-relative" style="margin-right: 5%; cursor: pointer;">
            	<c:if test="${sessionScope.reviewCnt != 0}">
               		<span class="position-absolute top-1 ms-4 translate-middle p-1 border border-light rounded-circle" style="background-color: #cc99ff;"></span>
            	</c:if>
                  <i class="fa-regular fa-newspaper" style="margin: 10%;">
                  </i>
                  <br>
                  <div style="font-weight: bold;">리뷰</div>
                  <div style="color: purple;">${sessionScope.reviewCnt}</div>
            </div>
            <div style="margin-right: 5%; cursor: pointer;">
               <i class="fa-solid fa-ticket"></i>
               <div id="memberCoupon2" style="font-weight: bold; cursor: pointer;">쿠폰</div>
               <div style="color: purple;">2</div>
            </div>
            <div style="cursor: pointer;">
               <i class="fa-solid fa-circle-dollar-to-slot"></i>
               <br>
               <div id="memberPoint2" style="font-weight: bold; cursor: pointer;">적립금</div>
               <div style="color: purple;">${sessionScope.loginUser.point}</div>
            </div>
           </div>
           <hr style="width: 90%; color: purple; border: solid 2px;">  
     
        <div>
         <h4 style="font-weight: bold; margin-top: 13%;">개인정보</h4>
         <hr style="width: 90%;">
            <div>
               <c:if test="${not empty sessionScope.loginUser}">
                  <div id="MyInfoView" style="display: flex; margin-bottom: 2%; cursor: pointer;">내정보 열람</div>
               </c:if>
               <c:if test="${not empty sessionScope.loginUser}">
                  <div id="MemberEdit" style="display: flex; margin-bottom: 2%; cursor: pointer;">내정보 수정</div>
               </c:if>
               <c:if test="${not empty sessionScope.loginUser}">
                  <div id="PwdUpdate" style="display: flex; margin-bottom: 2%; cursor: pointer;">비밀번호 변경</div>
               </c:if>
            </div>
       </div>
       
       <div>
         <h4 style="font-weight: bold; margin-top: 13%;">쇼핑정보</h4>
         <hr style="width: 90%;">
            <div>
               <div id="OrderList" style="display: flex; margin-bottom: 2%; cursor: pointer;">주문내역조회</div>
               <div id="reviewPage" class="reviewList" style="display: flex; margin-bottom: 2%; cursor: pointer;" >리뷰관리</div>
               <div id="memberCoupon" style="display: flex; margin-bottom: 2%; cursor: pointer;">쿠폰</div>
               <div id="memberPoint" style="display: flex; margin-bottom: 2%; cursor: pointer;">적립금</div>
            </div>
       </div>
       
       <c:if test="${sessionScope.loginUser.memberIdx == '9'}">
	      <div>
	         <h5 style="font-weight: bold; margin-top: 13%;">관리자 전용 메뉴</h5>
	         <hr style="width: 90%;">
	            <div>
	               <div id="adminOrder" style="display: flex; margin-bottom: 2%; cursor: pointer;">주문 관리</div>
	               <div id="adminMember" style="display: flex; margin-bottom: 2%; cursor: pointer;">회원 관리</div>
	               <div id="adminProduct" style="display: flex; margin-bottom: 2%; cursor: pointer;">제품 등록</div>
	               <div style="display: flex; margin-bottom: 2%; cursor: pointer;" data-bs-toggle="modal" data-bs-target="#adminCoupon">쿠폰 등록</div>
	           </div>
	       </div>
        </c:if>
	    
	  </div>
	  
	</div>
	
	<%-- 현 주소 기록 --%>
	<form action="post" name="passFrm" style="display: none;">
    	<input type="text" value="<%= url %>" name="url">
    </form>
