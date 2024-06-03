package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;

public class Chart extends AbstractController {

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
	         
	         super.setRedirect(false);
	         super.setViewPage("/WEB-INF/msg.jsp");
	         return; // 종료
	      }
	      
	      else { // 관리자가 제품 판매량 통계를 보려는 경우

	         super.setRedirect(false);
	         super.setViewPage("/WEB-INF/member/admin/chart.jsp");
	      }
	      
	}

}
