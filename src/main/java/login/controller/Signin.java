package login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Signin extends AbstractController {

	MemberDAO mdao = null;
	
	public Signin() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("userid", request.getParameter("userid"));
				paraMap.put("pwd", request.getParameter("pwd"));
				
				MemberDTO loginUser = mdao.signin(paraMap);
				
				if(loginUser != null) {
					
					
					
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", loginUser);
					
				}else {
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/index.jsp");
					return;
					
				}
				
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.wine");
	
				
			}else {
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/index.jsp");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
