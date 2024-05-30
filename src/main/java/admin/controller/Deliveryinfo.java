package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.domain.DeliveryDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;

public class Deliveryinfo extends AbstractController{
	
	CartDAO cdao = null;

	public Deliveryinfo() {
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(super.isDir(session)) {
			
			String oindexStr = request.getParameter("oindex");
			int oindex = 0;
			
			try {
				oindex = Integer.parseInt(oindexStr);
			}catch (Exception e) {
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.wine");
			}
			
			DeliveryDTO ddto = cdao.getOrderDetailAdmin(oindex);
			
			request.setAttribute("ddto", ddto);
			request.setAttribute("oindex", oindex);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/deliveryinfo.jsp");
			return;
		}
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");
		
	}

}
