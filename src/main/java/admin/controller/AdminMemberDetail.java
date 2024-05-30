package admin.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import coupon.domain.CouponDTO;
import coupon.domain.MyCouponDTO;
import coupon.model.CouponDAO;
import coupon.model.CouponDAO_imple;
import member.domain.LogDTO;
import member.domain.MemberDTO;
import member.domain.PointDTO;
import member.model.LogDAO;
import member.model.LogDAO_imple;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import shop.domain.ReviewDTO;

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

		try {
			if(super.isDir(request.getSession())) {
				// 관리자(admin)로 로그인 했을 경우
			
				String userid = request.getParameter("userid");
				String goBackURL = request.getParameter("goBackURL");
				
				if(!"POST".equalsIgnoreCase(method)) {
					// GET 방식일때
					
					MemberDTO mdto = mdao.selectOneMember(userid);
					request.setAttribute("mdto", mdto);
					request.setAttribute("goBackURL", goBackURL);
					
					List<CouponDTO> codtoList = codao.adminCoupon();
					request.setAttribute("codtoList", codtoList);
					
					List<MyCouponDTO> mycodtoList = codao.getMyList(userid);
					request.setAttribute("mycodtoList", mycodtoList);
					
					List<LogDTO> ldtoList = ldao.getMyLog(userid);
					request.setAttribute("ldtoList", ldtoList);
					
					List<PointDTO> podtoList = mdao.getMyPointAdmin(userid);
					request.setAttribute("podtoList", podtoList);
					
					List<ReviewDTO> adminReviewList = mdao.getMyReview(userid);
					request.setAttribute("adminReviewList", adminReviewList);
					
					
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
		} catch(Exception e) {

			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/index.wine");
		}
		
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
