package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.domain.CouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import shop.domain.OrderDTO;

public class OrderEnd extends AbstractController{

	CartDAO cdao = null;
	CouponDAO codao = null;
	
	public OrderEnd() {
		
		cdao = new CartDAO_imple();
		codao = new CouponDAO_imple();
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			
			String cinedxjoinarr = request.getParameter("cinedxjoinarr");
			String[] cindex = cinedxjoinarr.split("[,]");
			
			String coname = request.getParameter("coname");

			CouponDTO codto = codao.getcouponinfo(coname);
			
			HttpSession session = request.getSession();
			MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");
			
			if(mdto != null) {
				
				for(int i=0; i<cindex.length;i++) {
					
					CartDTO cdto = cdao.getProuctinfo(cindex[i]);
					
					String ototalprice = "";
					
					if(codto.getCotype()==1) {
						
						int price = Integer.parseInt(cdto.getPdto().getPprice());
						int volume = Integer.parseInt(cdto.getCvolume());
						int discount = codto.getCodiscount() / cindex.length;
						
						ototalprice = String.valueOf(( (price * volume) - discount) ); 
						
					}else {
						
						int price = Integer.parseInt(cdto.getPdto().getPprice());
						int volume = Integer.parseInt(cdto.getCvolume());
						int discount = codto.getCodiscount();
						
						ototalprice = String.valueOf((price * volume * 100 / discount) ); 
						
					}
					
					String opoint = String.valueOf(Integer.parseInt(cdto.getPdto().getPpoint()) * Integer.parseInt(cdto.getCvolume()) );
					String ovolume = cdto.getCvolume();

					OrderDTO odto = new OrderDTO();
					
					odto.setOtotalprice(ototalprice);
					odto.setOpoint(opoint);
					odto.setOvolume(ovolume);
					
					odto.setPdto(cdto.getPdto());
					odto.setMdto(mdto);
					
					if(1==cdao.orderone(odto)) {
						
					}
					
				}
				
			}
			
			
			
		}

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/shop/orderEnd.jsp");

	}


}