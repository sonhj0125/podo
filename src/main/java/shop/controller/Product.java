package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class Product extends AbstractController {

	ProductDAO pdao = null;
	
	public Product(){
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String index = request.getParameter("pindex");
		
		try {
			
			int pindex = Integer.parseInt(index);
			
			ProductDTO pdto = pdao.getproduct(pindex);
			
			if(pdto != null) {
				
				request.setAttribute("pdto", pdto);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/showproduct.jsp");
				
			}else {
				
				// 잘못된 idx값을 넣은경우
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/showproduct.jsp");
			}
			
			
			
		}catch (Exception e) {
			
			// 이전 화면으로 돌리기
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/shop/showproduct.jsp");
			
		}
		
		
	}

}
