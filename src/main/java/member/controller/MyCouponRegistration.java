package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.controller.AbstractController;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;

public class MyCouponRegistration extends AbstractController{

	CouponDAO codao = null;

	public MyCouponRegistration() {
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String method = request.getMethod();

		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		
		if (loginUser != null && "POST".equals(method)) {
			// 로그인을 했을 경우 + POST 로 값을 받았을 경우

			
			String cocode = request.getParameter("cocode");
			
			// boolean isExists = codao.isCoupon(cocode); 이렇게해서 쿠폰이 있는지 확인한 후에 있으면 true 없으면 false 로 해서 값 반환한다. 
			boolean isExists = codao.isCoupon(null);
			
			JSONObject jsonObj = new JSONObject(); // js 의 표현기법으로 넘겨줘야한다. [{},{}] 이렇게 하려면 lib 가 존재해야한다.
			jsonObj.put("isExists", isExists);
			
			String json = jsonObj.toString();
			
			request.setAttribute("json", json);

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		}
		 else {
				// 마이페이지에서 로그아웃했을 경우 아닐경우 홈페이지로 이동
				super.setRedirect(true);
				super.setViewPage(request.getContextPath() + "/index.wine");
				return;
			}
		
	}
		

}
