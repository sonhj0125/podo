package shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class OrderEnd extends AbstractController{

	CouponDAO codao = null;
	MemberDAO mdao = null;
	CartDAO cdao = null;
	
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
				
				// 주문 DB 작성
				
				
			
			}
			
		}
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");

	}

}