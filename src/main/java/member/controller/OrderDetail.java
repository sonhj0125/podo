package member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.DeliveryDTO;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class OrderDetail extends AbstractController {

	ProductDAO pdao = null;
	
	public OrderDetail() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			// 로그인을 했다면

			String oindex = request.getParameter("oindex"); // 주문번호
			
			HttpSession session = request.getSession();
			MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
			
			try {
				Integer.parseInt(oindex);
				
			} catch (NumberFormatException e) {
				// url에서 oindex를 문자로 바꿀 경우 주문내역 페이지로 이동
				
				super.setRedirect(true);
				super.setViewPage(request.getContextPath() + "/member/orderList.wine");
				return; // execute() 함수 종료
			}

			Map<String, String> paraMap = new HashMap<>();
			
			paraMap.put("oindex", oindex);
			paraMap.put("userid", loginUser.getUserid());
			
			// 주문 인덱스에 대한 상품, 주문, 배송 정보 받아오기
			DeliveryDTO ddto = pdao.getOrderDetail(paraMap);
			
			if(ddto == null) {
				// url에 다른 사용자의 oindex를 넣는 경우

				super.setRedirect(true);
				super.setViewPage(request.getContextPath() + "/member/orderList.wine");
				return; // execute() 함수 종료
				
			}
			
			request.setAttribute("ddto", ddto);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/myPage/orderDetail.jsp");
			
		} else {
			// 로그인을 하지 않았다면
			String msg = "로그인 후 이용 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
	}

}
