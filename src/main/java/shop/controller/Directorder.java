package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;

public class Directorder extends AbstractController{
	
	CartDAO cdao = null;
	CouponDAO codao = null;
	
	public Directorder() {
		cdao = new CartDAO_imple();
		codao = new CouponDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		String loc = request.getParameter("url");
		
		if("POST".equalsIgnoreCase(method)) {
			
			if(super.checkLogin(request)) {
				
				String userid = request.getParameter("userid");
				String pindex = request.getParameter("pindex");
				String cvolume = request.getParameter("cvolume");
				
				MemberDTO mdto = new MemberDTO();
				ProductDTO pdto = new ProductDTO();
				
				mdto.setUserid(userid);				
				pdto.setPindex(Integer.parseInt(pindex));
				
				CartDTO cdto = new CartDTO();
				
				cdto.setMdto(mdto);
				cdto.setPdto(pdto);
				cdto.setCvolume(cvolume);
				
				cdao.insertCart(cdto);
				
				String cindex = cdao.directselectCidx(userid);
				
				boolean nextStep = true;
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("cindex", cindex);
				paraMap.put("cvolume", cvolume);
				
				if(cdao.modifyVolume(paraMap)!=1) {
					nextStep = false;
				}
				
				if(nextStep) { // 정상
					
					List<CartDTO> cdtoList = cdao.getList(cindex);
					
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
			
			request.setAttribute("msg", "로그인 후 이용 할수 있습니다");
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		}else {
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
			
		}
	}

}
