package review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ReviewDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ReviewUpdate extends AbstractController {
	
	ProductDAO pdao = null;
	
	public ReviewUpdate() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			// 로그인을 했다면
			
			String method = request.getMethod();
			
			String rindex = request.getParameter("rindex"); // 리뷰 작성번호
			
			HttpSession session = request.getSession();
			MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
			
			if(!"POST".equalsIgnoreCase(method)) {
				// GET 방식일 경우
				
				try {
					Integer.parseInt(rindex);
					
				} catch (NumberFormatException e) {
					// url에서 rindex를 문자로 바꿀 경우 리뷰 관리 페이지로 이동
					
					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/reviewList.wine");
					return; // execute() 함수 종료
				}
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("rindex", rindex);
				paraMap.put("userid", loginUser.getUserid());
				
				// rindex에 대한 리뷰 가져오기
				ReviewDTO rdto = pdao.getReviewByRindex(paraMap);
				
				if(rdto == null) {
					// url에 다른 사용자의 rindex를 넣거나 작성되지 않은 rindex를 입력할 경우

					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/reviewList.wine");
					return; // execute() 함수 종료
				}
				
				request.setAttribute("rdto", rdto);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/myPage/reviewUpdate.jsp");
				
			} else {
				// POST 방식일 경우
				
				String rstar = request.getParameter("rstar");
				String rdetail = request.getParameter("rdetail");
				
				// rdatil XSS 공격 막기
				rdetail = rdetail.replaceAll("<", "&lt");
				rdetail = rdetail.replaceAll(">", "&gt");
				rdetail = rdetail.replaceAll("\r\n", "<br>");
				
				ReviewDTO rdto = new ReviewDTO();
				
				rdto.setRindex(Integer.parseInt(rindex));
				rdto.setRstar(rstar);
				rdto.setRdetail(rdetail);
				
				// 리뷰 수정하기
				int result = pdao.updateReview(rdto);

				String msg = "";
				String loc = "";
				
				if(result == 1) {
					
					msg = "리뷰가 수정되었습니다.";
					loc = request.getContextPath() + "/member/reviewList.wine";
					
				} else {
					msg = "리뷰 수정에 실패하였습니다.\\n메인 페이지로 돌아갑니다.";
					loc = request.getContextPath() + "/index.wine";
				}
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				super.setViewPage("/WEB-INF/msg.jsp");
			}
			
			
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
