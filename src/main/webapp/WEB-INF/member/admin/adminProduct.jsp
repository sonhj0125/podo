<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../header.jsp" />    

<style type="text/css">

   	table#tblProdInput {border: solid black 0px; 
                       border-collapse: collapse; }
                       
    table#tblProdInput td {border: solid black 1px; 
                          padding-left: 10px;
                          height: 50px;
                          color: white; }
                          
    .prodInputName {background-color: #ffbf80; 
                    font-weight: bold; }                                                 
   
   	.error {color: red; font-weight: bold; font-size: 9pt;}
   
   	div.fileDrop{ display: inline-block; 
                  width: 100%; 
                  height: 100px;
                  overflow: auto;
                  background-color: #fff;
                  padding-left: 10px;}
                 
    div.fileDrop > div.fileList > span.delete{display:inline-block; width: 20px; border: solid 1px gray; text-align: center;} 
    div.fileDrop > div.fileList > span.delete:hover{background-color: #000; color: #fff; cursor: pointer;}
    div.fileDrop > div.fileList > span.fileName{padding-left: 10px;}
    div.fileDrop > div.fileList > span.fileSize{padding-right: 20px; float:right;} 
    span.clear{clear: both;} 
   
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("span.error").hide();
		
		
		// "제품수량" 에 스피너 달아주기
		$("input#spinnerPqty").spinner({
			
		   spin:function(event,ui){
			   
		      if(ui.value > 100) {	
		         $(this).spinner("value", 100);
		         return false;
		      }
		      else if(ui.value < 1) {
		         $(this).spinner("value", 1);
		         return false;
		      }
		   } // end of spin:function(event,ui) 
		
		});// end of $("input#spinnerPqty").spinner()-------
		
		
		
		
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
		
		
		
		
		
		
		<%-- === jQuery 사용하여 드래그앤드롭(DragAndDrop)을 통한 파일 업로드 시작 === --%>
		let file_arr = [];
		
		// == 파일 Drag & Drop 만들기 == //
		$("div#fileDrop").on("dragenter", function(){
			
			e.preventDefault();
     
           
           	e.stopPropagation();
          
		}).on("dragover", function(e){ 
			e.preventDefault();
	        e.stopPropagation();
	        
	        $(this).css("background-color", "#ffd8d8");
	        
	    }).on("dragleave", function(e){ 
	    	e.preventDefault();
	        e.stopPropagation();
	        
	        $(this).css("background-color", "#fff");
	        
		}).on("drop", function(e){   	
			
	    	e.preventDefault();
		
	    });
		
		<%-- === jQuery 를 사용하여 드래그앤드롭(DragAndDrop)을 통한 파일 업로드 끝 === --%>
		
		
		
	}); // end of $(document).ready(function(){})

</script>




	<div align="center" style="margin-bottom: 20px;">

   	<div style="border: solid purple 2px; width: 250px; margin-top: 20px; padding-top: 10px; padding-bottom: 10px; border-left: hidden; border-right: hidden;">       
      	<span style="font-size: 15pt; font-weight: bold;">제품등록&nbsp;[관리자전용]</span>   
   	</div>
   	
   	<br/>
   
   	<%-- !!!!! ==== 중요 ==== !!!!! --%>
   	<%-- 폼에서 파일을 업로드 하려면 반드시 method 는 POST 이어야 하고 
        enctype="multipart/form-data" 으로 지정해주어야 한다.!! --%>
   	<form name="prodInputFrm" enctype="multipart/form-data"> 
         
      	<table id="tblProdInput" style="width: 80%;">
      	<tbody>
         	<tr>
            	<td width="25%" class="prodInputName" style="padding-top: 10px;">카테고리</td>
            	<td width="75%" align="left" style="padding-top: 10px;" >
               	<select name="fk_cnum" class="infoData">
                  	<option value="">선택하세요</option>
                  	
                  	<c:forEach var="cvo" items="${requestScope.categoryList}">
                  		<option value="${cvo.cnum}">${cvo.cname}</option>
                  	</c:forEach>
                  	
               	</select>
               	<span class="error">필수입력</span>
            	</td>   
         	</tr>
         <tr>
           	<td width="25%" class="prodInputName">제품명</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;" >
               	<input type="text" style="width: 300px;" name="pname" class="box infoData" />
               	<span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제조사</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input type="text" style="width: 300px;" name="pcompany" class="box infoData" />
               <span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품이미지</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input type="file" name="pimage1" class="infoData img_file" accept='image/*' /><span class="error">필수입력</span>
               <input type="file" name="pimage2" class="infoData img_file" accept='image/*' /><span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품설명서 파일첨부(선택)</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input type="file" name="prdmanualFile" />
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품수량</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input id="spinnerPqty" name="pqty" value="1" style="width: 30px; height: 20px;"> 개
               <span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품정가</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input type="text" style="width: 100px;" name="price" class="box infoData" /> 원
               <span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품판매가</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <input type="text" style="width: 100px;" name="saleprice" class="box infoData" /> 원
               <span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품스펙</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <select name="fk_snum" class="infoData">
                  <option value="">:::선택하세요:::</option>
                  <%-- 
                     <option value="1">HIT</option>
                     <option value="2">NEW</option>
                     <option value="3">BEST</option> 
                  --%>
                  
                  <c:forEach var="svo" items="${requestScope.specList}">
                  		<option value="${svo.snum}">${svo.sname}</option>
                  </c:forEach>
                     
               </select>
               <span class="error">필수입력</span>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName">제품설명</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden;">
               <textarea name="pcontent" rows="5" cols="60"></textarea>
            </td>
         </tr>
         <tr>
            <td width="25%" class="prodInputName" style="padding-bottom: 10px;">제품포인트</td>
            <td width="75%" align="left" style="border-top: hidden; border-bottom: hidden; padding-bottom: 10px;">
               <input type="text" style="width: 100px;" name="point" class="box infoData" /> POINT
               <span class="error">필수입력</span>
            </td>
         </tr>
         
         <%-- ==== 추가이미지파일을 마우스 드래그앤드롭(DragAndDrop)으로 추가하기 ==== --%>
          <tr>
                <td width="25%" class="prodInputName" style="padding-bottom: 10px;">추가이미지파일(선택)</td>
                <td>
                   <span style="font-size: 10pt;">파일을 1개씩 마우스로 끌어 오세요</span>
                <div id="fileDrop" class="fileDrop border border-secondary"></div>
                </td>
          </tr>
          
          <%-- ==== 이미지파일 미리보여주기 ==== --%>
          <tr>
                <td width="25%" class="prodInputName" style="padding-bottom: 10px;">이미지파일 미리보기</td>
                <td>
                   <img id="previewImg" width="300" />
                </td>
          </tr>
         
         <tr style="height: 70px;">
            <td colspan="2" align="center" style="border-left: hidden; border-bottom: hidden; border-right: hidden; padding: 50px 0;">
                <input type="button" value="제품등록" id="btnRegister" style="width: 120px;" class="btn btn-info btn-lg mr-5" /> 
                <input type="reset" value="취소"  style="width: 120px;" class="btn btn-danger btn-lg" />   
            </td>
         </tr>
      </tbody>
      </table>
      
   </form>

</div>
    
    
<jsp:include page="../../footer.jsp" />      