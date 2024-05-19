<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

<form>
   <div id="container" style="width: 100%;">
      
      <div style="width: 60%;">
      
      <div class="orderDetail_1">
      	 <h2 style="text-align: center;">주문상세</h2>
         <hr>
         <div class="hstack gap-3" style="padding: 2% 0;">
           <img src="<%=ctxPath %>/images/product/1.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
           <div style="display: flex; justify-content: space-between; width: 90%;">
	           <div>
	           		<div style="margin-bottom: 1%;">디아블로데블 카나발카베르네</div>
	           		<div>
		           		<span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">레드</span>
		                <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">칠레산</span>
		                <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">카베르네 소비뇽</span>
	           		</div>
	           </div>
	
	           <div style="display:flex;">
		            <div style="font-size: 13pt; font-weight: bold;">14,900원</div>
		            <div class="mx-2">|</div>
		            <div style="font-size: 13pt;">1EA</div>
	           </div>
           </div>
         </div>
         
         <div class="hstack gap-3" style="padding: 2% 0;">
           <img src="<%=ctxPath %>/images/product/2.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
           <div style="display: flex; justify-content: space-between; width: 90%;">
	           <div>
	           		<div style="margin-bottom: 1%;">알파박스 앤 다이스 타로 프로세코</div>
	           		<div>
		           		<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
		                <span class="badge rounded-pill p-2" style="background-color: #9999ff;">호주</span>
	           		</div>
	           </div>
	
	           <div style="display:flex;">
		            <div style="font-size: 13pt; font-weight: bold;">25,000원</div>
		            <div class="mx-2">|</div>
		            <div style="font-size: 13pt;">1EA</div>
	           </div>
           </div>
         </div>         
         <hr>
         <br><br><br>
      </div>
      
      <div class="cart_body_2" style="width: 100%;">
      
         <div>
            <h3>배송정보</h3>
            
            <hr>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">수령인</label>
               <div class="col-sm-7">
                  <div>강민정</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">연락처</label>
               <div class="col-sm-7">
                  <div>010-7777-7777</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">주소</label>
               <div class="col-sm-7">
                  <div>서울특별시 마포구 월드컵북로 21 풍성빌딩 2층</div>
               </div>
            </div>
            <div class="form-group row my-4" style="margin-bottom: 1.8%;">
               <label class="col-2" style="width: 15.5%; font-weight: bold;">배송메시지</label>
 				<div class="col-sm-7">
                  <div>빠른 배송 부탁드립니다.</div>
               </div>
            </div>
            <br><br><br>
         </div>
         
         <div style="border:solid 0px red;">
            
            <h3>결제 내역</h3>
            <hr>
            <div style="font-size: 11pt; font-weight: bold; margin-top: 3%;">총 상품가격  39,900원</div>
            <br>
            <br>
            <h4>결제정보</h4>
            <div style="border:solid 1px black; height: 250px; margin-top: 3%;">
               <br>
               <div style="width: 90%; display:flex; justify-content: space-between; margin: 0 auto;">
                  <div>총 구매금액</div>
                  <div style="font-weight: bold;">39,900원</div>
               </div>
               <br>
               <div style="width: 90%; display:flex; justify-content: space-between; margin: 0 auto;">
                  <div>쿠폰할인</div>
                  <div style="font-weight: bold;">-2,000원</div>
               </div>
               <br>
               <div style="width: 90%; display:flex; justify-content: space-between; margin: 0 auto;">
                  <div>배송비</div>
                  <div style="font-weight: bold;">+3,000원</div>
               </div>
               <br>
               <hr style="width: 90%; margin: 0 auto;">               
               <br>
               <div style="width: 90%; display:flex; justify-content: space-between; margin: 0 auto;">
                  <div style="margin-top: 1%;">최종결제금액</div>
                  <div style="font-weight: bold; font-size: 20pt;">40,900원</div>
               </div>
            </div>
         </div>
         
      </div>


     </div>
    </div>
    
</form>
<jsp:include page="../../footer.jsp" />