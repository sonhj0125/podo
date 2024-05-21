<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" /> 

<!-- Main css -->
<link rel="stylesheet" href="<%=ctxPath %>/css/member/myPage/memberEdit.css" type="text/css">

<!-- 우편번호찾기 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- Jquery JS -->
<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>

<!-- Main JS-->
<script src="<%=ctxPath %>/js/member/memberEdit.js"></script>


<!-- DataPicker -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<style>

    div#divRegisterFrm {
        font-family: "Noto Sans KR", serif;
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
    
    .month{
    	padding: 0 10px;
    }
    
    .yearselect{
    	display : inline-block;
    	float: left;
    	border : none;
    	max-width: 65px;
    	min-width: 65px;
    }
    
    .monthselect{
    	display : inline-block;
    	float: right;
    	border : none;
    	max-width : 65px;
    	min-width : 65px;
    }
    
    .weekend{
    	color : red;
    }
    
</style>
     
 <div class="wrapper wrapper--w680" style="margin-bottom: 80px">
    <div class="card card-4">
        <div class="card-body">
        	<div style="text-align: center">
            	<h2 class="title">내 정보 수정</h2>
            </div>
            <form name="Registerfrm">
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이름<span class="star">*</span></label>
                            <input class="input--style-4 requiredInfo" type="text" id="name" name="name" value="${sessionScope.loginUser.name}">
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">비밀번호</label>
                            <input class="input--style-4" type="password" id="pwd" name="pwd">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">비밀번호 확인</label>
                            <input class="input--style-4" type="password" id="pwdCheck">
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이메일<span class="star">*</span></label>
                            <input class="input--style-4 requiredInfo" type="email" name="email" id ="email" value="${sessionScope.loginUser.email}">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">연락처</label>
                            <input class="input--style-4" type="text" name="phone" id="phone" value="${sessionScope.loginUser.phone}">
                        </div>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">주소</label>
                        <input class="input--style-4" type="text" name="address" id="address" value="${sessionScope.loginUser.address}" readonly>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">상세주소</label>
                        <input class="input--style-4" type="text" name="addressDetail" id="addressDetail" value="${sessionScope.loginUser.addressDetail}">
                    </div>
                </div> 

			    <div class="w-100" style="display: flex; justify-content: space-between;">
				   	<button class="mt-2 btn btn-lg  btn-secondary" type="button" id="btn-close" style="width: 45%;">취소</button>
				    <button class="mt-2 btn btn-lg  btn-secondary" type="button" id="btnSubmit" style="width: 45%;" onclick="goEdit()">변경하기</button>
			    </div>
            </form>
        </div>
    </div>
    
</div>

<div class="toast-container position-fixed end-0 p-5 top-0 start-50 translate-middle-x">
	<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
		<div class="toast-header text-bg-warning">
			<strong class="me-auto">알림</strong>
   			<small>내정보 수정</small>
		</div>
		<div class="toast-body fw-bold" id="toast-msg">
		</div>
	</div>
</div>



<jsp:include page="/WEB-INF/footer.jsp" /> 