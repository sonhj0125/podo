<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" />

<!-- Main css -->
<link rel="stylesheet" href="<%=ctxPath %>/css/member/memberRegister.css" type="text/css">

<!-- 우편번호찾기 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- Main JS-->
<script src="<%=ctxPath %>/js/member/memberRegister.js"></script>

<style>
    div#divRegisterFrm {
        font-family: "Noto Sans KR", serif;
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
    }
</style>
     
 <div class="wrapper wrapper--w680" style="margin-bottom: 80px">
    <div class="card card-4">
        <div class="card-body">
            <h2 class="title">PODO 회원가입</h2>
            <form method="POST" id="Registerfrm">
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">아이디</label>
                            <input class="input--style-4" type="text" name="userid">
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">비밀번호</label>
                            <input class="input--style-4" type="password" name="pwd">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">비밀번호 확인</label>
                            <input class="input--style-4" type="password">
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">생년월일</label>
                            <div class="input-group-icon">
                                <input class="input--style-4 js-datepicker" type="text" name="birthday" id="date-picker">
                                <!-- <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i> -->
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">성별</label>
                            <div class="pt-3">
                                <label class="radio-container m-r-45">남자
                                    <input type="radio" checked="checked" name="gender">
                                    <span class="checkmark"></span>
                                </label>
                                <label class="radio-container" style="margin-left: 5%;">여자
                                    <input type="radio" name="gender">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이메일</label>
                            <input class="input--style-4" type="email" name="email">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">연락처</label>
                            <input class="input--style-4" type="text" name="phone">
                        </div>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">주소</label>
                        <input class="input--style-4" type="text" name="address" id="address" readonly>
                    </div>
                </div>
                
                <div class="row-space">
                    <div class="input-group">
                        <label class="label">상세주소</label>
                        <input class="input--style-4" type="text" name="addressDetail" id="addressDetail">
                    </div>
                </div> 

                <div class="p-t-15">
                    <button type="button" class="btn btn-dark">가입</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/footer.jsp" /> 