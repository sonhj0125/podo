package login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;

public class Signout extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 현 위치 주소 받아서 뷰단으로 날려야함;;
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");
		
	}

}
