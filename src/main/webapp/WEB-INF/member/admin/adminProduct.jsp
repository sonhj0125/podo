<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../header.jsp" />    

<style type="text/css">


label.input-group-text, span.input-group-text {
	background-color: #ccd9ff;
	width: 20%;

}
   
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		// ==>> 제품이미지 파일선택을 선택하면 화면에 이미지를 미리 보여주기 시작 <<== //
		$(document).on("change", "input.img_file", function(e){
			
			const input_file = $(e.target).get(0);
			
	        const fileReader = new FileReader();
			
	        fileReader.readAsDataURL(input_file.files[0]); 
	        fileReader.onload = function(){	
	        	
	            document.getElementById("previewImg").src = fileReader.result;	
	        	
	        }; // end of fileReader.onload = function()
			
	        
		}); // end of $(document).on("change", "input.img_file", function(e)
		// ==>> 제품이미지 파일선택을 선택하면 화면에 이미지를 미리 보여주기 끝 <<== //
		
		
		
	}); // end of $(document).ready(function(){})

</script>

	<div align="center" style="margin-bottom: 20px;">

   	<div style="border: solid pink 3px; width: 250px; margin-top: 20px; padding-top: 10px; padding-bottom: 10px; border-left: hidden; border-right: hidden;">       
      	<span style="font-size: 15pt; font-weight: bold;">제품등록&nbsp;[관리자전용]</span>   
   	</div>
   	
   	<br/>
   
   	<%-- !!!!! ==== 중요 ==== !!!!! --%>
   	<%-- 폼에서 파일을 업로드 하려면 반드시 method 는 POST 이어야 하고 
        enctype="multipart/form-data" 으로 지정해주어야 한다.!! --%>
   	<form id="prodInputFrm" enctype="multipart/form-data"> 
        <div id="container" style="width:70%; margin-bottom: 5%;">
			<div class="input-group mb-3">
				<label class="input-group-text" for="pType">제품 분류</label>
				<select class="form-select" id="pType">
					<option selected>선택하세요.</option>
					<option value="레드">레드</option>
					<option value="화이트">화이트</option>
					<option value="로제">로제</option>
					<option value="스파클링">스파클링</option>
				</select>
			</div>
			
			
			<div class="input-group mb-3">
				<label class="input-group-text" for="pHomeTown">원산지</label>
				<select class="form-select" id="pHomeTown" name="phometown">
					<option selected>선택하세요.</option>
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
			  	<input type="text" placeholder="알파박스 앤 다이스 타로 프로세코" aria-label="name" class="form-control" name="pname">
			 	<input type="text" placeholder="ALPHA BOX AND DICE TAROT PROSECCO" aria-label="ename" class="form-control" name="pengname">
			</div>
			
			
			<div class="input-group mb-3">
			  	<span class="input-group-text" id="price">가격</span>
			  	<input type="text" class="form-control" placeholder="25,000원" aria-label="price" aria-describedby="basic-addon1" name="pprice">
			</div>
			
			
			<div class="input-group mb-3">
			  	<span class="input-group-text" id="point">적립금</span>
			  	<input type="text" class="form-control" placeholder="1,250원" aria-label="point" aria-describedby="basic-addon1" name="ppoint">
			</div>
			
		
			<div class="input-group mb-3">
				<label class="input-group-text" for="pBody">바디</label>
				<select class="form-select" id="pBody" name="pbody">
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
				<select class="form-select" id="pAcid" name="pacid">
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
				<select class="form-select" id="pTannin" name="ptannin">
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
				<select class="form-select" id="pAcl" name="pacl">
					<option selected>선택하세요.</option>
					<option value="1">낮음(~11%)</option>
					<option value="2">중간(12~13%)</option>
					<option value="3">높음(14%~)</option>
				</select>
			</div>   	
	      	
	      	<div class="input-group">
			  	<span class="input-group-text">제품 설명</span>
			  	<textarea class="form-control" style="height:150px;" aria-label="With textarea" name="pdetail"></textarea>
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
            	<input class="form-control text-center me-3" id="count" name="cvolume" type="number" value="1" min="1" max="100" style="max-width: 5rem" name="pstock"/>
	        </div>
	        
	      	<input type="reset" value="취소"  style="width: 120px; margin-top:5%; " class="btn btn-secondary btn-lg" /> 
           	<input type="button" value="제품등록" id="btnRegister" style="width: 120px; margin-top:5%; margin-left	:50%;" class="btn btn-primary btn-lg mr-5" /> 
           	  	
		</div>
   </form>

</div>
    
    
<jsp:include page="../../footer.jsp" />    