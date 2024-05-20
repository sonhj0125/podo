package shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class Unlikeit extends AbstractController{

	ProductDAO pdao = null;
	
	public Unlikeit() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("post".equalsIgnoreCase(method)) {
			
			String userid = request.getParameter("userid");
			
			String msg = "";
			String loc = request.getParameter("url");
			
			if(userid.isBlank() || userid == null) {
				
				msg = "로그인후 이용가능합니다.";
				
			}else {
				
				Map<String,String> paraMap = new HashMap<>();
				
				String pindex = request.getParameter("pindex");
				
				paraMap.put("userid", userid);
				paraMap.put("pindex", pindex);
				
				if(1==pdao.setunlike(paraMap)) {
					msg ="좋아요가 취소되었습니다.";
				}else {
					msg = "오류가 발생하였습니다.";
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
