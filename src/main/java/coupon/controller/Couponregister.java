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
		String loc = request.getContextPath() + "/member/admin/adminCoupon.wine";

		if ("POST".equalsIgnoreCase(method)) {

			if (super.isDir(request.getSession())) { // 관리자만 접근가능

				CouponDTO codto = new CouponDTO();
				
				String cocode = request.getParameter("cocode");
				if(cocode == null) {
					codto.setCocode("");
				}
				else {
					codto.setCocode(cocode);
				}

				codto.setConame(request.getParameter("coname"));
				codto.setCodetail(request.getParameter("codetail"));
				codto.setCotype(Integer.parseInt(request.getParameter("cotype")));
				codto.setCodiscount(Integer.parseInt(request.getParameter("codiscount")));
				codto.setCodate(request.getParameter("codate"));

				// 쿠폰을 삽입하기 전에 쿠폰이 이미 존재하는지 중복 검사(coname이름PK)
				boolean isCouponExist = false; // 쿠폰이 이미 존재하는가 (존재하면 true 없으면 false)
				isCouponExist = codao.isCouponExist(codto);

				if (isCouponExist) {
					// 중복된 쿠폰이름이 존재하면
					msg = "중복된 쿠폰 존재합니다.";
				}

				else {
					// 중복된 쿠폰이름이 존재하지 않으면
					if (1 == codao.register(codto)) {
						msg = "쿠폰 등록 성공";
					}
				}

			}

		}

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/msg.jsp");

	}

}
