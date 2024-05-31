package admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import cart.model.CartDAO;
import cart.model.CartDAO_imple;
import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ChartJSON extends AbstractController {
	
	ProductDAO pdao = null;
	
	public ChartJSON() {
		pdao = new ProductDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// === 로그인 유무 검사하기 === //
		  HttpSession session =  request.getSession();
	      MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
	      
	      int memberIndex = Integer.parseInt(loginUser.getMemberIdx());
	      
	      if(!(loginUser != null && memberIndex == 9)){
	         // 관리자가 아닌 회원이 제품 판매량 통계를 보려는 경우
	         
	         request.setAttribute("msg", "제품 판매량 통계는 관리자만 열람할 수 있습니다!");
	         request.setAttribute("loc", "javascript:history.back()"); 
	         
	         // super.setRedirect(false);
	         super.setViewPage("/WEB-INF/msg.jsp");
	         return; // 종료
	      }
	      else {
	    	  // 관리자로 로그인 한 경우
	    	  
	    	  List<Map<String, String>> chart_map_List = pdao.chart_map_List();
	    	  
	    	  JSONArray json_arr = new JSONArray();
	    	  
	    	  if(chart_map_List.size() > 0) {
	    		  
	    		  for(Map<String, String> map : chart_map_List) {
		    		  JSONObject json_obj = new JSONObject();
		    		  json_obj.put("ptype", map.get("ptype"));
		    		  json_obj.put("ordersum", map.get("ordersum"));
		    		  
		    		  json_arr.put(json_obj);
		    		  
	    		  }// end of for()-----------------------
	    	  
	    	  }// end of if()-------------------------------------
	    	  
	    	  request.setAttribute("json", json_arr.toString() );
	          
              // super.setRedirect(false);
              super.setViewPage("/WEB-INF/jsonview.jsp");
	      }

	}

}
