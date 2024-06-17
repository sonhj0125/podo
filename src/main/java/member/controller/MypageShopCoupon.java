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
import my.util.MyUtil;

public class MypageShopCoupon extends AbstractController {

	CouponDAO codao = null;

	public MypageShopCoupon() {
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		
		if (loginUser != null) {
			// 로그인을 했을 경우
			
			request.setAttribute("loginUser", loginUser);
			
			int availableCoupons = codao.getAvailableCoupons(loginUser.getUserid());
			session.setAttribute("availableCoupons", availableCoupons);
			
			request.setAttribute("availableCoupons", availableCoupons);
			
			String userid = loginUser.getUserid();

            // 현재 페이지 번호
            String currentShowPageNo = request.getParameter("currentShowPageNo");
            // 페이지당 보여줄 쿠폰 개수
            String sizePerPage = "5";

            // 총 페이지 수
            int totalPage = codao.getTotalPage(userid);
			if(currentShowPageNo == null) {
				currentShowPageNo = "1";
			}
			
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
			
			
			String pageBar = "";  
			
			int blockSize = 5;
			// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.
			
			int loop = 1;
			// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다. 
			
			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
			int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1; 
			// pageNo 는 페이지바에서 보여지는 첫번째 번호이다.
			
			// *** [맨처음][이전] 만들기 *** //
			pageBar += "<li class='page-item'><a class='page-link' href='mypageShopCoupon.wine?sizePerPage="+sizePerPage+"&currentShowPageNo=1'>◀◀</a></li>"; 
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='mypageShopCoupon.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>◀</a></li>"; 
			}
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if(pageNo == Integer.parseInt(currentShowPageNo)) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>"; 
				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='mypageShopCoupon.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>"; 
				}
				
				loop++;    // 1 2 3 4 5 6 7 8 9 10
				
				pageNo++;  //  1  2  3  4  5  6  7  8  9 10
				           // 11 12 13 14 15 16 17 18 19 20
				           // 21 22 23 24 25 26 27 28 29 30
				           // 31 32 33 34 35 36 37 38 39 40
				           // 41 42 
				            
			}// end of while( !( ) )--------
			
			// *** [다음][마지막] 만들기 *** //
			// pageNo ==> 11
			
			if(pageNo <= totalPage) { 
				pageBar += "<li class='page-item'><a class='page-link' href='mypageShopCoupon.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>▶</a></li>";
			}
			pageBar += "<li class='page-item'><a class='page-link' href='mypageShopCoupon.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>▶▶</a></li>";
			
			
			// *** ======= 페이지바 만들기 끝 ======= *** //	        
	        
			
			
			// *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
			String currentURL = MyUtil.getCurrentURL(request);
			// 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.
			
            // **** 페이징 처리를 한 모든 쿠폰 목록 보여주기 **** //
            List<MyCouponDTO> MyCouponpagingList = codao.selectMyCouponpaging(paraMap);
            
			request.setAttribute("MyCouponpagingList", MyCouponpagingList);
			request.setAttribute("sizePerPage", sizePerPage);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("currentURL", currentURL);
			
            // 총 쿠폰 개수
            int totalMyCouponCount = codao.getTotalMyCouponCount(userid);
            
            request.setAttribute("totalMyCouponCount", totalMyCouponCount);
            request.setAttribute("currentShowPageNo", currentShowPageNo);
            

	        
			// 총 쿠폰 발행 수,	사용 쿠폰 수,	가용 쿠폰 수
			List<MyCouponDTO> myCouponList = codao.getMyCouponList(userid);
			
			int totalCoupon = 0;
			int usedCoupon = 0;
			if(myCouponList.size() != 0) {
				totalCoupon = myCouponList.size(); // 총 쿠폰 발행 수
				
				for (MyCouponDTO myCoupon : myCouponList) {
					if(myCoupon.getCostatus() == 2) {
						usedCoupon++; // 사용한 쿠폰 수
					}

				}
			}
			
			request.setAttribute("myCouponList", myCouponList);
			request.setAttribute("totalCoupon", totalCoupon);
			request.setAttribute("usedCoupon", usedCoupon);
			
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
