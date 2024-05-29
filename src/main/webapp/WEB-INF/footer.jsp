<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top">
<p class="col-md-4 mb-0 text-body-secondary">&copy; 2024 Gclass Team 4</p>
	<ul class="nav col-md-4 justify-content-end">
		<li class="nav-item">
			<label id="btnHome" class="nav-link px-2 text-body-secondary curpointer">Home</label>
		</li>
		<li class="nav-item"><label id="btnAbout" class="nav-link px-2 text-body-secondary curpointer">About</label>
		</li>
		<li class="nav-item"><label id="btnKakao" class="nav-link px-2 text-body-secondary curpointer" onclick="location.href = 'https://open.kakao.com/o/gwCJMkug';">Kakao</label>
		</li>
	</ul>
</footer>
</body>
</html>