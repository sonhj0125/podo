package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import member.domain.PointDTO;

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
