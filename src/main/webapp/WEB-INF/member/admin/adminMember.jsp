<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../../header.jsp" />

<script type="text/javascript">
   
   window.onload = ()=> {
      
      <%-- 회원클릭시 상세보기 페이지 --%>
      $("tr#memberDetail").bind('click',()=>{
         location.href="<%=ctxPath%>/member/admin/adminMemberDetail.wine";
      });
      
      
   }; // end of window.onload
   
</script>

<style type="text/css">
   
   <%-- 회원목록 테이블 --%>
	form[name="member_search_frm"] {
	   margin: 0 auto 3% auto;
	}
	
	form[name="member_search_frm"] button.btn-danger {
	   margin-left: 2%;
	   margin-right: 32%;
	}
	
	table#memberTbl {
	   text-align: center;
	   font-size: 14pt;
	}
	
	tbody {
		  text-align: center;
		  font-size: 12pt;
	}
	
	table#memberTbl tr.memberInfo:hover {
	   background-color: #e6ffe6;
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
 
 
</style>


<div class="container" style="padding: 3% 0;">
	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;">회원 관리</h2>
	
	<hr>
	
	<form name="member_search_frm">
		<select name="searchType">
			<option value="">검색대상</option>
			<option value="name">회원명</option>
			<option value="userid">아이디</option>
			<option value="email">이메일</option>
		</select>
		&nbsp;
		<input type="text" name="searchWord" />
		
		<input type="text" style="display: none;" />

		<button type="button" class="btn btn-danger">검색</button>

		<span style="font-size: 12pt; font-weight: bold; margin-left: 22.5%;">페이지당 회원명수&nbsp;-&nbsp;</span>
		<select name="sizePerPage">
			<option value="10">10명</option>
			<option value="5">5명</option>
			<option value="3">3명</option>
		</select>
	</form>
	
	<table class="table table-bordered table table-striped table table-hover" id="memberTbl">
		<thead>
			<tr class="table-dark">
				<th>번호</th>
				<th>아이디</th>
				<th>회원명</th>
				<th>이메일</th>
				<th>연락처</th>
				<th>성별</th>
				<th>회원상태</th>
			</tr>
		</thead>

		<tbody>
			<tr id="memberDetail">
				<td>1</td>
				<td>test003</td>
				<td>테스트용</td>
				<td>test003@naver.com</td>
				<td>010-5957-8484</td>
				<td>남</td>
				<td>정상</td>
			</tr>
			<tr id="memberDetail">
				<td>2</td>
				<td>test001</td>
				<td>테스트</td>
				<td>test001@naver.com</td>
				<td>010-6547-2315</td>
				<td>여</td>
				<td>정상</td>
			</tr>
		<%--
			<c:if test="${not empty requestScope.member}">
				<c:forEach var="membervo" items="${requestScope.memberList}" varStatus="status">
					<tr class="memberInfo">
					
						<fmt:parseNumber var="currentShowPageNo" value="${requestScope.currentShowPageNo}" />
						<fmt:parseNumber var="sizePerPage" value="${requestScope.sizePerPage}" />
						<td>${requestScope.totalMemberCount - (currentShowPageNo - 1) * sizePerPage - (status.index)}</td>
						
						<td class="userid">${.userid}</td>
						<td>${.name}</td>
						<td>${.email}</td>
						<td>${.phone}</td>
						<td>
							<c:choose>
								<c:when test="${.gender == '1'}">남</c:when>
								<c:otherwise>여</c:otherwise>
							</c:choose>
						</td>
						<td>${.memberidx}</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty requestScope.memberList}">
				<tr>
					<td colspan="7" style="text-align: center;">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
		--%>
		</tbody>
	</table>
	
	
	<%-- 페이지 이동 --%>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">${requestScope.pageBar}</ul>
	</nav>
	
</div>

<jsp:include page="../../footer.jsp" />