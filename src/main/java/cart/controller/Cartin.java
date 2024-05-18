package cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;

public class Cartin extends AbstractController {

	CartDAO cdao = null;
	
	public Cartin() {
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String msg = "";
			String loc = request.getParameter("url");
			
			String userid = request.getParameter("userid");
			String pindex = request.getParameter("pindex");
			String cvolume = request.getParameter("cvolume");
			
			if(userid.isBlank() || userid==null) {
				
				msg = "로그인 후 이용 가능합니다.";
				
			}else {
				
				MemberDTO mdto = new MemberDTO();
				ProductDTO pdto = new ProductDTO();
				
				mdto.setUserid(userid);				
				pdto.setPindex(Integer.parseInt(pindex));
				
				CartDTO cdto = new CartDTO();
				
				cdto.setMdto(mdto);
				cdto.setPdto(pdto);
				cdto.setCvolume(cvolume);
				
				cdao.insertCart(cdto);
				
				msg = "장바구니에 해당상품을 추가하였습니다.";
				
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		}else { // get 으로 접근했을 경우
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
			
		}
		
	}

	
	
}
