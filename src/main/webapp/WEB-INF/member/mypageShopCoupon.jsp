<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="/WEB-INF/header.jsp" />

<style type="text/css">
div#pageBar {
	border: solid 0px red;
	width: 80%;
	margin: 3% auto 0 auto;
	display: flex;
}

div#pageBar>nav {
	margin: auto;
}

</style>

<script type="text/javascript">
    $(document).ready(function() {
		
    	

    });

    function couponRegistration() {
        const cocode = $("input:text[name='couponCode']").val().trim();

        if (cocode == "") {
            alert("쿠폰번호를 입력해주세요.");
            return;
        } 
            $.ajax({
                url: "${pageContext.request.contextPath}/member/myCouponRegistration.wine",
                type: "POST",
                data: {"cocode":cocode},
                dataType: "json",
                success: function(json) {
                    if (json.isExists === true && json.result === false) {
                        alert("쿠폰이 등록되었습니다..");
                        location.href = "/member/mypageShopCoupon.wine"; // 지금 페이지로 간다.
                    } else {
                        alert("존재하지 않는 쿠폰번호입니다.");
                        return;
                    }
                },
                error: function(request, status, error) {
                    alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
                }
            });
        }
    
</script>

<div class="container" style="padding: 3% 0;">
    <h2 style="font-weight:bold; margin-bottom:2%; text-align:center;">
        <img src="<%=ctxPath%>/images/coupon.png" style="width:35px; vertical-align: text-top;">
        &nbsp;마이페이지
    </h2>
    <hr>

    <!-- 사용자 정보 테이블 -->
    <table class="table" style="width:50;">
        <thead>
            <tr>
                <th scope="col" style="font-size:16pt">
                    <span style="font-weight:bold; font-size:20pt; color:#ff6666;">
                        <img src="<%=ctxPath%>/images/mypageP.png" style="width:35px; vertical-align: text-top;">
                        &nbsp;${sessionScope.loginUser.name}
                    </span>님
                </th>
            </tr>
        </thead>
        <tbody class="table-group-divider" style="text-align:center;">
            <tr class="table-secondary">
                <th scope="row">총 쿠폰 발행 수</th>
                <th>사용 쿠폰 수</th>
                <th>가용 쿠폰 수</th>
            </tr>
            <tr class="table-group-divider">
                <c:if test="${requestScope.myCouponList == null}">
                    <tr>
                        <td>0</td>
                        <td>0</td>
                        <td>0</td>
                    </tr>
                </c:if>

                <c:if test="${requestScope.myCouponList != null}">
                    <tr>
                        <td>${requestScope.totalCoupon}</td>
                        <td>${requestScope.usedCoupon}</td>
                        <td>${requestScope.availableCoupons}</td>
                    </tr>
                </c:if>
            </tr>
        </tbody>
    </table>

    <!-- 쿠폰 등록 폼 -->
    <div class="input-group mb-3" style="width:40%; margin-left:30%; margin-top:5%;">
        <input type="text" name="couponCode" class="form-control" placeholder="쿠폰번호를 입력해주세요." aria-label="Recipient's username" aria-describedby="button-addon2">
        <button class="btn btn-outline-danger" type="button" onclick="couponRegistration()">등록하기</button>
    </div>

    <!-- 쿠폰 목록 -->
    <div class="vr" style="border:solid 1px blue; height:20px;"></div>
    <span style="font-weight:bold; font-size:16pt;">나의 쿠폰</span>
    <div class="vr" style="border:solid 1px blue; height:20px;"></div>

    <c:if test="${empty requestScope.MyCouponpagingList}">
        <hr style="border:solid 1px black; margin-top:5%;">
        <div style="display:flex; margin-top:3%;">
            <div style="margin-right:20%; margin-left:10%;">
                <img src="<%=ctxPath%>/images/salecoupon/sale.png" style="width:80px; vertical-align: middle;">&nbsp;
            </div>
            <div style="font-weight:bold; font-size:20pt; text-align:right; margin:auto 2%">
                할인쿠폰이 없습니다.
            </div>
        </div>
        <hr style="border:solid 1px black; margin-top:3%;">
    </c:if>


    <c:if test="${not empty requestScope.MyCouponpagingList}">
        <c:forEach var="mycodto" items="${requestScope.MyCouponpagingList}" varStatus="status">
            <%-- 쿠폰 번호 계산 --%>
            
            <fmt:parseNumber var="currentShowPageNo" value="${requestScope.currentShowPageNo}" /> 
            <fmt:parseNumber var="sizePerPage" value="${requestScope.sizePerPage}" />
