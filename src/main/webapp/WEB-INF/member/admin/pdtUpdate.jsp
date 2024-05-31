<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../header.jsp" />    

<style type="text/css">

label.input-group-text, span.input-group-text {
	background-color: ;
	width: 20%;
}
   
</style>

<script type="text/javascript">

	

	let total_fileSize = 0; // 첨부한 파일의 총량을 누적하는 용도

	$(document).ready(function(){
		
		// DB 값 넣기
		$("select#ptype").val("${requestScope.pdto.ptype}");
		$("select#phometown").val("${requestScope.pdto.phometown}");
		$("input#pname").val("${requestScope.pdto.pname}");
		$("input#pengname").val("${requestScope.pdto.pengname}");
		$("input#pprice").val("${requestScope.pdto.pprice}".split(",").join(""));
		$("input#ppoint").val("${requestScope.pdto.ppoint}");
		$("select#pbody").val("${requestScope.pdto.pbody}");
		$("select#pacid").val("${requestScope.pdto.pacid}");
		$("select#ptannin").val("${requestScope.pdto.ptannin}");
		$("select#pacl").val("${requestScope.pdto.pacl}");
		$("textarea[name='pdetail']").val(`${requestScope.pdto.pdetail}`);
		$("input#pstock").val("${requestScope.pdto.pstock}");
		
        
       // 와인영문이름 유효성검사	
       $("input#pengname").blur( function (e) {
         	 
         	 const pengname = $(e.target).val().trim();
         	 const regExp_name = new RegExp(/^[가-힣]{2,50}$/);
         	 const bool = regExp_name.test(pengname);
         	 
         	 if(bool) {
         		 alert("올바른 이름이 아닙니다! 영문으로만 입력해주세요.");
         		checkPengName = false;
         		 return;
         	 }
         	 else {
         		checkPengName = true;
       	     }
         	 
        });
       
        // 가격 유효성검사
        $("input#pprice").blur( function (e) {
        	 
	       	 const pprice = $(e.target).val().trim();
	       	 const regExp_price = new RegExp(/^[0-9]*$/);
	       	 const bool = regExp_price.test(pprice);
	       	 
	       	 if(!bool) {
	       		 alert("가격은 숫자로만 입력해주세요!");
	       		 return;
	       	 }
    	 
       });
        
       $("input#pprice").bind("change", function (e) {
       	 
    	   $("input#ppoint").val($('input#pprice').val() * (5/100));
       });
       

       // ================ 제품 수정하기 ================
       $("input:button[id='btnUpdate']").click(function() {
	    	  
	    	 const pbody_val = $("select[name='pbody']").val();
	    	 
	    	 if(pbody_val == "선택하세요."){
	    		 alert("바디를 선택하세요!");
	    		 return false;
	    	 }
	    	 
			 const ptype_val = $("select[name='ptype']").val();
	    	 
	    	 if(ptype_val == "선택하세요."){
	    		 alert("와인 제품분류를 선택하세요!");
	    		 return false;
	    	 }
	    	 
	         const pHomeTown_val = $("select[name='phometown']").val();
	    	 
	    	 if(pHomeTown_val == "선택하세요."){
	    		 alert("와인 원산지를 선택하세요!");
	    		 return false;
	    	 }
	    	 
		     const pAcid_val = $("select[name='pacid']").val();
	    	 
	    	 if(pAcid_val == "선택하세요."){
	    		 alert("와인 산도를 선택하세요!");
	    		 return false;
	    	 }
	    	 
	    	 console.log(pAcid_val);
	    	 
			 const pTannin_val = $("select[name='ptannin']").val();
	    	 
	    	 if(pTannin_val == "선택하세요."){
	    		 alert("와인 타닌을 선택하세요!");
	    		 return false;
	    	 }
	    	 
			 const pAcl_val = $("select[name='pacl']").val();
	    	 
	    	 if(pAcl_val == "선택하세요."){
	    		 alert("와인 도수를 선택하세요!");
	    		 return false;
	    	 }
	    	 
	         let is_infoData_OK = true;
	         
	         $(".infoData").each(function(index, elmt) {
	            
	            const val = $(elmt).val().trim();
	            
	            if(val == "") {
	               
	               alert("필수 입력사항입니다!");
	               is_infoData_OK = false;
	               return false;
	            }
	         });

	         
	         if(is_infoData_OK) {

	            var formData = new FormData($("form[name='prodInputFrm']").get(0)); // $("form[name='prodInputFrm']").get(0) : 폼에 작성된 모든 데이터 보내기
	               
	            // 첨부한 파일의 총량이 20MB 초과 시 //   
	            if( total_fileSize > 20*1024*1024 ) {
	            	
	                alert("첨부한 파일의 총합의 크기가 20MB를 초과하여 제품 수정이 불가합니다.");
	                return; // 종료
	            } 
	            ///////////////////////////////////////
	            
	            $.ajax({
	                    url: "${pageContext.request.contextPath}/member/admin/pdtUpdate.wine",
	                    type: "post",
	               	    data : formData,
	                    processData: false,  // 파일 전송 시 설정 ★★★
	                    contentType: false,  // 파일 전송 시 설정 ★★★
	                    dataType:"json",
	                    success:function(json) {
	                       
	                       if(json.result == 1) {
	                          location.href = "${pageContext.request.contextPath}/member/admin/updateProduct.wine?pindex="; // pindex 추가하기 
	                       }
	                   },
	                   error: function(request, status, error) {
	                        alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	                   }
	            });

	         }
	         
	      });//end of 제품 수정하기 ----------------------------------------
		
	}); // end of $(document).ready(function(){})

