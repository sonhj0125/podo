package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import coupon.domain.CouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;

public class Checkcoupondate extends AbstractController {
	
	CouponDAO codao = null;
	
	public Checkcoupondate() {
		codao = new CouponDAO_imple();
	}
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String coname = request.getParameter("coname");
			
			CouponDTO codto = codao.getcouponinfo(coname);
			
			JSONObject json = new JSONObject();
			json.put("cotype", codto.getCotype());
			json.put("codiscount", codto.getCodiscount());
			
			String jsonStr = json.toString();
			
			request.setAttribute("json", jsonStr);

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
			
		}
		
		
	}

}
