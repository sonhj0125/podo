package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.domain.CartDTO;
import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;

public class Order extends AbstractController {

	CartDAO cdao = null;
	
	public Order() {
		cdao = new CartDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			
			String cindexArr = request.getParameter("Arr_cindexOne");
			String cvolumeArr = request.getParameter("Arr_cvolumeOne");
			
			boolean nullFlag = false;
			
			try {
				if(cindexArr.isBlank()) {
					nullFlag = true;
				}
			}catch (NullPointerException e) {
				nullFlag = true;
			}
			
			if(nullFlag) {
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.wine");
				return;
			}
			
			String cindex[] = cindexArr.split("[,]");
			String volumeArr[] = cvolumeArr.split("[,]");
			
			boolean nextStep = true;
			
			for(int i=0; i<cindex.length;i++) {
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("cindex", cindex[i]);
				paraMap.put("cvolume", volumeArr[i]);
				
				if(cdao.modifyVolume(paraMap)!=1) {
					nextStep = false;
				}
				
			}
			
			if(nextStep) { // 정상
				
				List<CartDTO> cdtoList = cdao.getList(cindexArr);
				
				request.setAttribute("cdtoList", cdtoList);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/shop/order.jsp");
				
			}else { // 오류
				
				request.setAttribute("msg", "알수없는 오류 발생");
				request.setAttribute("loc", request.getContextPath()+"/index.wine");
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/msg.jsp");
				
			}
			
		}else {
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
			
		}
		
		

	}

}