</script>

	<div align="center" style="margin-bottom: 20px;">

   	<div style="border: solid pink 3px; width: 250px; margin-top: 20px; padding-top: 10px; padding-bottom: 10px; border-left: hidden; border-right: hidden;">       
      	<span style="font-size: 15pt; font-weight: bold;">제품수정&nbsp;[관리자전용]</span>   
   	</div>
   	
   	<br/>
   
   	<%-- !!!!! ==== 중요 ==== !!!!! --%>
   	<%-- 폼에서 파일을 업로드 하려면 반드시 method 는 POST 이어야 하고 
        enctype="multipart/form-data" 으로 지정해주어야 한다.!! --%>
   	<form name="prodInputFrm" enctype="multipart/form-data"> 
        <div id="container" style="width:70%; margin-bottom: 5%;">
        
        	<input type="hidden" value="${requestScope.pdto.pindex}" name="pindex">
        	<input type="hidden" value="${requestScope.pdto.pimg}" name="origin_pimg">
        	<input type="hidden" value="${requestScope.pdImgName}" name="origin_pdimg">
        	
			<div class="input-group mb-3">
				<label class="input-group-text" for="pType">제품 분류</label>
				<select class="form-select infoData" name="ptype" id="ptype">
					<option>선택하세요.</option>
					<option value="레드">레드</option>
					<option value="화이트">화이트</option>
					<option value="로제">로제</option>
					<option value="스파클링">스파클링</option>
				</select>
			</div>
			
			
			<div class="input-group mb-3">
				<label class="input-group-text" for="pHomeTown">원산지</label>
				<select class="form-select infoData" name="phometown" id="phometown">
					<option>선택하세요.</option>
					<option value="칠레">칠레</option>
					<option value="미국">미국</option>
					<option value="이탈리아">이탈리아</option>
					<option value="뉴질랜드">뉴질랜드</option>
					<option value="호주">호주</option>
					<option value="스페인">스페인</option>
					<option value="프랑스">프랑스</option>
				</select>
			</div>
			
			
			<div class="input-group">
			  	<span class="input-group-text">제품명 및 영문명</span>
			  	<input type="text" placeholder="제품의 한글이름을 입력해주세요" aria-label="name" class="form-control infoData" name="pname" id="pname">
			 	<input type="text" placeholder="제픔의 영문이름을 입력해주세요" aria-label="ename" class="form-control infoData" name="pengname" id="pengname">
			</div>
			
			
			<div class="input-group mb-3">
			  	<span class="input-group-text" id="price">가격</span>
			  	<input type="text" class="form-control infoData" aria-label="price" aria-describedby="basic-addon1" name="pprice" id="pprice">
			</div>
			
			
			<div class="input-group mb-3">
			  	<span class="input-group-text" id="point">적립금</span>
			  	<input type="text" class="form-control infoData" aria-label="point" aria-describedby="basic-addon1" name="ppoint" id="ppoint" value="" readonly>
			</div>
			
		
			<div class="input-group mb-3">
				<label class="input-group-text" for="pBody">바디</label>
				<select class="form-select infoData" name="pbody" id="pbody">
					<option selected>선택하세요.</option>
					<option value="1">가벼움</option>
					<option value="2">약간가벼움</option>
					<option value="3">중간</option>
					<option value="4">약간무거움</option>
					<option value="5">무거움</option>
				</select>
			</div>
		      	
		    <div class="input-group mb-3">
				<label class="input-group-text" for="pAcid">산도</label>
				<select class="form-select infoData" name="pacid" id="pacid">
					<option selected>선택하세요.</option>
					<option value="1">낮음</option>
					<option value="2">약간낮음</option>
					<option value="3">중간</option>
					<option value="4">약간높음</option>
					<option value="5">높음</option>
				</select>
			</div>  	
		      	
		    <div class="input-group mb-3">
				<label class="input-group-text" for="pTannin">타닌</label>
				<select class="form-select infoData" name="ptannin" id="ptannin">
					<option selected>선택하세요.</option>
					<option value="1">약함</option>
					<option value="2">약간약함</option>
					<option value="3">중간</option>
					<option value="4">약간강함</option>
					<option value="5">강함</option>
				</select>
			</div> 
			
			<div class="input-group mb-3">
				<label class="input-group-text" for="pAcl">도수(알코올)</label>
				<select class="form-select infoData" name="pacl" id="pacl">
					<option selected>선택하세요.</option>
					<option value="1">낮음(~11%)</option>
					<option value="2">중간(12~13%)</option>
					<option value="3">높음(14%~)</option>
				</select>
			</div>   	
	      	
	      	<div class="input-group">
			  	<span class="input-group-text">제품 설명</span>
			  	<textarea class="form-control infoData" style="height:150px;" aria-label="With textarea" name="pdetail"></textarea>
			</div>
	     
	     	<div class="input-group mb-3">
			  	<label class="input-group-text" for="inputGroupFile01">제품이미지</label>
			  	<input type="file" class="form-control" id="inputGroupFile01" name="pimg">
			</div>
			
			<div class="input-group mb-3">
			  	<label class="input-group-text" for="inputGroupFile01">제품상세이미지</label>
			  	<input type="file" class="form-control" id="inputGroupFile02" name="pdimg">
			</div>
			
			<div class="input-group mb-3">
                <span class="input-group-text">재고량</span>
            	<input class="form-control text-center me-3 infoData" type="number" value="1" min="0" max="99999" style="max-width: 5rem" name="pstock" id="pstock"/>
	        </div>																								
	        
	      	<input type="reset" value="취소"  style="width: 120px; margin-top:5%; " class="btn btn-secondary btn-lg" /> 
           	<input type="button" value="제품수정" id="btnUpdate" style="width: 120px; margin-top:5%; margin-left	:50%;" class="btn btn-primary btn-lg mr-5" /> 
           	  	
		</div>
   </form>

</div>
    
    
<jsp:include page="../../footer.jsp" />