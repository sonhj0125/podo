package cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;

public class Cartoutarr extends AbstractController {

	CartDAO cdao = null;
	
	public Cartoutarr() {
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String msg = "장바구니에서 삭제하였습니다.";
			String loc = request.getContextPath()+"/cart/cart.wine";
			
			String strcindex = request.getParameter("Arr_cindexOne");
			
			String[] arrcindex = strcindex.split("[,]");
			
			for(int i=0;i<arrcindex.length;i++) {
				
				String cindex = arrcindex[i];
				
				if(1 != cdao.deleteCartfromindex(cindex)) {
					msg = "오류발생";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/msg.jsp");
					break;
				}
				
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		}else {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
		}
		
	}

	
	
}
