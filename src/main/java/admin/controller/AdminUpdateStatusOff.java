package admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class AdminUpdateStatusOff extends AbstractController {

	private MemberDAO mdao = null;
	
	public AdminUpdateStatusOff() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		String msg = "잘못된 접근입니다.";
		String loc = request.getContextPath() + "/member/admin/adminMemberDetail.wine";


		if(super.isDir(request.getSession())) {
			// 관리자(admin)로 로그인 했을 경우
		
			String userid = request.getParameter("userid");
			String goBackURL = request.getParameter("goBackURL");
			String memberidx = request.getParameter("memberidx");
			
			if("POST".equalsIgnoreCase(method)) {

				MemberDTO mdto = mdao.selectOneMember(userid);
				request.setAttribute("mdto", mdto);
				request.setAttribute("goBackURL", goBackURL);
				
				Map<String, String> paraMap = new HashMap<>();
				
				paraMap.put("userid", userid);
				paraMap.put("memberidx", memberidx);
				
				int adstatusOff = mdao.disableMember(paraMap);
				
				JSONObject jsobj = new JSONObject();
				jsobj.put("adstatusOff", adstatusOff);
				jsobj.put("memberidx", memberidx);
				
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
