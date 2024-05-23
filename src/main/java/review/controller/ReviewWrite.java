package review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ReviewWrite extends AbstractController {
	
	ProductDAO pdao = null;
	
	public ReviewWrite() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			
			String oindex = request.getParameter("oindex");
			
			int n_oindex = 0;
			try {
				n_oindex = Integer.parseInt(oindex);
				
			} catch (NumberFormatException e) {
				// url에서 oindex를 임의로 바꿀 경우
				
				super.setRedirect(true);
				super.setViewPage("javascript:history.back()");
			}
			
			// 주문 인덱스에 대한 상품 정보 받아오기
			ProductDTO pdto = pdao.getProductByOindex(n_oindex);
			
			request.setAttribute("pdto", pdto);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/myPage/reviewWrite.jsp");
			
			
		}  else {
			// 로그인을 하지 않았다면
			String msg = "로그인 후 이용 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
	}

}
