package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class MemberRegister extends AbstractController {

	MemberDAO mdao = null;
	
	public MemberRegister() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				
				MemberDTO mdto = new MemberDTO();
				
				mdto.setUserid(request.getParameter("userid"));
				mdto.setPwd(request.getParameter("pwd"));
				mdto.setName(request.getParameter("name"));
				mdto.setEmail(request.getParameter("email"));
				mdto.setPhone(request.getParameter("phone"));
				mdto.setAddress(request.getParameter("address"));
				mdto.setAddressDetail(request.getParameter("addressDetail"));
				mdto.setGender(request.getParameter("gender"));
				mdto.setBirthday(request.getParameter("birthday"));
				
				if(mdao.doRegister(mdto)==1) {
					request.setAttribute("userid", mdto.getUserid());
					request.setAttribute("pwd", mdto.getPwd());
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/login/autoLogin.jsp");
				}else {
					request.setAttribute("msg", "알수없는이유로 회원가입에 실패하였습니다.");
					request.setAttribute("loc", request.getContextPath()+"/index.wine");
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/msg.jsp");
				}
				
			}else {
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/memberRegister.jsp");	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
