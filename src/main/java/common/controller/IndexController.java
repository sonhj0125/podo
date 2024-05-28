package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class IndexController extends AbstractController {

	ProductDAO pdao = null;
	CouponDAO codao = null;
	
	public IndexController() {
		
		pdao = new ProductDAO_imple();
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
			HttpSession session = request.getSession();
			MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

			
			if (loginUser != null) {
				// 로그인을 했을 경우
				// 총 쿠폰 발행 수,	사용 쿠폰 수,	가용 쿠폰 수
				int availableCoupons = codao.getAvailableCoupons(loginUser.getUserid());

				request.setAttribute("availableCoupons", availableCoupons);
			}
			
		List<ProductDTO> pdtoList = pdao.listReadDesc();
		List<ProductDTO> pdtoList2 = pdao.listPopReadDesc();
		
		request.setAttribute("newProductList", pdtoList);
		request.setAttribute("popProductList", pdtoList2);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/index.jsp");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
