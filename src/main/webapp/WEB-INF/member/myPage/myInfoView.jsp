<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" /> 

<!-- Main css -->
<link rel="stylesheet" href="<%=ctxPath %>/css/member/memberRegister.css" type="text/css">

<!-- Jquery JS -->
<script src="<%=ctxPath %>/js/jquery-3.7.1.min.js"></script>

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
            <h2 class="title" style="text-align: center; font-weight: bold;">${sessionScope.loginUser.name}[${sessionScope.loginUser.userid}]님의 회원정보</h2>

                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">아이디</label>
                            <input class="input--style-4" type="text" id="userid" name="userid" value="${sessionScope.loginUser.userid}"disabled>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이름</label>
                            <input class="input--style-4" type="text" id="name" name="name" value="${sessionScope.loginUser.name}" disabled>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">생년월일</label>
                            <div class="input-group-icon">
                                <input class="input--style-4 datepicker" type="text" name="birthday" id="birthday" value="${sessionScope.loginUser.birthday}" disabled>
                                <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">성별</label>
                            <div class="pt-3">
                            <c:if test="${sessionScope.loginUser.gender==1}">
                                <label class="radio-container m-r-45">남자
                                    <input type="radio" checked="checked" name="gender" value="1" onclick="return false">
                                    <span class="checkmark"></span>
                                </label>
                                <label class="radio-container" style="margin-left: 5%;">여자
                                    <input type="radio" name="gender" value="2" onclick="return false">
                                    <span class="checkmark"></span>
                                </label>
                            </c:if>
                            <c:if test="${sessionScope.loginUser.gender==2}">
                                <label class="radio-container m-r-45">남자
                                    <input type="radio" name="gender" value="1" onclick="return false">
                                    <span class="checkmark"></span>
                                </label>
                                <label class="radio-container" style="margin-left: 5%;">여자
                                    <input type="radio" checked="checked" name="gender" value="2" onclick="return false">
                                    <span class="checkmark"></span>
                                </label>
                            </c:if>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이메일</label>
                            <input class="input--style-4" type="email" name="email" id ="email" value="${sessionScope.loginUser.email}" disabled>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">연락처</label>
                            <input class="input--style-4" type="text" name="phone" id="phone" value="${sessionScope.loginUser.phone}" disabled>
                        </div>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">주소</label>
                        <input class="input--style-4" type="text" name="address" id="address" readonly value="${sessionScope.loginUser.address}" disabled>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">상세주소</label>
                        <input class="input--style-4" type="text" name="addressDetail" id="addressDetail" value="${sessionScope.loginUser.addressDetail}" disabled> 
                    </div>
                </div> 
        </div>
    </div>
    
</div>

<div class="toast-container position-fixed end-0 p-5 top-0 start-50 translate-middle-x">
	<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
		<div class="toast-header text-bg-warning">
			<strong class="me-auto">알림</strong>
   			<small>회원가입</small>
		</div>
		<div class="toast-body fw-bold" id="toast-msg">
		</div>
	</div>
</div>



<jsp:include page="/WEB-INF/footer.jsp" /> 