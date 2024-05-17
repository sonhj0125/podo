package login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import javax.servlet.http.HttpSession;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import util.gmail.GoogleMail;

public class PwdFindEmail extends AbstractController {

	private MemberDAO mdao = null;
	
	public PwdFindEmail() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) { // "POST" 방식인 경우
			// 비밀번호 찾기 모달 창에서 "찾기" 버튼을 클릭했을 경우

			String name = request.getParameter("name");
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("name", name);
			paraMap.put("userid", userid);
			paraMap.put("email", email);

			// 데이터베이스에 사용자가 존재하는지 알아오기
			boolean isUserExist = mdao.isUserExist(paraMap);
			
			////////////////////////////////////////////////
			boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도

			if(isUserExist) { // 회원이 존재하는 경우

				// 인증키를 랜덤하게 생성하도록 한다.
				Random rnd = new Random();

				String certification_code = "";
				// 인증키는 영문소문자 5글자 + 숫자 7글자 로 만들겠습니다.

				char randchar = ' ';
				for (int i = 0; i < 5; i++) {
				/*
					min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면
					int rndnum = rnd.nextInt(max - min + 1) + min;
					영문 대문자 'A' 부터 'Z' 까지 랜덤하게 1개를 만든다.
				*/
					randchar = (char) (rnd.nextInt('Z' - 'A' + 1) + 'A');
					certification_code += randchar;
					
				} // end of for ---------------------

				// 랜덤하게 생성한 인증코드(certification_code)를 비밀번호 찾기를 하고자 하는 사용자의 email 로 전송시킨다.
				GoogleMail mail = new GoogleMail();
				
				try {
					mail.send_certification_code(email, certification_code);
					sendMailSuccess = true;
					
					// 세션 불러오기
					HttpSession session = request.getSession();
					session.setAttribute("certification_code", certification_code);
					
				} catch (Exception e) {
					// 메일 전송이 실패한 경우
					e.printStackTrace();
				}
				
			} // end of if(isUserExist) ---------------

			request.setAttribute("isUserExist", isUserExist);
			request.setAttribute("sendMailSuccess", sendMailSuccess);
			request.setAttribute("name", name);
			request.setAttribute("userid", userid);
			request.setAttribute("email", email);
			
		} // end of if("POST".equalsIgnoreCase(method)) ----------- 
		
		request.setAttribute("method", method);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/login/pwdFindEmail.jsp");
		
		
	}

}
