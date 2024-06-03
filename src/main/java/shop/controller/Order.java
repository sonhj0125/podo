package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;

public class Order extends AbstractController {

	CartDAO cdao = null;
	CouponDAO codao = null;
	
	public Order() {
		cdao = new CartDAO_imple();
		codao = new CouponDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			if(super.checkLogin(request)) {
				String cindexArr = request.getParameter("Arr_cindexOne");
				String cvolumeArr = request.getParameter("Arr_cvolumeOne");
				
				boolean nullFlag = false;
				
				try {
					if(cindexArr.isBlank()) {
						nullFlag = true;
					}
				}catch (NullPointerException e) {
					nullFlag = true;
				}
				
				if(nullFlag) {
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/index.wine");
					return;
				}
				
				String cindex[] = cindexArr.split("[,]");
				String volumeArr[] = cvolumeArr.split("[,]");
				
				boolean nextStep = true;
				
				for(int i=0; i<cindex.length;i++) {
					
					Map<String, String> paraMap = new HashMap<>();
					
					paraMap.put("cindex", cindex[i]);
					paraMap.put("cvolume", volumeArr[i]);
					
					if(cdao.modifyVolume(paraMap)!=1) {
						nextStep = false;
					}
					
				}
				
				if(nextStep) { // 정상
					
					HttpSession session = request.getSession();
					MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");
					
					List<CartDTO> cdtoList = cdao.getList(cindexArr);
					
					try {
						List<MyCouponDTO> mycodtoList = codao.getMyList(mdto.getUserid());
						request.setAttribute("mycodtoList", mycodtoList);
					}catch (Exception e) {
					}
					request.setAttribute("cdtoList", cdtoList);
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/shop/order.jsp");
					return;
					
				}
				
			}
			
			request.setAttribute("msg", "로그인후 이용 가능합니다!");
			request.setAttribute("loc", request.getContextPath()+"/index.wine");
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		}else {
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
			
		}
		

	}

}