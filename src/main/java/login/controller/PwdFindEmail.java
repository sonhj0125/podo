package login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class PwdFindEmail extends AbstractController {

	private MemberDAO mdao = null;
	
	public PwdFindEmail() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) { // "POST" 방식인 경우
			// 비밀번호 찾기 모달 창에서 "찾기" 버튼을 클릭했을 경우

			String name = request.getParameter("name");
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("name", name);
			paraMap.put("userid", userid);
			paraMap.put("email", email);

			// 데이터베이스에 사용자가 존재하는지 알아오기
			boolean isUserExist = mdao.isUserExist(paraMap);
			
			
		
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/login/pwdFindEmail.jsp");
			
		} 
		
		request.setAttribute("method", method);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/login/pwdFindEmail.jsp");
		
		
	}

}
