package coupon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import coupon.domain.CouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;

public class Couponregister extends AbstractController {

	CouponDAO codao = null;
	
	public Couponregister() {
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		String msg = "잘못된 접근입니다.";
		String loc = request.getContextPath()+"/member/admin/adminCoupon.wine";
		
		if("POST".equalsIgnoreCase(method)) {
			
			if(super.isDir(request.getSession()) ){ // 관리자만 접근가능
				
				CouponDTO codto = new CouponDTO();
				
				codto.setConame(request.getParameter("coname"));
				codto.setCodetail(request.getParameter("codetail"));
				codto.setCotype(Integer.parseInt(request.getParameter("cotype")));
				codto.setCodiscount(Integer.parseInt(request.getParameter("codiscount")));
				codto.setComin(Integer.parseInt(request.getParameter("comin")));
				codto.setCodate(request.getParameter("codate"));
				
				if(1==codao.register(codto)) {
					
					msg = "쿠폰 등록 성공";
					
				}
				
			}
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/msg.jsp");
		
	}

}
