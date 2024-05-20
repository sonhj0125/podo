<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<style>
  .my-hr3 {
    border: 0;
    height: 2px;
    opacity: 30%;
  }
</style>

<script>
		function reviewDelete() {
			var result = confirm("리뷰를 정말 삭제하시겠습니까?");

			if (result == true) {
				alert("리뷰를 삭제하였습니다.");
			}
			else {
				
			}
		}
		
</script>


<jsp:include page="../../header.jsp" />    

<form>
	<div id="container" style="width: 100%;">
		<h3 style="width: 100%; text-align: center;">리뷰관리</h3>
		
		<div style="width: 60%;">
			<div>
				<div>2024.04.24</div>
			</div>
			
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
				
					<img src="<%=ctxPath %>/images/product/1.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
						<div style="display: flex; width: 90%; justify-content: space-between;">
						<div style="margin: auto 0%;">
								<div>디아블로데블 카나발카베르네</div>
								<div>14,900원</div>
						</div>
						<div style="margin: auto 0;">
							<button type="button" class="btn btn-secondary" onclick="location.href='<%=ctxPath %>/member/reviewWrite.wine'">리뷰 작성</button>
						</div>
					</div>
				</div>
			</div>
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
				
					<img src="<%=ctxPath %>/images/product/2.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
						<div style="display: flex; width: 90%; justify-content: space-between;">
						<div style="margin: auto 0%;">
								<div>알파박스 앤 다이스 타로 프로세코</div>
								<div>25,000원</div>
						</div>
						<div style="margin: auto 0;">
							<button type="button" class="btn btn-secondary" onclick="location.href='<%=ctxPath %>/member/reviewWrite.wine'">리뷰 작성</button>
						</div>
					</div>
				</div>
			</div>
			<hr class="my-hr3" style="background-color: #000000;">
		</div>
		
		
		<div style="width: 60%;">
			<div>
				<div>2024.03.05</div>
			</div>
			
			<div>
				<hr>
				<div style="display: flex; justify-content: space-between;">
				
					<img src="<%=ctxPath %>/images/product/1.png" style="border: solid 1px black; border-radius: 15px; width: 100px; height: 100px;">
						<div style="display: flex; width: 90%; justify-content: space-between;">
						<div style="margin: auto 0%;">
								<div>닥터 린드만 빈25 스파클링 뀌베</div>
								<div>14,900원</div>
						</div>
						<div style="margin: auto 0;">
							<button type="button" class="btn btn-secondary" onclick="location.href='<%=ctxPath %>/member/reviewUpdate.wine'">리뷰 수정</button>
							<button type="button" class="btn btn-secondary" onclick='reviewDelete()'>리뷰 삭제</button>
						</div>
					</div>
				</div>
			</div>
			<hr class="my-hr3" style="background-color: #000000;">
		</div>

	</div>

</form>

<jsp:include page="../../footer.jsp" />