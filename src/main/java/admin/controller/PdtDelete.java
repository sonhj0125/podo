package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class PdtDelete extends AbstractController {

	ProductDAO pdao = null;
	CartDAO cdao = null;

	public PdtDelete() {
		pdao = new ProductDAO_imple();
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(super.checkLogin(request) && "9".equals(loginUser.getMemberIdx())) {
			// 관리자로 로그인 한 경우

			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				// POST 방식인 경우
				
				String pindex = request.getParameter("pindex");
				
				int result = 0;
				
				// 제품번호에 대한 주문번호 여러 개 받아오기
				List<String> oindexList = pdao.getOindexListByPindex(pindex);
				
				boolean orderDel = false;
				
				if(oindexList.size() != 0) {
					
					int o_result = 0;
					
					for(String oindex : oindexList) {
						
						// 리뷰 삭제하기
						int n1 = pdao.deleteReview(oindex);
						
						// 배송정보 삭제하기
						int n2 = pdao.deleteDelivery(oindex);
						
						if(n1*n2 == 1) {
							// 주문내역 삭제하기
							o_result += pdao.deleteOrders(oindex);
						}
					} // end of for -----------------------
					
					if(o_result == oindexList.size()) {
						orderDel = true;
					}
					
				} else {
					// 제품에 따른 주문내역이 없다면
					orderDel = true;
				}
				
				// 제품에 대한 좋아요 수 확인
				int likeCnt = pdao.getLikeCnt(result);
				
				int l_result = 0;
				
				if(likeCnt != 0) {
					// 좋아요 삭제하기
					l_result = pdao.deleteLikeit(pindex);
					
				} else {
					l_result = 1;
				}
				
				// 제품에 대한 장바구니 개수 확인
				int cartCnt = cdao.getCartCnt(pindex);
				
				int c_result = 0;
				
				if(cartCnt != 0) {
					// 장바구니 내역 삭제하기
					c_result = cdao.deleteCartFromPindex(pindex);
					
				} else {
					c_result = 1;
				}
				
				// 제품상세이미지 삭제하기
				int pd_result = pdao.deletePdimg(pindex);
				
				if(orderDel && l_result != 0 && c_result != 0 && pd_result == 1) {
					// 제품 삭제하기
					result = pdao.deleteProduct(pindex);
				}
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("result", result);

				String json = jsonObj.toString();
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/jsonview.jsp");
				
			} else {

				String msg = "잘못된 접근입니다.";
				String loc = request.getContextPath() + "/index.wine";
		
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
		
				super.setViewPage("/WEB-INF/msg.jsp");
			}
			
			
		} else {
			// 로그인을 하지 않았거나 관리자가 아니라면
			
			String msg = "관리자만 접근 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";
	
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
	
			super.setViewPage("/WEB-INF/msg.jsp");
		
		}
		
	}

}
