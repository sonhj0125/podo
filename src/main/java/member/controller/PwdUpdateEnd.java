package member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class PwdUpdateEnd extends AbstractController {

	private MemberDAO mdao = null;
	
	public PwdUpdateEnd() {
		mdao = new MemberDAO_imple();
	}	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("POST".equalsIgnoreCase(method)) {
           // **** POST 방식으로 넘어온 것이라면 **** //

			
		   // === 회원수정이 성공되어지면 "회원정보 수정 성공!!" 이라는 alert 를 띄우고 시작페이지로 이동한다. === //
		   String msg = "";
		   String loc = "";
			
			try {
				String new_pwd = request.getParameter("pwd");
				
				HttpSession session = request.getSession();
				MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("userid", loginUser.getUserid());
				paraMap.put("new_pwd", new_pwd);
				
				int n = 0;
				
				n = mdao.pwdUpdate(paraMap);
					
				if(n==1) {
					
					loginUser.setPwd(new_pwd);

					msg = "비밀번호 변경 성공!!";
					
					session.invalidate();
					loc = request.getContextPath()+"/index.wine"; // 시작페이지로 이동한다.
				}
				
			} catch(SQLException e) {
				msg = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것. 
				e.printStackTrace();
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false); 
			super.setViewPage("/WEB-INF/msg.jsp");
		}		
		
	}

}
