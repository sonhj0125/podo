<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctxPath = request.getContextPath();
%>


<jsp:include page="../../header.jsp" /> 

<div class="container" style="padding: 3% 0;">
	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;">주문 관리</h2>
	
	<hr>
	
	<form name="member_search_frm">
		<select name="searchType">
			<option>주문상태</option>
			<option value="1">주문접수</option>
			<option value="2">제품준비</option>
			<option value="3">배송중</option>
			<option value="4">배송완료</option>
		</select>
		&nbsp;
		<label> 회원 아이디 </label>
		<input type="text" name="searchWord" />
		<button type="button" class="btn btn-danger" onclick="doSearch()">검색</button>
	</form>
	&nbsp;
	<table class="table table-bordered table table-striped table table-hover" id="memberTbl">
		<thead>
			<tr class="table-dark">
				<th>주문번호</th>
				<th>제품명</th>
				<th>제품 갯수</th>
				<th>주문 가격</th>
				<th>주문 일자</th>
				<th>배송 도착일</th>
				<th>주문정보열람</th>
				<th>주문상태변경</th>
			</tr>
		</thead>

		<tbody id="memberContents">
			
			<c:if test="${not empty requestScope.odtoList}">
				<c:forEach var="odto" items="${requestScope.odtoList}" varStatus="status">
					<tr class="memberInfo">
						<td>${odto.oindex}</td> 
						<td>${odto.pdto.pname}</td>
						<td>${odto.ovolume}</td>
						<td>${odto.ototalprice}</td>
						<td>${odto.odate}</td>
						<td>${odto.oardate }</td>
						<td>버튼</td>
						<td>변경인풋</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty requestScope.odtoList}">
				<tr>
					<td colspan="8" style="text-align: center;">데이터가 존재하지 않습니다.</td>
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