package review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ReviewWrite extends AbstractController {
	
	ProductDAO pdao = null;
	MemberDAO mdao = null;
	
	public ReviewWrite() {
		pdao = new ProductDAO_imple();
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			// 로그인을 했다면
			
			String method = request.getMethod();
			
			String oindex = request.getParameter("oindex"); // 주문번호
			
			HttpSession session = request.getSession();
			MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
			
			if(!"POST".equalsIgnoreCase(method)) {
				// GET 방식일 경우
				
				try {
					Integer.parseInt(oindex);
					
				} catch (NumberFormatException e) {
					// url에서 oindex를 문자로 바꿀 경우 리뷰 관리 페이지로 이동
					
					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/reviewList.wine");
					return; // execute() 함수 종료
				}
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("oindex", oindex);
				paraMap.put("userid", loginUser.getUserid());
				
				// 주문 인덱스에 대한 상품 정보 받아오기
				ProductDTO pdto = pdao.getProductByOindex(paraMap);
				
				// oindex에 대한 리뷰가 존재하는지 확인
				boolean isExistReview = pdao.isExistReviewByOindex(oindex);
				
				if(pdto == null || isExistReview) {
					// url에 다른 사용자의 oindex를 넣거나 이미 작성된 리뷰의 oindex를 넣는 경우

					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/reviewList.wine");
					return; // execute() 함수 종료
					
				}
				
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
				paraMap.put("userid", loginUser.getUserid());
				
				// 리뷰 작성하기
				int result = pdao.addReview(paraMap);
				
				boolean addPoint = false;
				boolean writeLog = false;
				
				if(result == 1) {
					
					String point = "500";
					paraMap.put("point", point);
					
					// 리뷰 작성 포인트 500 주기
					addPoint = mdao.addPoint(paraMap);
					
				}
				
				if(addPoint) {
					// 포인트 적립 로그 작성
					writeLog = mdao.writeReivewPointUp(paraMap);
				}
				
				String msg = "";
				String loc = "";
				
				if(result == 1 && addPoint && writeLog) {
					
					msg = "리뷰가 작성되었습니다.\\n리뷰 등록 후 삭제가 불가합니다.";
					loc = request.getContextPath() + "/member/reviewList.wine";
					
				} else {
					msg = "리뷰 작성에 실패하였습니다.\\n메인 페이지로 돌아갑니다.";
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
