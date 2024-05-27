package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;

public class MypageShopCoupon extends AbstractController {

	CouponDAO codao = null;

	public MypageShopCoupon() {
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();

		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		
		if (loginUser != null) {
			// 로그인을 했을 경우

			request.setAttribute("loginUser", loginUser);
			
			String userid = loginUser.getUserid();

            // 현재 페이지 번호
            String currentShowPageNo = "1";

            // 페이지당 보여줄 쿠폰 개수
            String sizePerPage = "5";

            // 총 쿠폰 개수
            int totalMyCouponCount = codao.getTotalMyCouponCount(userid);

            // 총 페이지 수
            int totalPage = codao.getTotalPage(userid);
            
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("userid", userid);
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수 
            
			try {
				 if( Integer.parseInt(currentShowPageNo) > totalPage || 
					 Integer.parseInt(currentShowPageNo) <= 0 ) {
					 currentShowPageNo = "1";
					 paraMap.put("currentShowPageNo", currentShowPageNo);
				 }
			} catch(NumberFormatException e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}
			
            
            // **** 페이징 처리를 한 모든 쿠폰 목록 보여주기 **** //
            List<MyCouponDTO> MyCouponpagingList = codao.selectMyCouponpaging(paraMap);
			
			request.setAttribute("MyCouponpagingList", MyCouponpagingList);
			
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentShowPageNo", currentShowPageNo);
            request.setAttribute("totalMyCouponCount", totalMyCouponCount);
			
            StringBuffer requestURL = request.getRequestURL();
            String url = requestURL.toString();
            
            StringBuilder pageBarsb = new StringBuilder();
			
			int blockSize = 10;
			// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.
			
			int loop = 1;
			// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다. 
			
			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
			int startPage   = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1; 
			
	        int endPage = startPage + blockSize - 1;
	        if (endPage > totalPage) {
	            endPage = totalPage;
	        }
	        
	        if (startPage  > 1) {
	            pageBarsb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"").append(url).append("?pageNo=").append(startPage - 1).append("\">&laquo;</a></li>");
	        }
	        
	        // 페이지 번호 링크 추가
	        for (int i = startPage; i <= endPage; i++) {
	            if (i == (Integer.parseInt(currentShowPageNo))) {
	                pageBarsb.append("<li class=\"page-item active\"><a class=\"page-link\" href=\"#\">").append(i).append("</a></li>");
	            } else {
	                pageBarsb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"").append(url).append("?pageNo=").append(i).append("\">").append(i).append("</a></li>");
	            }
	        }
	        
	        // 다음 페이지 바 링크 추가
	        if (endPage < totalPage) {
	            pageBarsb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"").append(url).append("?pageNo=").append(endPage + 1).append("\">&raquo;</a></li>");
	        }
	        
	        String pageBar = pageBarsb.toString();
	        
	        request.setAttribute("pageBar", pageBar);
			
			// 총 쿠폰 발행 수,	사용 쿠폰 수,	가용 쿠폰 수
			List<MyCouponDTO> myCouponList = codao.getMyCouponList(userid);
			
			int totalCoupon = 0;
			int usedCoupon = 0;
			int availableCoupons = 0;
			if(myCouponList.size() != 0) {
				totalCoupon = myCouponList.size(); // 총 쿠폰 발행 수
				
				for (MyCouponDTO myCoupon : myCouponList) {
					if(myCoupon.getCostatus() == 2) {
						usedCoupon++; // 사용한 쿠폰 수
					}
					if(myCoupon.getCostatus() == 1) {
						availableCoupons++; // 가용 쿠폰 수
					}
				}
			}
			
			request.setAttribute("myCouponList", myCouponList);
			request.setAttribute("totalCoupon", totalCoupon);
			request.setAttribute("usedCoupon", usedCoupon);
			request.setAttribute("availableCoupons", availableCoupons);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/mypageShopCoupon.jsp");
		}
		 else {
				// 마이페이지에서 로그아웃했을 경우 아닐경우 홈페이지로 이동
				super.setRedirect(true);
				super.setViewPage(request.getContextPath() + "/index.wine");
				return;
			}
		
	}

}
