package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class EmailDuplicateCheck extends AbstractController {

	MemberDAO mdao = null;
	
	public EmailDuplicateCheck() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("POST".equals(method)) {
			
			String email = request.getParameter("email");
			
			boolean isExist = mdao.emailDuplicateCheck(email);
			
			JSONObject jsonObj = new JSONObject(); // {}
			jsonObj.put("isExist", isExist); // {"isExist":true} 또는 {"isExist":false} 로 만들어진다.
			
			String json = jsonObj.toString(); // 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다.
			
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
			
		}
		
	}

}
