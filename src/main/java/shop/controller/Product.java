package shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class Product extends AbstractController {

	ProductDAO pdao = null;
	CartDAO cdao = null;

	public Product() {
		pdao = new ProductDAO_imple();
		cdao = new CartDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String index = request.getParameter("pindex");

		try {

			int pindex = Integer.parseInt(index);
			ProductDTO pdto = pdao.getproduct(pindex);

			if (pdto != null) {

				String cartInfo = "none";
				String likeIt = "none";
				
				request.setAttribute("pdto", pdto);

				
				HttpSession session = request.getSession();
				MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");
				
				if(mdto != null) {
					String userid = mdto.getUserid();
					
					Map<String,String> paraMap = new HashMap<>(); 
					paraMap.put("userid", userid);
					paraMap.put("pindex", String.valueOf(pindex));
					
					if(cdao.isProductCartIn(paraMap)) {
						cartInfo = "show";
					}
					if(pdao.isLike(paraMap)) {
						likeIt = "show";
					}
					
				}
				
				request.setAttribute("cartInfo", cartInfo);
				request.setAttribute("likeit", likeIt);

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/showproduct.jsp");

			} else {

				// 잘못된 idx값을 넣은경우

				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.wine");
			}

		} catch (Exception e) {

			// 접근오류
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");

		}

	}

}
