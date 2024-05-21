package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;

public class MemberEdit extends AbstractController {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
	      // 내정보(회원정보)를 수정하기 위한 전제조건은 먼저 로그인을 해야 하는 것이다. 
	      if(super.checkLogin(request)) {
	         // 로그인을 했으면 
	         
	         String userid = request.getParameter("userid");
	         
	         HttpSession session = request.getSession();
	         MemberDTO loginuser = (MemberDTO) session.getAttribute("loginUser"); 
	         
	         if(loginuser.getUserid().equals(userid)) {
	            // 로그인한 사용자가 자신의 정보를 수정하는 경우 
	            
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/member/myPage/memberEdit.jsp");
	            
	         }
	         else {
	            // 로그인한 사용자가 다른 사용자의 정보를 수정하려고 시도하는 경우 
	            String msg = "다른 사용자의 정보 변경은 불가합니다!!";
	            String loc = "javascript:history.back()";
	            
	            request.setAttribute("msg", msg);
	            request.setAttribute("loc", loc);
	            
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/msg.jsp");
	         }
	         
	      }
	      
	      else {
	         // 로그인을 안했으면 
	         String msg = "회원정보를 수정하기 위해서는 먼저 로그인을 하세요!!";
	         String loc = "/WEB-INF/login/login.jsp";
	         
	         request.setAttribute("msg", msg);
	         request.setAttribute("loc", loc);
	         
	      //   super.setRedirect(false);
	         super.setViewPage("/WEB-INF/msg.jsp");
	      }
	      
	   }

}
