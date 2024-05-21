<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String ctxPath = request.getContextPath();
%>

<style>

	.checkb{
	
		border: solid 1px red;
	
	}

</style>

<jsp:include page="../header.jsp" />

<script type="text/javascript" src="<%=ctxPath %>/js/shop/cart.js"></script>

   <div id="container">
   
      <div class="cart_header" style="background-color: #F8F8F8; width: 100%; height: 200px; text-align: center;">
         <br><br>
         <h2 style="margin-bottom: 1.5%;">장바구니</h2>
         <div style="text-align: center; margin:0 10%;">
         <div style="display: flex; width: 16.5%; margin:0 auto;">
            
            <div style="margin-right: 5%;">
               <i class="fa-solid fa-cart-shopping"></i>
               <br>
               <div style="font-weight: bold;">장바구니</div>
            </div>
            <div style="margin-right: 5%;">
               <br>
               <i class="fa-solid fa-chevron-right"></i>
            </div>
            <div style="margin-right: 5%;">
               <i class="fa-regular fa-credit-card" style="color: #bcbcbc;"></i>
               <br>
               <div style="color: #bcbcbc; font-weight: bold;">주문결제</div>
            </div>
            <div style="margin-right: 5%;">
               <br>
               <i class="fa-solid fa-chevron-right" style="color: #c0c0c1;"></i>
            </div>
            <div>
               <i class="fa-regular fa-circle-check" style="color: #bcbcbc;"></i>
               <br>
               <div style="color: #bcbcbc; font-weight: bold;">주문완료</div>
            </div>
         
         </div>
         </div>
      </div>
      
      <div class="cart_body">
      
      	
         
         
         <c:if test="${not empty requestScope.cdtoList}">
         
         <div style="width: 80%; margin:0 auto; padding: 0;">
         
         <div class="hstack gap-1" style="margin-bottom: 15px">
	      	 <input id="cbAll" class="form-check-input" type="checkbox" style="margin-right : 1%;">
	         <div style="width: 13%; min-width: 13%;">
		           	전체선택
		     </div>
		           
		           <div style="width: 28%; min-width: 28%; font-weight: bold; text-align: center;">
		           		제품명
		           </div>
		           <div style="width: 12%; min-width: 12%; text-align: center;">
		           		제품종류
		           </div>
		           <div style="width: 10%; min-width: 10%; text-align: center;">
		           		수량
		           </div>
		           <div style="width: 10%; min-width: 10%; text-align: center;">
		           		수량변경
		           </div>
		           <div style="width:10%; min-width: 10%;  text-align: center; color: gray;">
						개당가격
		           </div>
		           <div style="width:10%; min-width: 10%; font-weight: bold; text-align: center;">
						총가격
		           </div>
         </div>
         
         	<c:forEach var="cdtolist" items="${requestScope.cdtoList}">
         		<hr>
	         	<div class="hstack gap-1" style="padding-top: 10px; padding-bottom: 10px">
	         	
		           <input class ="form-check-input cbOne" type="checkbox">
		           
		           <div style="width: 13%; min-width: 13%;">
		           	<img src="<%=ctxPath %>/images/product/${cdtolist.pdto.pimg}" style="width: 100%;">
		           </div>
		           
		           <div class="curpointer" style="width: 28%; min-width: 28%; font-weight: bold; font-size: 1.0vw;" onclick="showProduct('${cdtolist.pdto.pindex}')">
		           		${cdtolist.pdto.pname}
		           </div>
		           
		           <div style="width: 12%; min-width: 12%; text-align: center;">
		              <c:if test="${cdtolist.pdto.ptype == '레드'}">
                      		<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
                      </c:if>
                      <c:if test="${cdtolist.pdto.ptype == '화이트'}">
                      		<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
                      </c:if>
                      <c:if test="${cdtolist.pdto.ptype == '로제'}">
                      		<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
                      </c:if>
                      <c:if test="${cdtolist.pdto.ptype == '스파클링'}">
                      		<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
                      </c:if>
		              <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">${cdtolist.pdto.phometown}</span>
		           </div>
		           
		           <div style="width: 10%; min-width: 10%; text-align: center; font-size: 1.5vw;">
						${cdtolist.cvolume}EA
		           </div>
		           
		           <div style="width: 10%; min-width: 10%; text-align: center;">
		           		<button type="button" class="btn btn-light">변경</button>
		           </div>
		           
		           <div style="width:10%; min-width: 10%; font-size: 1.0vw; font-weight: bold; text-align: right; color: gray;">
		                  ${cdtolist.pdto.pprice}원
		           </div>
		           <div class="priceOne" style="width:10%; min-width: 10%; font-size: 1.0vw; font-weight: bold; text-align: right;">
		                  ${cdtolist.sumprice}원
		           </div>
		         </div>
		         
		         <div class="cart-index" style="font-size: 40;">
		         	${cdtolist.cindex}
		         </div>
		         
         	</c:forEach>
         		<div class="hstack gap-1" style="border-top: solid 2px gray; padding-left: 65%; font-size: 1.5vw; font-weight: bold;">
         			<div style="width:45%; min-width: 45%;">결제 예정금액 : </div>
         			<div id="sumPrice" style="width:54%; min-width: 54%; font-size: 1.5vw; font-weight: bold; text-align: right; padding-right: 7%">
		        	</div>
         		</div>

				         
         <form name="orderSet">
         	<input type="text" id="setCindex" name="Arr_cindex">
         </form>
		
         <form name="orderSetOne">
             <input type="text" id="setCindexOne" name="Arr_cindexOne">
         </form>
		
		</div>

         </c:if>
         
         <c:if test="${empty requestScope.cdtoList}">
         	<div style="width: 100%; text-align: center; font-weight: bold; padding: 70px 0; font-size: 2vw;" >
         		장바구니에 상품이 없습니다.
         	</div>
         </c:if>
         
      </div>
      
      <div class="cart_footer" style="text-align: center;">
	      <div style="display:center; margin-top: 2.5%;">
	      	 <button type="button" class="btn btn-outline-secondary">선택상품 삭제</button>
	         <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/shop/list.wine';">계속쇼핑하기</button>
	         <button type="button" class="btn btn-outline-secondary">선택상품 주문</button>
	         <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/shop/order.wine';">전체상품 주문</button>
	      </div> 
      </div>
   </div>

<jsp:include page="../footer.jsp" />