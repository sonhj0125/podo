package shop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.domain.CouponDTO;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import shop.domain.OrderDTO;

public class OrderEnd extends AbstractController{

	CartDAO cdao = null;
	CouponDAO codao = null;
	MemberDAO mdao = null;
	
	public OrderEnd() {
		
		cdao = new CartDAO_imple();
		codao = new CouponDAO_imple();
		mdao = new MemberDAO_imple();
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String cinedxjoinarr = request.getParameter("cinedxjoinarr");
			String[] cindex = cinedxjoinarr.split("[,]");
			String point = request.getParameter("point");
			
			String coname = request.getParameter("coname");
			HttpSession session = request.getSession();
			MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");

			CouponDTO codto = null;
			
			int myPoint = mdao.getPointRange(mdto); // 내 포인트 조회
			
			try {
				if(myPoint<Integer.parseInt(point)) { // 입력된 포인트가 보유 포인트 보다 클 경우
					
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/index.wine");
					return;
				}
			}catch (Exception e) {
				point = "0";
			}
			
			boolean isCoupon = true;
			
			if("-- 쿠폰 선택 --".equals(coname)) {
				isCoupon = false;
			}
			
			if(isCoupon) { // 쿠폰이 있으면 등록후 해당 쿠폰 삭제
				
				codto = codao.getcouponinfo(coname);
				
				MyCouponDTO mycodto = new MyCouponDTO();
				mycodto.setMdto(mdto);
				mycodto.setCodto(codto);
				
				codao.useCoupon(mycodto);
				
			}
			
			if(mdto != null) { // 로그인 한경우
				
				Map<String,String> paraMapPoint = new HashMap<>();
				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
				
				String nowStr = format.format(now);
				
				String msgP = nowStr + "상품구입";
				
				paraMapPoint.put("userid", mdto.getUserid());
				paraMapPoint.put("point", point);
				paraMapPoint.put("podetail", msgP);
				
				mdao.pointuse(paraMapPoint); // 포인트 차감
				mdao.pointWrite(paraMapPoint); // 포인트 로그 기록
				
				for(int i=0; i<cindex.length;i++) { // 장바구니 하나당 오더 하나씩
					
					CartDTO cdto = cdao.getProuctinfo(cindex[i]);
					
					String ototalprice = "";
					
					if(codto != null) { // 쿠폰이 있는경우
						
						if(codto.getCotype()==1) { // 할인
							
							int price = Integer.parseInt(cdto.getPdto().getPprice());
							int volume = Integer.parseInt(cdto.getCvolume());
							
							int discount = codto.getCodiscount() / cindex.length;
							
							ototalprice = String.valueOf(( (price * volume) - discount) ); 
							
						}else { // 할인율
							
							int price = Integer.parseInt(cdto.getPdto().getPprice());
							int volume = Integer.parseInt(cdto.getCvolume());
							int discount = codto.getCodiscount();
							
							ototalprice = String.valueOf((price * volume * 100 / discount) ); 
							
						}
						
					}else { // 쿠폰이 없는경우
						
						int price = Integer.parseInt(cdto.getPdto().getPprice());
						int volume = Integer.parseInt(cdto.getCvolume());
						
						ototalprice = String.valueOf(price * volume);
						
					}
					
					String opoint = String.valueOf(Integer.parseInt(cdto.getPdto().getPpoint()) * Integer.parseInt(cdto.getCvolume()) );
					String ovolume = cdto.getCvolume();

					OrderDTO odto = new OrderDTO();
					
					odto.setOtotalprice(ototalprice);
					odto.setOpoint(opoint);
					odto.setOvolume(ovolume);
					
					odto.setPdto(cdto.getPdto());
					odto.setMdto(mdto);
					
					if(1==cdao.orderone(odto)) { // 오더 성공
						
						Map<String,String> paraMap = new HashMap<>();
						
						String msg = odto.getPdto().getPname()+" 주문";
						
						paraMap.put("userid", mdto.getUserid());
						paraMap.put("point", opoint);
						paraMap.put("podetail",msg);
						
						cdao.deleteCartfromindex(cindex[i]);
						mdao.pointUp(paraMap);
						mdao.pointWrite(paraMap);
						
					}
					
				} // end of for
				
				super.sessionRefresh(request); // session 내용 수정해야함
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/orderEnd.jsp");
				
			}else { // get 접근
				
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.wine");
				
			}
			
		}

	}

}