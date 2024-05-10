<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- Bootstrap CSS --%>
<link rel="stylesheet" href="<%=ctxPath %>/bootstrap-5.3.3-dist/css/bootstrap.min.css">

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%=ctxPath %>/bootstrap-5.3.3-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/login/login.js"></script>

<style>
div.login_sub_btn {
    border: solid 0px red;
    display: flex;
    margin: 3% auto;
}

button#idFind,
button#pwdFind {
    width: 48%;
}

button#pwdFind {
    margin-left: 5%;
}
</style>



<form name="loginFrm">
    <div class="form-floating mb-3">
        <input type="text" class="form-control rounded-3" id="loginUserid" name="userid" placeholder="name@example.com">
        <label for="loginUserid">아이디</label>
    </div>
    <div class="form-floating mb-3">
        <input type="password" class="form-control rounded-3" id="loginPwd" name="pwd" placeholder="Password">
        <label for="loginPwd">비밀번호</label>
    </div>
    <button class="w-100 mb-2 btn btn-lg rounded-3 btn-primary" type="button" id="btnSubmit">로그인</button>
    <div class="login_sub_btn">
        <button type="button" class="btn btn-secondary" id="idFind" name="idFind" onclick="goIdFind('<%= ctxPath%>')">아이디 찾기</button>
        <button type="button" class="btn btn-secondary" id="pwdFind" name="pwdFind">비밀번호 찾기</button>
    </div>
    <hr class="my-4">
    <!-- <h2 class="fs-5 fw-bold mb-3">Or use a third-party</h2> -->
    <button class="w-100 py-2 mb-2 btn btn-outline-primary rounded-3" type="button" id="signIn" onclick="doregister()">
        회원가입
    </button>
</form>
