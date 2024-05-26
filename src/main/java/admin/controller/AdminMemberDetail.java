package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.LogDTO;
import member.domain.MemberDTO;
import member.model.LogDAO;
import member.model.LogDAO_imple;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class AdminMemberDetail extends AbstractController {

	private MemberDAO mdao = null;
	private CouponDAO codao = null;
	private LogDAO ldao = null;
	
	public AdminMemberDetail() {
		mdao = new MemberDAO_imple();
		codao = new CouponDAO_imple();
		ldao = new LogDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String method = request.getMethod();
		String msg = "잘못된 접근입니다.";
		String loc = request.getContextPath() + "/member/admin/adminMemberDetail.wine";

		if("POST".equalsIgnoreCase(method)) {
			// POST 방식일 때
		
			if(super.isDir(request.getSession())) {
				// 관리자(admin)로 로그인 했을 경우
			
				String userid = request.getParameter("userid");
				String goBackURL = request.getParameter("goBackURL");
				
				MemberDTO mdto = mdao.selectOneMember(userid);
				request.setAttribute("mdto", mdto);
				request.setAttribute("goBackURL", goBackURL);
				
				List<MyCouponDTO> mycodtoList = codao.getMyList(userid);
				request.setAttribute("mycodtoList", mycodtoList);
				
				List<LogDTO> ldtoList = ldao.getMyLog(userid);
				
			
				
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/admin/adminMemberDetail.jsp");
				
			} 
			
			
		}
		else {
			// 관리자(admin)로 로그인 하지 않은 경우 또는 로그인을 하지 않은 경우
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
			
		} // end of if("POST".equalsIgnoreCase(method)) 
		
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
