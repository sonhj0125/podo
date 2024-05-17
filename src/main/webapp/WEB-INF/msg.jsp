<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	alert("${requestScope.message}"); // 메시지 출력해주기
	location.href = "${requestScope.loc}"; // 페이지 이동
	
	/*  
	    location.href="javascript:history.go(-2);";  // 이전이전 페이지로 이동 
	    location.href="javascript:history.go(-1);";  // 이전 페이지로 이동
	    location.href="javascript:history.go(0);";   // 현재 페이지로 이동(==새로고침) 캐시에서 읽어옴.
	    location.href="javascript:history.go(1);";   // 다음 페이지로 이동.
	    
	    location.href="javascript:history.back();";       // 이전 페이지로 이동 
	    location.href="javascript:location.reload(true)"; // 현재 페이지로 이동(==새로고침) 서버에 가서 다시 읽어옴. 
	    location.href="javascript:history.forward();";    // 다음 페이지로 이동.
	*/
	 
	/* === 새로고침(다시읽기) 방법 3가지 차이점 ===
	   >>> 1. 일반적인 다시읽기 <<<
	   window.location.reload();
	   ==> 이렇게 하면 컴퓨터의 캐시에서 우선 파일을 찾아본다.
	       없으면 서버에서 받아온다. 
	   
	   >>> 2. 강력하고 강제적인 다시읽기 <<<
	   window.location.reload(true);
	   ==> true 라는 파라미터를 입력하면, 무조건 서버에서 직접 파일을 가져오게 된다.
	       캐시는 완전히 무시된다.
	   
	   >>> 3. 부드럽고 소극적인 다시읽기 <<<
	   history.go(0);
	   ==> 이렇게 하면 캐시에서 현재 페이지의 파일들을 항상 우선적으로 찾는다.
	*/
	
</script>