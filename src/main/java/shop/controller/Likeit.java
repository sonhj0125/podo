package shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class Likeit extends AbstractController {

	ProductDAO pdao = null;
	
	public Likeit() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String userid = request.getParameter("userid");
			String pindex = request.getParameter("pindex");
			
			String loc = request.getParameter("url");
			String msg = "";
			
			if(userid.isBlank() || userid==null) {
				
				msg = "로그인 후 이용 가능합니다.";
				
			}else {
			
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("userid", userid);
				paraMap.put("pindex", pindex);
				
				if(1==pdao.setLike(paraMap)) {
					
					super.setRedirect(true);
					super.setViewPage(loc);
					return;
									
				}else {
					
					msg = "오류";
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
