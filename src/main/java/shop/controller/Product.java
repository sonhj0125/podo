package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;
import shop.domain.ReviewDTO;
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
				
				// 좋아요 수 확인
				int likeItCnt = pdao.getLikeCnt(pindex);
				request.setAttribute("likeItCnt", likeItCnt);

				String cartInfo = "none";
				String likeIt = "none";
				
				// pindex에 대한 리뷰 목록 불러오기
				List<ReviewDTO> reviewList = pdao.getReviewListByPindex(pindex);
				
				request.setAttribute("pdto", pdto);
				request.setAttribute("reviewList", reviewList);
				
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
				
				String pdImgName = pdao.getProductDetailImg(pindex);
				
				if (pdImgName != "") {
					// 제품상세이미지가 존재할 경우
					request.setAttribute("pdImgName", pdImgName);
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
