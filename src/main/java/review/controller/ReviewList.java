package review.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ReviewDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ReviewList extends AbstractController {
	
	ProductDAO pdao = null;
	
	public ReviewList() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			// 로그인을 했다면
			
			HttpSession session = request.getSession();
			MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
			
			String userid = loginUser.getUserid();
			
			// [리뷰 관리] 회원이 주문한 상품 중 배송완료인 상품 목록 띄우기
			List<ReviewDTO> reviewPdtList = pdao.selectProductReviewList(userid);
			
			request.setAttribute("reviewPdtList", reviewPdtList);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/myPage/reviewList.jsp");
			
		} else {
			// 로그인을 하지 않았다면
			String msg = "로그인 후 이용 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
	}

}
