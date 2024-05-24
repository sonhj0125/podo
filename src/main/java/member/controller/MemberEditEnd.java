package member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class MemberEditEnd extends AbstractController {

	private MemberDAO mdao = null;
	
	public MemberEditEnd() {
		mdao = new MemberDAO_imple();
	}	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("POST".equalsIgnoreCase(method)) {
           // **** POST 방식으로 넘어온 것이라면 **** //
		
		   String userid = request.getParameter("userid");	
		   String name = request.getParameter("name");
		   String email = request.getParameter("email");
    	   String phone = request.getParameter("phone");
		   String address = request.getParameter("address");
		   String addressDetail = request.getParameter("addressDetail");
			
		   MemberDTO member = new MemberDTO();
		   member.setUserid(userid);
		   member.setName(name);
		   member.setEmail(email);
		   member.setPhone(phone);
		   member.setAddress(address);
		   member.setAddressDetail(addressDetail);
			
		   // === 회원수정이 성공되어지면 "회원정보 수정 성공!!" 이라는 alert 를 띄우고 시작페이지로 이동한다. === //
		   String msg = "";
		   String loc = "";
			
			try {
				
				int n = mdao.updateMember(member); 
				
				if(n==1) {
					
					// !!!! session 에 저장된 loginuser 를 변경된 사용자의 정보값으로 변경해주어야 한다. !!!!
					HttpSession session = request.getSession();
					MemberDTO loginuser = (MemberDTO) session.getAttribute("loginUser");
					
					loginuser.setName(name);
					loginuser.setEmail(email);
					loginuser.setPhone(phone);
					loginuser.setAddress(address);
					loginuser.setAddressDetail(addressDetail);
					
					msg = "회원정보 수정 성공!";
					loc = request.getContextPath()+"/index.wine"; // 시작페이지로 이동한다.
				}
				
			} catch(SQLException e) {
				msg = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것. 
				e.printStackTrace();
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(false); 
			super.setViewPage("/WEB-INF/msg.jsp");
		}		
		
	} 
	

}
