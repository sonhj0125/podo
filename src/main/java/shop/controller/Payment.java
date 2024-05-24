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
import coupon.domain.CouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Payment extends AbstractController {

	CouponDAO codao = null;
	MemberDAO mdao = null;
	CartDAO cdao = null;
	
	public Payment() {
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
				
				// 여기서부터 수정
				
				// 방법 (클라이언트를 믿지마라)
				// 선택된 카트 배열만 받는다
				// 사용할 포인트, 사용할 쿠폰을 정보를 클라이언트에서 받는다. (서버에서 다시 검사 할것)
				// 주문정보는 네추럴로 전부 받아와서 넘기기만 한다.
				// 위 정보가 유효하다면 최종 결제를 실행한다. 결제 성공시,
				// 주문정보를 처리 페이지로 넘긴다.
				// 계산이 성공된후 다음을 처리한다.
				
				// 1. 쿠폰을 사용했으면 쿠폰삭제(상태변경) [if]
				// 2. 포인트를 사용했으면 포인트차감 [if]
				// 3. 물품별 포인트 적립및 기록작성
				// 4. 주문 DB 최신화
				// 5. 구매한 물품의 CART 정보 삭제
				// 6. 배달 DB 최신화
				
				// 받아야하는 정보 : 모든 주문자 정보, 회원아이디, 쿠폰정보, 포인트사용정보, 구매할 ITEM(CINDEX) 정보
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
				
				// 플래그
				
				boolean haveCoupon = false;
				boolean havePoint = false;
				
				// 유효성 검사 (쿠폰)
				
				if(!"-- 쿠폰 선택 --".equals(coname)) {
					
					Map<String,String> paraMap = new HashMap<>();
					
					paraMap.put("userid", userid);
					paraMap.put("coname", coname);
					
					if(!codao.isCoupon(paraMap)) {
						errsend(request);
						return;
					}
					
					haveCoupon = true;
					
				}
				
				// 유효성 검사 (포인트)
				
				try {
					
					int pointNum = Integer.parseInt(point);
					
					if(pointNum != 0) {
						int mypoint = mdao.pointread(userid);
						
						if(mypoint == -1) {
							errsend(request);
							return;
						}else if(pointNum > mypoint) {
							errsend(request);
							return;
						}
						
						havePoint = true;
						
					}
					
				}catch (Exception e) {
					errsend(request);
					return;
				}
				
				// 서버 가격 계산
				// 1. 제품정보 가져오기 (제품명, 가격, 포인트, 구매수량)
				String[] pname = new String[cindex.length];
				int[] pprice = new int[cindex.length];
				int[] ppoint = new int[cindex.length];
				String[] cvolume = new String[cindex.length];
				
				for(int i=0; i<cindex.length;i++) {
					
					CartDTO cdto = cdao.getProuctinfo(cindex[i]);
					
					pname[i] = cdto.getPdto().getPname();
					pprice[i] = Integer.parseInt(cdto.getPdto().getPprice());
					ppoint[i] = Integer.parseInt(cdto.getPdto().getPpoint());
					cvolume[i] = cdto.getCvolume();
					
				}
				
				// 2. 가격,적립포인트 계산 (총 가격 + 배달비)
				
				int sumPrice = 3000;
				int sumPoint = 0;
				
				for(int i=0;i<pprice.length;i++) {
					sumPrice += pprice[i] * Integer.parseInt(cvolume[i]);
					sumPoint += ppoint[i];
				}
				
				//3. 쿠폰 보유시
				
				if(haveCoupon) {
					
					CouponDTO codto = codao.getcouponinfo(coname);
					
					int discount = 0;
					
					if(1 == codto.getCotype()) { //할인금액
						
						discount = codto.getCodiscount();
						
					}else { // 할인률
						
						discount = sumPrice * codto.getCodiscount() / 100 ;
						
					}
					
					sumPrice = sumPrice - discount;
					
				}
				
				//4. 포인트 보유시
				
				if(havePoint) {
					
					sumPrice = sumPoint - Integer.parseInt(point);
					
				}
				
				//5. 제품명 만들기
				
				
				
				// 전부 성공시
				return;
				// super.setRedirect(false);
				// super.setViewPage("/WEB-INF/member/paymentGateway.jsp");
				
			}else {
				errsend(request);
				return;
				
			}
		}else {
			errsend(request);
			return;
		}
		
	}
	
	private void errsend(HttpServletRequest request) {
		String message = "잘못된 접근입니다.";
        String loc = "javascript:history.back()";
         
        request.setAttribute("msg", message);
        request.setAttribute("loc", loc);
         
        super.setRedirect(false);
        super.setViewPage("/WEB-INF/msg.jsp");
        return;
	}
	
}
