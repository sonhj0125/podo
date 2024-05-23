<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String ctxPath = request.getContextPath();
%>

<style>
    .star-rating {
      display: flex;
    }

    .star {
      appearance: none;
      padding: 1px;
    }

    .star::after {
      content: '☆';
      color: purple;
      font-size: 50px;
    }

    .star:hover::after,
    .star:has(~ .star:hover)::after,
    .star:checked::after,
    .star:has(~ .star:checked)::after {
      content: '★';
    }

    .star:hover ~ .star::after {
      content: '☆';
    }
</style>

<jsp:include page="../../header.jsp" />

<form>
   <div id="container" style="width: 100%;">
      
      <div style="width: 60%; text-align: center;">

          <h2>리뷰 작성</h2>
          <div>
            <hr>
             <div style="display: flex; border: solid 0px red; margin: 6% 2%;">
             <div style="width: 50%;">
               <div style="display: flex; justify-content: space-between; padding: 0 2%;">
      				<c:if test="${not empty requestScope.pdto}">
	                 <div style="display: flex; width: 100%; justify-content: space-between;">
	                 
	                    <img src="<%=ctxPath%>/images/product/${pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
	                    <div style="margin: auto 1%; text-align: right;">
	                          <div>${pdto.pname}</div>
	                          <div>${pdto.pprice}원</div>
	                    </div>
	                 </div>
      				</c:if>		
               </div>
            
             </div>
               <br>
               <div style="width: 50%; border-left: solid 1px black; margin: 0 2%;">
                  <h5>와인을 평가해주세요</h5>
                  <div class="star-rating" style="margin:0 auto; width:100%; justify-content:center;">
                   <input type="radio" class="star" value="1" style="text-align: center;">
                   <input type="radio" class="star" value="2" style="text-align: center;">
                   <input type="radio" class="star" value="3" style="text-align: center;">
                   <input type="radio" class="star" value="4" style="text-align: center;">
                   <input type="radio" class="star" value="5" style="text-align: center;">
                  </div>
               </div>
                </div>
                
            <br><br>
            <h5>후기를 남겨주세요</h5>
            <div style="display: flex;">
            <textarea class="form-control h-25" id="exampleFormControlTextarea1" rows="8" placeholder="만족도에 대한 후기를 남겨주세요" style="background-color: #f2f2f2;"></textarea>
            </div>
            <hr>
          </div>
          
        <button type="button" class="btn btn-secondary" style="width: 20%;">등록</button>

      </div>
      
    </div>
    
</form>
<jsp:include page="../../footer.jsp" />