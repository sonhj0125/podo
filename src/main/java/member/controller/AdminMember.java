package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class AdminMember extends AbstractController {
	
	private MemberDAO mdao = null;
	
	public AdminMember() {
		mdao = new MemberDAO_imple();
	}	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// === 관리자(admin)로 로그인 했을 때만 회원조회 === //
		HttpSession session = request.getSession();
		
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(loginUser != null && "ejss0125".equals(loginUser.getUserid())) {
			// 관리자(admin)로 로그인 했을 경우
			
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String sizePerPage = request.getParameter("sizePerPage");
			String currentShowPageNo = request.getParameter("currentShowPageNo");
			
			if(searchType == null || 
			   (!"name".equals(searchType) &&
				!"userid".equals(searchType) &&
				!"email".equals(searchType))) {
				
				searchType = "";
			}
			
			if(searchWord == null ||
			   (searchWord != null && searchWord.trim().isEmpty())) {
										
				searchWord = "";
			}
			
			if(sizePerPage == null  ||
			   (!"10".equals(sizePerPage) &&
				!"5".equals(sizePerPage) &&
				!"3".equals(sizePerPage))) {
				
				sizePerPage = "10";
			}
			
			if(currentShowPageNo == null) {
				currentShowPageNo = "1";
			}
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("searchType", searchType);
			paraMap.put("searchWord", searchWord);
			
			// **** 페이징 처리를 하지 않은 모든 회원 또는 검색한 회원 목록 보여주기 **** //
			List<MemberDTO> memberList = mdao.select_Member_nopaging(paraMap);
			
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한 페이지 당 보여줄 행의 개수
			

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/adminMember.jsp");

			
		}
		else {
			// 로그인 하지 않거나, 관리자가 아닌 경우
			String message = "관리자만 접근이 가능합니다.";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
		} // end of if(loginuser != null && "ejss0125".equals(loginuser.getUserid()))
		
		
		
	} // end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception

}
