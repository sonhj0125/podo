<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctxPath = request.getContextPath();
%>


<jsp:include page="../../header.jsp" /> 

<script type="text/javascript" src="<%=ctxPath %>/js/admin/adminorder.js"></script>
<script type="text/javascript">

$(function() {
    
	if("${requestScope.searchType}" != "") { 
		
		$("select[name='searchType']").val("${requestScope.searchType}");
		
	}
	
	if("${requestScope.searchWord}" != "") { 
			
		$("input:text[name='searchWord']").val("${requestScope.searchWord}");
			
	}
	
	$("select[name='searchType']").bind("change", function() {
		
		doSearch('<%=ctxPath%>');
		
	});

});

</script>

<div id="ctxPath" style="display: none"><%=ctxPath %></div>

<div class="container" style="padding: 3% 0;">
	<h2 style="font-weight:bold; margin-bottom:2%; text-align:center;">주문 관리</h2>
	
	<hr>
	
	<form name="member_search_frm">
		<select name="searchType">
		    <option value="0">모두보기</option>
			<option value="1">주문접수</option>
			<option value="2">제품준비</option>
			<option value="3">배송중</option>
			<option value="4">배송완료</option>
		</select>
		&nbsp;
		<label> 회원 아이디 </label>
		<input type="text" name="searchWord" />
		<button type="button" class="btn btn-danger" onclick="doSearch('<%=ctxPath%>')">검색</button>
	</form>
	&nbsp;
	<table class="table table-bordered table table-striped table table-hover" id="memberTbl">
		<thead>
			<tr class="table-dark">
				<th>주문번호</th>
				<th>제품명</th>
				<th>아이디</th>
				<th>개수</th>
				<th>총가격</th>
				<th>주문일</th>
				<th>도착일</th>
			</tr>
		</thead>

		<tbody id="memberContents">
			
			<c:if test="${not empty requestScope.odtoList}">
				<c:forEach var="odto" items="${requestScope.odtoList}" varStatus="status">
					<tr class="memberInfo" data-bs-toggle="modal" data-bs-target="#showDinfo">
						<td>${odto.oindex}</td> 
						<td>${odto.pdto.pname}</td>
						<td>${odto.userid }</td>
						<td>${odto.ovolume}</td>
						<td>${odto.ototalprice}</td>
						<td>${odto.odate}</td>
						<td>${odto.oardate}</td>
						<td id="oindex" style="display: none;">${odto.oindex}</td>
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
	
	<%-- iframe_delivery Modal --%>
      <div class="modal fade" id="showDinfo" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content rounded-4 shadow">
            
                <div class="modal-header p-5 pb-4 border-bottom-0">
                    <h1 class="fw-bold mb-0 fs-2">설정변경</h1>
                    <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                   <iframe id="iframe_delivery" style="border: none; width: 100%; height: 600px;">
                   </iframe>
                
	        </div>
	    </div>
    </div>
    <%-- iframe_delivery Modal 끝 --%>
	
	<%-- 페이지 이동 --%>
	<c:if test="${not empty requestScope.odtoList}">
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center" style="margin-top:7%;">${requestScope.pageBar}</ul>
		</nav>
	</c:if>
	
</div>


<jsp:include page="../../footer.jsp" /> 