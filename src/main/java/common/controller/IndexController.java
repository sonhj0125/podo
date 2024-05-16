package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class IndexController extends AbstractController {

	ProductDAO pdao = null;
	
	public IndexController() {
		
		pdao = new ProductDAO_imple();
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
		List<ProductDTO> pdtoList = pdao.listReadDesc();
		
		request.setAttribute("newProductList", pdtoList);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/index.jsp");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
