<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctxPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포도</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
    
    <!-- jQuery -->
	<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>
	
	<!-- Font -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">	
	
	<!-- 직접만든 CSS -->
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
    
	</style>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		<%-- 모달창을 끄면 새로고침--%>
		$("button.btn-close").click(function(){
			javascript:history.go(0);
		}); 
	});
	
	window.onload = ()=> {
		
		<%-- Header Home --%>
		$("label#navTitle").bind('click',()=>{
			location.href="<%=ctxPath%>/index.wine";
		});
		
		<%-- Footer Home --%>
		$("label#btnHome").bind('click',()=>{
			location.href="<%=ctxPath%>/index.wine";
		})
		
		<%-- New Product Click --%>
		$('div#newItem1').bind("click",function(){
			location.href= "<%=ctxPath%>/shop/product.wine";
		});
		
		<%-- Header Shop Click --%>
		$("label#btnShop").bind('click',()=>{
			location.href="<%=ctxPath%>/shop/list.wine";
		});
		
	}
	
	window.closeModal = function() {
	    $('#loginModal').modal('hide');
	    location.href="<%=ctxPath%>/member/memberRegister.wine";
	}
	
	
	
	</script>
    
</head>
<body>
    <nav class="py-2 bg-dark">
        <div class="container d-flex flex-wrap justify-content-center">
            <div class="nav">
                <label id="navTitle" class="text-align-center curpointer" style="color: white;"><img src="<%=ctxPath %>/images/title.png" class="img-fluid mx-auto d-block" style="max-width: 40%;"></label>
            </div>
        </div>
    </nav>
    <header class="py-1 mb-2 d-flex flex-wrap ">
            <div class="p-2 me-2 d-flex me-auto">
                <ul class="nav">
                    <li class="nav-item fw-bold"><label id="btnShop" class="nav-link link-body-emphasis px-2 curpointer">Shop</label></li>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnAbout" class="nav-link link-body-emphasis px-2 curpointer">Event</label></li>
                    <div class="vr m-2"></div>
                    <li class="nav-item fw-bold"><label id="btnAbout" class="nav-link link-body-emphasis px-2 curpointer">About</label></li>
                </ul>
            </div>
            <div class="p-2 me-2 d-flex">
                <ul class="nav">
                    <li class="nav-item fw-bold"><label id="btnSingup" class="nav-link link-body-emphasis px-2 curpointer" data-bs-toggle="modal" data-bs-target="#loginModal">Sign in</label></li>
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
		                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                </div>
		
		                <div class="modal-body p-5 pt-0">
		                    <div id="login">
								<iframe id="iframe_login" style="border: none; width: 100%; height: 420px;" src="<%= ctxPath%>/login/login.wine">
								</iframe>
							</div>
		                </div>
		            </div>
		        </div>
		    </div>
		    <%-- Sign in Modal 끝 --%>
		    
		     <%-- 검색 Modal --%>
             <form>
               <div class="modal fade" id="searchModal" aria-labelledby="exampleModalLabel" aria-hidden="true">
                 <div class="modal-dialog modal-lg">
                   <div class="modal-content">
                     <div class="modal-body">
                     <div class="input-group">
                          <div class="form-outline" data-mdb-input-init style="padding-left: 8.5%;">
                            <input type="search" id="form1" class="form-control" style=" width: 600px; maxlength=20; height: 50px;" placeholder="와인을 검색하세요"/>                             
                          </div> 
                          <button type="button" class="btn btn-primary" style="height: 50px;" data-mdb-ripple-init>
                                  <i class="fas fa-search"></i>
                          </button>
                     </div>
                     </div>
                   </div>
                 </div>
               </div>
            </form>
    	    <%-- 검색 Modal --%>
    </header>