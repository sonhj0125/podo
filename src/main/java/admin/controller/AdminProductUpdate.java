package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class AdminProductUpdate extends AbstractController {

    private ProductDAO pdao = null;
      
    public AdminProductUpdate() {
       pdao = new ProductDAO_imple();
    }
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			
			
		} else {
			
		}
		
//		super.setRedirect(false);
        super.setViewPage("/WEB-INF/member/admin/adminProductUpdate.jsp");
		
	}

}
