package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class AdminProductUpdate extends AbstractController {

    private ProductDAO pdao = null;
      
    public AdminProductUpdate() {
       pdao = new ProductDAO_imple();
    }
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(super.checkLogin(request) && "9".equals(loginUser.getMemberIdx())) {
			// 관리자로 로그인 한 경우
			
//			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/adminProductUpdate.jsp");
			
		} else {
			// 로그인을 하지 않았거나 관리자가 아니라면
			String msg = "관리자만 접근 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";

			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);

			super.setViewPage("/WEB-INF/msg.jsp");
		}
	}

}
