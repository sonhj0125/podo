package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import shop.domain.OrderDTO;

public class AdminOrder extends AbstractController {

	CartDAO cdao = null;
	
	public AdminOrder() {
		cdao = new CartDAO_imple();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(super.isDir(session)) { // 관리자만 접근가능
			
			String searchWord = request.getParameter("searchWord");
			String searchType = request.getParameter("searchType");
			String navNum = request.getParameter("navNum");
			
			if(searchType == null || (!"1".equals(searchType) && !"2".equals(searchType)
					&& !"3".equals(searchType) && !"4".equals(searchType))) {
				searchType="0";
			}
			
			if(navNum == null) {
				navNum = "1";
			}
			
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("searchWord", searchWord);
			paraMap.put("searchType", searchType);
			paraMap.put("navNum", navNum);
			
			int totalPage = cdao.getTotalPage(paraMap);

			try {
				
				if(Integer.parseInt(navNum) > totalPage ||
				   Integer.parseInt(navNum) <= 0) {
					navNum = "1";
					paraMap.put("navNum", navNum);
				}
				
			} catch(Exception e) {
				navNum = "1";
				paraMap.put("navNum", navNum);
			}
			
			String pageBar = "";
			int blockSize = 10;
			int loop = 1;
			int pageNo = ( (Integer.parseInt(navNum) -1)/blockSize ) * blockSize + 1;
			
			pageBar += "<li class='page-item'><a class='page-link' href='adminOrder.wine?searchType="+searchType+"&searchWord="+searchWord+"&navNum=1'>◀◀</a></li>";
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='adminOrder.wine?searchType="+searchType+"&searchWord="+searchWord+"&navNum="+(pageNo-1)+"'>◀</a></li>";
			}
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if( pageNo == Integer.parseInt(navNum) ) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
					
				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='adminOrder.wine?searchType="+searchType+"&searchWord="+searchWord+"&navNum="+pageNo+"'>"+pageNo+"</a></li>";
				}
				loop++;		
				pageNo++;	
			}
			
			if( pageNo <= totalPage ) {
				
				pageBar += "<li class='page-item'><a class='page-link' href='adminOrder.wine?searchType="+searchType+"&searchWord="+searchWord+"&navNum="+pageNo+"'>▶</a></li>";
			}
			
			pageBar += "<li class='page-item'><a class='page-link' href='adminOrder.wine?searchType="+searchType+"&searchWord="+searchWord+"&navNum="+totalPage+"'>▶▶</a></li>";
			
			List<OrderDTO> odtoList = cdao.selectOrderPaging(paraMap);
			
			request.setAttribute("odtoList", odtoList);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("searchWord", searchWord);
			request.setAttribute("searchType", searchType);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/adminOrder.jsp");
			return;
		}
		
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/index.wine");
		
	}

}
