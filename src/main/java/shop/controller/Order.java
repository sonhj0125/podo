package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;

public class Order extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String cvloumeArr = request.getParameter("")
			
		}
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/shop/order.jsp");

	}

}