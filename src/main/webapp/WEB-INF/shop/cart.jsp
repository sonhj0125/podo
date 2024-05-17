<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String ctxPath = request.getContextPath();
%>

<script type="text/javascript">
function count(type)  {
     // 결과를 표시할 element
     const resultElement = document.getElementById('result');
     
     // 현재 화면에 표시된 값
     let number = resultElement.innerText;
     
     // 더하기/빼기
     if(type === 'plus') {
        
       number = parseInt(number) + 1;
       
     } else if(type === 'minus')  {
        
       number = parseInt(number) - 1;
       
       if(number < 1) {
          number == 1
          retrun;
       }
     }
     
     // 결과 출력
     resultElement.innerText = number;
   }
</script>

<jsp:include page="../header.jsp" />

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
         <input class="form-check-input" type="checkbox" id="flexCheckDefault">
         <label class="form-check-label" for="flexCheckDefault">
             전체 선택
         </label>
         <hr>
         <div class="hstack gap-3" style="border: solid 3px gray; border-width: 0 0 3px; border-style: solid; padding: 2% 0;">
           <input type="checkbox">
           <img src="<%=ctxPath %>/images/product/1.png" style="width: 100px; height: 100px;">
           <h5 style="font-weight: bold; margin-right: 1%;">디아블로데블 카나발카베르네</h5>
           <div style="margin-right: 23%;">
              <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">레드</span>
              <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">칠레산</span>
              <span class="badge rounded-pill p-2" style="background-color: #b3b3ff;">카베르네 소비뇽</span>
            </div>
            <div style="display: flex; margin-right: 20.999%;">
               <input type='button' onclick='count("minus")' value='-' style="margin-right:15%;"/>
              <div id='result'>1</div>
              <input type='button' onclick='count("plus")' value='+' style="margin-left:15%;"/>
           </div>
            <div style="font-size: 22pt;">14,900원</div>
         </div>
      </div>
      
      <div class="cart_footer" style="text-align: center;">
      <div style="display:center; margin-top: 2.5%;">
         <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/shop/list.wine';">계속쇼핑하기</button>
         <button type="button" class="btn btn-outline-secondary">선택상품 주문</button>
         <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%=ctxPath%>/shop/order.wine';">전체상품 주문</button>
      </div>   
      </div>

   </div>


<jsp:include page="../footer.jsp" />