<%-- 
            <td>${(requestScope.totalMyCouponCount) - (currentShowPageNo - 1) * sizePerPage - (status.index)}</td>
--%>
            <%-- 할인쿠폰 --%>
            <c:if test="${mycodto.codto.cotype == 1}">
                <hr style="border:solid 1px black; margin-top:5%;">
                <div class="container">
                    <div class="row">
                        <div class="col-2 d-flex justify-content-end align-items-center">
                            <div>
                                <img src="<%=ctxPath%>/images/salecoupon/sale.png" style="width:80px; vertical-align: middle;">&nbsp;
                            </div>
                        </div>
                        <div class="col-8 d-flex justify-content-center align-items-center">
                            <div>
                                <span style="font-weight:bold; font-size:12pt; margin-bottom: 2%;">
                                    ${mycodto.codto.coname}
                                </span><br>
                                할인금액 : <fmt:formatNumber value="${mycodto.codto.codiscount}" pattern="###,###" /> 원<br>
                                ~ ${mycodto.codto.codate}
                            </div>
                        </div>
                        <div class="col-2 d-flex justify-content-end align-items-center">
                            <div>
                                <%-- 1사용가능 --%>
                                <c:if test="${mycodto.costatus == 1}">
                                    <span style="font-weight:bold; font-size:14pt; color:green;">사용가능</span>
                                </c:if>
                                <%-- 2사용완료 --%>
                                <c:if test="${mycodto.costatus == 2}">
                                    <span style="font-weight:bold; font-size:14pt; color:red;">사용완료</span>
                                </c:if>
                                <%-- 3기간만료 --%>
                                <c:if test="${mycodto.costatus == 3}">
                                    <span style="font-weight:bold; font-size:14pt; color:gray;">기간만료</span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <hr style="border:solid 1px black; margin-top:3%;">
            </c:if>      		
       		
       					<%-- cotype == 2 할인율  --%>
			<c:if test="${mycodto.codto.cotype == 2}">
			    <hr style="border:solid 1px black; margin-top:5%;">
                <div class="container">
                    <div class="row">
                        <div class="col-2 d-flex justify-content-end align-items-center">
			                <div>
			                    <img src="<%=ctxPath%>/images/salecoupon/registersale.png" style="width:80px; vertical-align: middle;">&nbsp;
			                </div>
			            </div>
			            <div class="col-8 d-flex justify-content-center align-items-center">
			                <div>
			                    <span style="font-weight:bold; font-size:12pt; margin-bottom: 2%;" >${mycodto.codto.coname}</span><br>
			                    할인율 : ${mycodto.codto.codiscount} % <br>
			                    ~ ${mycodto.codto.codate}
			                </div>
			            </div>
                        <div class="col-2 d-flex justify-content-end align-items-center">
                            <div>
			                    <%-- 1사용가능  --%>
			                    <c:if test="${mycodto.costatus == 1}">
			                        <span style="font-weight:bold; font-size:14pt; color:green;">사용가능</span>
			                    </c:if>
			                    <%-- 2사용완료 --%>
			                    <c:if test="${mycodto.costatus == 2}">
			                        <span style="font-weight:bold; font-size:14pt; color:red;">사용완료</span>
			                    </c:if>
			                    <%-- 3기간만료 --%>
			                    <c:if test="${mycodto.costatus == 3}">
			                        <span style="font-weight:bold; font-size:14pt; color:gray;">기간만료</span>
			                    </c:if>
			                </div>
			            </div>
			        </div>
			    </div>
			    <hr style="border:solid 1px black; margin-top:3%;">       			
			</c:if>  
       		
        </c:forEach>
    </c:if>
    
	<!-- 페이지바 -->
	<div id="pageBar">
	    <nav>
	        <ul class="pagination">
	            ${requestScope.pageBar}
	        </ul>
	    </nav>
	</div>


</div>
<jsp page="/WEB-INF/footer.jsp" />
                                