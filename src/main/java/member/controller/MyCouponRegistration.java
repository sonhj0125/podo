package member.controller;

import java.util.HashMap;
import java.util.Map;

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

		
		if (loginUser != null && "POST".equalsIgnoreCase(method)) {
			// 로그인을 했을 경우 + POST 로 값을 받았을 경우

			String cocode = request.getParameter("cocode");
			String userid = loginUser.getUserid();
			// 쿠폰등록 (쿠폰번호가 있으면 true 없으면 false 로 해서 값 반환한다.)
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("cocode", cocode);
			paraMap.put("userid", userid);
			
			boolean result = false;
			boolean isExists = false;
			
			JSONObject jsonObj = new JSONObject(); // js 의 표현기법으로 넘겨줘야한다. [{},{}] 이렇게 하려면 lib 가 존재해야한다.
			
			result = codao.isCoupon(paraMap);  // 이미 있으면 true 없으면 false
			
			if(!result) {
				// 이미 등록된 쿠폰이 아닐경우
				isExists = codao.CouponRegistration(paraMap);  // 등록됐으면 true 안됐으면 false
			}
			
			jsonObj.put("isExists", isExists);
			jsonObj.put("result", result);
			
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
