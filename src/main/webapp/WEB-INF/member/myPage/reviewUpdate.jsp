<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

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

<script type="text/javascript">
$(document).ready(function() {
	
	<%-- 별점 표시 --%>
	const rstar = $("input#getRstar").val();
	$("input#star" + rstar).prop("checked", true);
	
	<%-- 작성 취소 버튼 클릭 시 입력값 초기화 --%>
	$("button.btn-secondary").click(function() {
		
		$("input[name='rstar']").each(function(index, item) {
			$(item).prop("checked", false);
		});
		
		$("textarea").val("");
		
	});
	
});


function reviewUpdate() {

	const rstar_len = $("input[name='rstar']:checked").length;
	
	if(rstar_len == 0) {
		alert("평가 점수를 선택해주세요!");
        return false;
	}
	
	const rdetail = $("textarea").val().trim();
	
	if(rdetail.length == 0) {
		alert("후기를 입력해주세요!");
		return false;
		
	} else if(rdetail.length < 5) {
		alert("후기를 5글자 이상 입력해주세요!");
		return false;
	}
	
	const frm = document.reviewUpdateFrm;
	frm.method = "post";
	frm.submit();
}
</script>

<form name="reviewUpdateFrm">
   <div id="container" style="width: 100%;">
      
      <div style="width: 60%; text-align: center;">
      	 <h2>리뷰 수정</h2>
      	 <c:if test="${not empty requestScope.rdto}">
	      	 <div>
	            <hr>
	             <div style="display: flex; border: solid 0px red; margin: 6% 2%;">
		             <div style="width: 50%;">
		               <div style="display: flex; justify-content: space-between; padding: 0 2%;">
		                   <div style="display: flex; width: 100%; justify-content: space-between;">
		                      <img src="<%=ctxPath%>/images/product/${rdto.odto.pdto.pimg}" style="border: solid 1px black; border-radius: 15px; width: 120px;">
		                      <div style="margin: auto 1%; text-align: right;">
		                            <div class="mb-2" style="font-size: 14pt;">${rdto.odto.pdto.pname}</div>
		                            <div>${rdto.odto.pdto.pprice}원</div>
		                            
		                      </div>
		                   </div>
		               </div>
		            
		             </div>
		               <br>
		               <div class="pt-4" style="width: 50%; border-left: solid 1px black; margin: 0 2%;">
		                  <h5>와인을 평가해주세요</h5>
		                  <div class="star-rating" style="margin:0 auto; width:100%; justify-content:center;">
		                   <input type="radio" name="rstar" class="star" value="1" id="star1" style="text-align: center;">
		                   <input type="radio" name="rstar" class="star" value="2" id="star2" style="text-align: center;">
		                   <input type="radio" name="rstar" class="star" value="3" id="star3" style="text-align: center;">
		                   <input type="radio" name="rstar" class="star" value="4" id="star4" style="text-align: center;">
		                   <input type="radio" name="rstar" class="star" value="5" id="star5" style="text-align: center;">
		                  </div>
		               </div>
		               <input type="hidden" id="getRstar" value="${rdto.rstar}">
	             </div>
	                
				<br><br>
				<h5>후기를 남겨주세요</h5>
				<div style="display: flex;">
				<textarea class="form-control h-25" id="exampleFormControlTextarea1" name="rdetail" rows="8" placeholder="만족도에 대한 후기를 5글자 이상 남겨주세요" style="background-color: #f2f2f2;">${rdto.rdetail}</textarea>
				</div>
				<hr>
	      	 </div>
      	 </c:if>
		 
		 <button type="button" class="btn btn-primary" onclick="reviewUpdate()">수정하기</button>
	     <button type="button" class="btn btn-secondary">수정 취소</button>

      </div>
    </div>
    
</form>
<jsp:include page="../../footer.jsp" />