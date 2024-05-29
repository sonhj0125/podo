package admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;

public class UpdateRegisterOstatus extends AbstractController {

	CartDAO cdao = null;
	
	public UpdateRegisterOstatus() {
		cdao = new CartDAO_imple();
	}
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			HttpSession session = request.getSession();
			
			if(super.isDir(session)) {
				
				boolean result = false;
				
				String ostatus = request.getParameter("ostatus");
				String oindex = request.getParameter("oindex");
				
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("ostatus", ostatus);
				paraMap.put("oindex", oindex);
				
				if(cdao.registerostatus(paraMap)) {
					result = true;
				}
				
				JSONObject json = new JSONObject();
				json.put("result", result);
				String jsonStr = json.toString();
				
				request.setAttribute("json", jsonStr);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/jsonview.jsp");
				return;
			}
		
		}
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");
	
	}
	
}
