package login.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class IdFind extends AbstractController {
	
	private MemberDAO mdao = null;

	public IdFind() {
		mdao = new MemberDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();

		if ("POST".equalsIgnoreCase(method)) {

			Map<String, String> paraMap = new HashMap<>();
			
			String name = request.getParameter("name");
			String email =  request.getParameter("email");
			
			paraMap.put("name", name);
			paraMap.put("email", email);
			
			String userid = mdao.finduserid(paraMap);
			
			if(userid != null) {
				request.setAttribute("userid", userid);
			}
			
		}
		
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/login/idFind.jsp");
		
	}

}
