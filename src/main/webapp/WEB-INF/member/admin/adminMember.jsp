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
      
	      
		// 검색 시 검색타입, 검색 단어 그대로 유지하도록 하기
	  	if("${requestScope.searchType}" != "" &&
	  	   "${requestScope.searchWord}" != "") { 
	  		
	  		$("select[name='searchType']").val("${requestScope.searchType}");
	  		$("input:text[name='searchWord']").val("${requestScope.searchWord}");
	  		
	  	}
	  	
	  	// 검색 시 페이지 당 회원수 선택한 것 유지하기
	  	if("${requestScope.sizePerPage}" != "") { 
	  		$("select[name='sizePerPage']").val("${requestScope.sizePerPage}");
	  		
	  	}
	     
	     
	  	// searchWord 엔터 시 검색 실행
	  	$("input:text[name='searchWord']").bind("keydown", function(e) {
	  		
	  		if(e.keyCode == 13) {
	  			goSearch();
	  		}
	  		
	  	}); // end of $("input:text[name='searchWord']").bind("keydown", function(e) {})
	      
	  	
	 	
		$("select[name='sizePerPage']").bind("change", function() {
			
			const frm = document.member_search_frm;
			frm.submit();
			
		}); // end of $("select[name='sizePerPage']").bind("change", function() {}) ------------------
		
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	

		<%-- 회원클릭시 상세보기 페이지 --%>
		$("tr#memberDetail").bind('click',()=>{
		   location.href="<%=ctxPath%>/member/admin/adminMemberDetail.wine";
		});
		
      
   }; // end of window.onload
   
	
   	// function declaration
	function goSearch() {
		
		const searchType = $("select[name='searchType']").val();
		
		if(searchType == "") {
			alert("검색대상을 선택하세요!");
			return; // goSearch() 함수 종료
		}
		
		const frm = document.member_search_frm;
		frm.submit();
		
	} // end of function goSearch() ---------------------------
   
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

		<button type="button" class="btn btn-danger" onclick="goSearch()">검색</button>

		<span style="font-size: 12pt; font-weight: bold; margin-left: 22%;">페이지당 회원명수&nbsp;-&nbsp;</span>
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
			
			<c:if test="${not empty requestScope.memberList}">
				<c:forEach var="mdto" items="${requestScope.memberList}" varStatus="status">
					<tr class="memberInfo">
					
						<fmt:parseNumber var="currentShowPageNo" value="${requestScope.currentShowPageNo}" />
						<fmt:parseNumber var="sizePerPage" value="${requestScope.sizePerPage}" />
						<td>${requestScope.totalMemberCount - (currentShowPageNo - 1) * sizePerPage - (status.index)}</td> 
						<td class="userid">${mdto.userid}</td>
						<td>${mdto.name}</td>
						<td>${mdto.email}</td>
						<td>${mdto.phone}</td>
						<td>
							<c:choose>
								<c:when test="${mdto.gender == '1'}">남</c:when>
								<c:otherwise>여</c:otherwise>
							</c:choose>
						</td>
						<td>${mdto.status}</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty requestScope.memberList}">
				<tr>
					<td colspan="7" style="text-align: center;">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
		
		</tbody>
	</table>
	
	
	<%-- 페이지 이동 --%>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center" style="margin-top:7%;">${requestScope.pageBar}</ul>
	</nav>
	
</div>

<jsp:include page="../../footer.jsp" />