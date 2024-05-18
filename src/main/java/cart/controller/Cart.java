package cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;

public class Cart extends AbstractController {
	
	CartDAO cdao = null;
	
	public Cart() {
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginUser");
		
		if(mdto != null) {

			String userid = mdto.getUserid();
			
			List<CartDTO> cdtoList = cdao.getCartList(userid);
			
			request.setAttribute("cdtoList", cdtoList);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/shop/cart.jsp");
			
		}else {
			
			// 로그인후 이용가능하다는 뷰단 만들어야함 임시로 메인페이지로 이동
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/shop/cart.jsp");
			
		}
		

	}

}