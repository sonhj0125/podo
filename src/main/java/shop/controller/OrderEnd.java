package shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.domain.DeliveryDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import shop.domain.OrderDTO;
import shop.model.ProductDAO;

public class OrderEnd extends AbstractController{

	CouponDAO codao = null;
	MemberDAO mdao = null;
	CartDAO cdao = null;
	ProductDAO pdao = null; 
	
	public OrderEnd() {
		mdao = new MemberDAO_imple();
		codao = new CouponDAO_imple();
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
		
			String userid = request.getParameter("userid");
			
			HttpSession session = request.getSession();
			MemberDTO loginuser = (MemberDTO) session.getAttribute("loginUser");
			
			if(loginuser.getUserid().equals(userid)) {
			
				
				String cinedxjoinarr = request.getParameter("cinedxjoinarr");
				String[] cindex = cinedxjoinarr.split("[,]");
				
				String coname = request.getParameter("coname");
				String point = request.getParameter("point");
				
				// 주문자 정보
				String dname = request.getParameter("name");
				String demail = request.getParameter("email");
				String daddress = request.getParameter("address");
				String daddressdeatil = request.getParameter("addressDetail");
				String dphone = request.getParameter("phone");
				String dmsg = request.getParameter("order_msg");
				
				if("직접입력".equals(dmsg)) {
					dmsg = request.getParameter("memo");
				}else if("-- 메시지 선택 --".equals(dmsg)) {
					dmsg = "메세지 없음";
				}
				
				// 쿠폰삭제
				if(!"-- 쿠폰 선택 --".equals(coname)) {
					
					Map<String,String> paraMap = new HashMap<>();
					
					paraMap.put("userid", userid);
					paraMap.put("coname", coname);
					
					if(!codao.delCoupon(paraMap)) {
						System.out.println("쿠폰삭제 실패");
					}
					
				}
				
				// 포인트차감
				if(!"0".equals(point)) {
					
					Map<String,String> paraMap = new HashMap<>();
					
					paraMap.put("userid", userid);
					paraMap.put("point", point);
					
					if(!mdao.delPoint(paraMap)) {
						System.out.println("포인트 차감 실패");
					}
					if(!mdao.writePointDown(paraMap)) {
						System.out.println("포인트 차감 기록작성 실패");
					}
					
				}
				
				// 물품별 포인트 적립
				try {
					
					int sumPoint = 0;
					
					for(int i=0;i<cindex.length;i++) {
						
						CartDTO cdto = cdao.getProuctinfo(cindex[i]);
						
						sumPoint += Integer.parseInt(cdto.getPdto().getPpoint());
						
					}
					
					Map<String,String> paraMap = new HashMap<>();
					
					paraMap.put("userid", userid);
					paraMap.put("point", String.valueOf(sumPoint));
					
					if(!mdao.addPoint(paraMap)) {
						System.out.println("포인트 적립 실패");
					}
					if(!mdao.writePointUp(paraMap)) {
						System.out.println("포인트 적립 기록작성 실패");
					}
					
				}catch (Exception e) {
					System.out.println("포인트 적립중 오류 발생");
				}
				
				try {
					for(int i=0;0<cindex.length;i++) {
						
						// 주문내역 입력
						
						CartDTO cdto = cdao.getProuctinfo(cindex[i]);
						
						OrderDTO odto = new OrderDTO();
						odto.setOpoint(cdto.getPdto().getPpoint());
						odto.setOvolume(cdto.getCvolume());
						
						int sumPrice = Integer.parseInt(cdto.getPdto().getPprice())*Integer.parseInt(cdto.getCvolume());
						odto.setOtotalprice(String.valueOf(sumPrice));
						odto.setUserid(userid);
						
						odto.setPindex(cdto.getPdto().getPindex());
						
						if(1!=cdao.setOrder(odto)) {
							System.out.println("주문 DB 작성중 오류 발생");
						}
						
						// 배송지 입력
						
						DeliveryDTO ddto = new DeliveryDTO();
						
						int Oindex = cdao.getOindex(odto);
						
						ddto.setOindex(Oindex);
						ddto.setDname(dname);
						ddto.setDemail(demail);
						ddto.setDphone(dphone);
						ddto.setDmsg(dmsg);
						ddto.setDaddress(daddress);
						ddto.setDaddressdetail(daddressdeatil);
						
						if(!cdao.setDelivery(ddto)) {
							System.out.println("배달 DB 작성중 오류 발생");
						}
						
						// 장바구니 삭제
						
						if(1 != cdao.deleteCartfromindex(cindex[i])) {
							System.out.println("장바구니 삭제 실패");
						}
						
						
					}// end of for
				}catch (Exception e) {
					
				}
				
				String newpoint = mdao.getMyPoint(userid);
				// 세션 재입력
				
				loginuser.setPoint(newpoint);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/orderEnd.jsp");
				return;
				
			}// 유효성
			
		}// Post
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");

	}


}