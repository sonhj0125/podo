package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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


		if(super.isDir(request.getSession())) {
			// 관리자(admin)로 로그인 했을 경우
		
			String userid = request.getParameter("userid");
			String goBackURL = request.getParameter("goBackURL");
			String coname = request.getParameter("coname");
			String memberidx = request.getParameter("memberidx");
			
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
				
				List<PointDTO> podtoList = mdao.getMyPoint(userid);
				request.setAttribute("podtoList", podtoList);
				
				List<ReviewDTO> adminReviewList = mdao.getMyReview(userid);
				request.setAttribute("adminReviewList", adminReviewList);
				
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/admin/adminMemberDetail.jsp");
				
			} 
			else {
				// POST 방식일때
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("userid", userid);
				paraMap.put("coname", coname);
				
				int admycodtoList = codao.adminCouponIn(paraMap);
			
				paraMap.put("userid", userid);
				paraMap.put("memberidx", memberidx);
				
				int adstatusOff = mdao.disableMember(paraMap);
				int adstatusOn = mdao.ableMember(paraMap);

				JSONObject jsobj = new JSONObject();
				jsobj.put("admycodtoList", admycodtoList);	
				jsobj.put("adstatusOff", adstatusOff);
				jsobj.put("adstatusOn", adstatusOn);
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
			
			
		} // end of if("POST".equalsIgnoreCase(method)) 
		
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
