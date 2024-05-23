package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import javax.servlet.http.HttpSession;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class AdminMemberDetail extends AbstractController {

	private MemberDAO mdao = null;
	
	public AdminMemberDetail() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// === 관리자(admin)로 로그인 했을때만 조회가 가능하도록 한다. === //
		if(super.isDir(request.getSession())) {
			// 관리자(admin)로 로그인 했을 경우
			
			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				// POST 방식일 때
				
				String userid = request.getParameter("userid");
				String goBackURL = request.getParameter("goBackURL");
				
				MemberDTO mdto = mdao.selectOneMember(userid);
				
				request.setAttribute("mdto", mdto);
				request.setAttribute("goBackURL", goBackURL);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/admin/adminMemberDetail.jsp");
				
			}
			
			
		}
		else {
			// 관리자(admin)로 로그인 하지 않은 경우 또는 로그인을 하지 않은 경우
			
			
			
			
		}
		
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
