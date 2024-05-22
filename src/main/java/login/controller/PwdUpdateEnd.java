package login.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class PwdUpdateEnd extends AbstractController {

	private MemberDAO mdao = null;
	
	public PwdUpdateEnd() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userid = request.getParameter("userid");
		
		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("POST".equalsIgnoreCase(method)) {
			// "암호 변경하기" 버튼을 클릭했을 경우
			
			String new_pwd = request.getParameter("pwd");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("new_pwd", new_pwd);
			
			int n = 0;
			
			try {
				n = mdao.pwdUpdate(paraMap);
				]
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("n", n);
			
		} // end of if("POST".equalsIgnoreCase(method)) ----------------
		
		request.setAttribute("userid", userid);
		request.setAttribute("method", method);
		
//		super.setRedirect(false);
		super.setViewPage("/WEB-INF/login/pwdUpdateEnd.jsp");
	}

}
