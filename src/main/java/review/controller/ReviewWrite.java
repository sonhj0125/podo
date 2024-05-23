package review.controller;

import java.util.HashMap;
import java.util.Map;

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
			
			String method = request.getMethod();
			
			String oindex = request.getParameter("oindex"); // 주문번호
			
			if(!"POST".equalsIgnoreCase(method)) {
				// GET 방식일 경우
				
				
				try {
					Integer.parseInt(oindex);
					
				} catch (NumberFormatException e) {
					// url에서 oindex를 임의로 바꿀 경우 리뷰 관리 페이지로 이동
					
					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/reviewList.wine");
					return; // execute() 함수 종료
				}
				
				// 주문 인덱스에 대한 상품 정보 받아오기
				ProductDTO pdto = pdao.getProductByOindex(oindex);
				
				request.setAttribute("pdto", pdto);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/myPage/reviewWrite.jsp");
				
			} else {
				// POST 방식일 경우
				
				String rstar = request.getParameter("rstar");
				String rdetail = request.getParameter("rdetail");
				
				// rdatil XSS 공격 막기
				rdetail = rdetail.replaceAll("<", "&lt");
				rdetail = rdetail.replaceAll(">", "&gt");
				rdetail = rdetail.replaceAll("\r\n", "<br>");
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("oindex", oindex);
				paraMap.put("rstar", rstar);
				paraMap.put("rdetail", rdetail);
				
				// 리뷰 작성하기
				int n = pdao.addReview(paraMap);
				
				String msg = "";
				String loc = "";
				
				if(n == 1) {
					
					msg = "리뷰 작성 성공!";
					loc = request.getContextPath() + "/member/reviewList.wine";
					
					
				} else {
					msg = "리뷰 작성 실패";
					loc = request.getContextPath() + "/index.wine";
				}
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				super.setViewPage("/WEB-INF/msg.jsp");
			}
			
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
