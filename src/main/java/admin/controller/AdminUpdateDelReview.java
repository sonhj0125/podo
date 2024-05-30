package admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class AdminUpdateDelReview extends AbstractController {
	
	private ProductDAO pdao = null;
	private MemberDAO mdao = null;
	
	public AdminUpdateDelReview() {
		pdao = new ProductDAO_imple();
		mdao = new MemberDAO_imple();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		String msg = "잘못된 접근입니다.";
		String loc = request.getContextPath() + "/member/admin/adminMemberDetail.wine";
		
		if(super.isDir(request.getSession())) {
			// 관리자(admin)로 로그인 했을 경우
		
			String goBackURL = request.getParameter("goBackURL");
			String rindex = request.getParameter("rindex");
			
			if("POST".equalsIgnoreCase(method)) {

				request.setAttribute("goBackURL", goBackURL);
				
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("rindex", rindex);
				
				
				
				System.out.println(rindex);
				
				
				
				
				int delReviewAd = pdao.delReviewAd(rindex);
				
				JSONObject jsobj = new JSONObject();
				jsobj.put("delReviewAd", delReviewAd);
				
				String json = jsobj.toString();
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/jsonview.jsp");
				
				
			}
			
			
		}
		else {
			// 관리자(admin)로 로그인 하지 않은 경우 또는 로그인을 하지 않은 경우
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
			
		} // end of if(super.isDir(request.getSession()))
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
