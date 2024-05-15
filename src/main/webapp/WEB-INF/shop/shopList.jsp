<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

<style>

<%--  체크박스 색상 변경 --%>
input[type="checkbox"] { 
	border:solid 2px pink;
	accent-color: #9684e6; width:20px; height:20px;
	margin-right: 5%;
	cursor: pointer;
} 


<%-- 페이지 이동 --%>
.page-link {
  color: #000; 
  background-color: #fff;
  border: 1px solid #ccc; 
}

.page-item.active .page-link {
 z-index: 1;
 color: #555;
 font-weight:bold;
 background-color: #f1f1f1;
 border-color: #ccc;
 
}

.page-link:focus, .page-link:hover {
  color: #000;
  background-color: #fafafa; 
  border-color: #ccc;
}

<%-- 슬라이더 --%>
#slider {
	accent-color:pink;
	width:100%;
	cursor: pointer;
}
        
datalist {
	display: grid;
	grid-auto-flow: column;
	width:100%;
	grid-column-gap: 5%;
	
}

datalist > option {
	font-size:9pt;
	width:100%;
	border:solid 0px red;
}

</style>



<div class="container">
	<%-- 상단 --%>
	<div class="mt-4">
		<h1 style="font-weight:bold;margin-bottom:2%; text-align:center;">WINE</h1>
	</div>
	<hr>
	<div class="hstack gap-3 mt-3">
		<form>
			<div class="p-2">
				<div class="form">
				  <select class="form-select border border-black">
				    <option selected>Latest</option>
				    <option>Popular</option>
				    <option>High price</option>
				    <option>Low price</option>
				  </select>
				</div>
			</div>
		</form>
		<div class="p-2 ms-auto"></div>
			<div class="p-2">
				<button class="btn btn-outline-secondary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
					<img src="../images/setting.png" width="20" height="20" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16"/>
					<span style="font-size:10pt; font-weight:bold; color:black;">SMART SEARCH</span>
				</button>
			</div>
		</div>

	<%-- 본페이지 --%>
	<div class="row row-cols-1 row-cols-md-4 g-4 mb-5 mt-3">
	  	<div class="col">
	    	<div class="card h-100">
	    	<%-- product image --%>
	      	<img src="../images/product/1.png" class="card-img-top" alt="...">
	      	<%-- sale badge --%>
		    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
		    <%-- product explain --%>
		    <div class="card-body">
		    	<h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
		        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
		        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
		        <div class="mb-3 text-center">
              		<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
              		<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            	</div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      	</div>
	    	</div>
	  </div>
	  
	  <div class="col">
    	 <div class="card h-100">
	    	<!-- product image -->
	      	<img src="../images/product/2.png" class="card-img-top" alt="...">
	      	<!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      	<!-- product explain -->
	      	<div class="card-body">
		        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
		        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
		        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
		        <div class="mb-3 text-center">
	              	<span class="badge rounded-pill p-2" style="background-color: #ffb366;">화이트</span>
	              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
	            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	      </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/3.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #ff8080;">로제</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/4.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/5.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #ff3333;">레드</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/6.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/7.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/8.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col">
	    <div class="card h-100">
	      <img src="../images/product/9.png" class="card-img-top" alt="...">
	      <!-- sale badge -->
	      	<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
	      <div class="card-body">
	        <h5 class="card-title text-center" style="font-weight: bold;">맥스 에이트 (8, VIII)</h5>
	        <p class="card-text text-center" style="font-weight: bold;">Max Eight (8, VIII)</p>
	        <p class="card-text">80명이 넘는 오너가 소유하고있는 포도밭으로 부르고뉴에서 가장 비싼 포도밭 중 하나</p>
	        <div class="mb-3 text-center">
              	<span class="badge rounded-pill p-2" style="background-color: #66c2ff;">스파클링</span>
              	<span class="badge rounded-pill p-2" style="background-color: #9999ff;">칠레</span>
            </div>
            	<p class="card-text text-center" style="font-size: 16pt; font-weight: bold;">39,900원</p>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	
	
	
	<%-- search 버튼 클릭 시 미니 창 --%>
	<div class="offcanvas offcanvas-end bg-dark text-light" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
	  <div class="offcanvas-header">
	    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	  </div>
	  <div class="offcanvas-body">
	  	<h1 style="text-align: center; font-weight:bold; padding: 10%;">Search</h1>
	   
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷와인 종류</p>
			    <hr>
			   
		    	<div>
				    <div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="red" value="" >
					  	<label class="form-check-label" for="red">레드(Red)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="rose" value="">
					  	<label class="form-check-label" for="rose">로제(Rose)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="white" value="">
					  	<label class="form-check-label" for="white">화이트(White)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="sparkling" value="">
					  	<label class="form-check-label" for="sparkling">스파클링(Sparkling)</label>
					</div>
				</div>
		    </div>
		    
		    <br>
		    
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷가격</p>
			    <hr>
			    <div>
				    <div class="form-check">
					  <input class="form-check-input" type="checkbox" id="1" value="">
					  <label class="form-check-label" for="1">
					    ~ 10,000원
					  </label>
					</div>
					<br>
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" id="2" value="">
					  <label class="form-check-label" for="2">
					    10,000원 ~ 50,000원
					  </label>
					</div>
					<br>
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" id="3" value="">
					  <label class="form-check-label" for="3">
					    60,000원 ~ 150,000원
					  </label>
					</div>
					<br>
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" id="4" value="">
					  <label class="form-check-label" for="4">
					    160,000원 ~ 300,000원
					  </label>
					</div>
					<br>
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" id="5" value="">
					  <label class="form-check-label" for="5">
					    300,000원 ~
					  </label>
					</div>
				</div>
		    </div>
		    
		    <br>
		    
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷원산지</p>
			    <hr>
			    
			    <div>
				    <div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="Chile" value="">
					  	<label class="form-check-label" for="Chile">칠레(Chile)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="USA" value="">
					  	<label class="form-check-label" for="USA">미국(USA)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="Italy" value="">
					  	<label class="form-check-label" for="Italy">이탈리아(Italy)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="France" value="">
					  	<label class="form-check-label" for="France">프랑스(France)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="Spain" value="">
					  	<label class="form-check-label" for="Spain">스페인(Spain)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="Australia" value="">
					  	<label class="form-check-label" for="Australia">호주(Australia)</label>
					</div>
					<br>
					<div class="form-check">
					  	<input class="form-check-input" type="checkbox" id="NewZealand" value="">
					  	<label class="form-check-label" for="NewZealand">뉴질랜드(NewZealand)</label>
					</div>
				</div>
		    </div>
		    
		    <br>
		    
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷바디</p>
			    <hr>
			    <input id="slider" type="range" min="1" max="5" step="1" list="body">
				    <datalist id="tickmarks">
				        <option value="1">가벼움</option>
				        <option value="2">약간가벼움</option>
				        <option value="3">중간</option>
				        <option value="4">약간무거움</option>
				        <option value="5">무거움</option>
				    </datalist>
		    </div>
		    
		    <br>
		    
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷산도</p>
			    <hr>
			    <input id="slider" type="range" min="1" max="5" step="1" list="acid">
				    <datalist id="tickmarks">
				        <option value="1">낮음</option>
				        <option value="2">약간낮음</option>
				        <option value="3">중간</option>
				        <option value="4">약간높음</option>
				        <option value="5">높음</option>
				    </datalist>
		    </div>
		    
		    <br>
		    
		    <div class="mt-5">
			    <p style="font-weight:bold; font-size:14pt;">🍷타닌</p>
			    <hr>
			    <input id="slider" type="range" min="1" max="5" step="1" list="tanin">
				    <datalist id="tickmarks">
				        <option value="1">약함</option>
				        <option value="2">약간약함</option>
				        <option value="3">중간</option>
				        <option value="4">약간강함</option>
				        <option value="5">강함</option>
				    </datalist>
		    </div>
	  </div>
	  
	  	<%-- search 제출 버튼 --%>
		<button type="button" class="btn btn-danger">Search</button>
	  
	</div>


	


	<%-- 페이지 이동 --%>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    <li class="page-item">
	      <a class="page-link">◀</a>
	    </li>
	    <li class="page-item"><a class="page-link">1</a></li>
	    <li class="page-item"><a class="page-link">2</a></li>
	    <li class="page-item"><a class="page-link">3</a></li>
	    <li class="page-item">
	      <a class="page-link">▶</a>
	    </li>
	  </ul>
	</nav>
	


<jsp:include page="../footer.jsp" />