package login.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Signin extends AbstractController {

	MemberDAO mdao = null;
	
	public Signin() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String msg = "";
		
		try {
			
			String method = request.getMethod();
			String location = request.getContextPath()+"/index.wine";
			
			if("POST".equalsIgnoreCase(method)) {
				
				Map<String,String> paraMap = new HashMap<>();
				
				paraMap.put("userid", request.getParameter("userid"));
				paraMap.put("pwd", request.getParameter("pwd"));
				
				MemberDTO loginUser = mdao.signin(paraMap);
				
				if(loginUser != null) { // id 있음
					
					paraMap.put("clientip", request.getRemoteAddr());
					
					if(mdao.logwrite(paraMap)==1) { // Log 입력 정상
						
						HttpSession session = request.getSession();
						session.setAttribute("loginUser", loginUser);
						
						int reviewCnt = mdao.getReviewCnt(request.getParameter("userid"));
						session.setAttribute("reviewCnt", reviewCnt);
						
						int dayDiff = 0;
						
						if(loginUser.getPwdUpdateDay() == null) { // pw 변경이력 X
							
							Date now = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
							String nowStr = sdf.format(now);
							String updatedayStr = loginUser.getRegisterDay();
							
							
							String[] nowArr = nowStr.split("[-]");
							String[] updatedayArr = updatedayStr.split("[-]");
							
							LocalDate date1 = LocalDate.of(Integer.parseInt(nowArr[0]) , Integer.parseInt(nowArr[1]), Integer.parseInt(nowArr[2]));
					        LocalDate date2 = LocalDate.of(Integer.parseInt(updatedayArr[0]), Integer.parseInt(updatedayArr[1]), Integer.parseInt(updatedayArr[2]));
					        
					        Period period = Period.between(date2, date1);
					        
					        dayDiff = period.getDays();
						     
					        
						}else { // pw 변경이력 O
							
							Date now = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
							String nowStr = sdf.format(now);
							String updatedayStr = loginUser.getPwdUpdateDay();
							
							String[] nowArr = nowStr.split("[-]");
							String[] updatedayArr = updatedayStr.split("[-]");
							
							try {
							
								LocalDate date1 = LocalDate.of(Integer.parseInt(nowArr[0]) , Integer.parseInt(nowArr[1]), Integer.parseInt(nowArr[2]));
						        LocalDate date2 = LocalDate.of(Integer.parseInt(updatedayArr[0]), Integer.parseInt(updatedayArr[1]), Integer.parseInt(updatedayArr[2]));
						        
						        Period period = Period.between(date1, date2);
						        
						        dayDiff = period.getDays();
						        
							}catch(Exception e) {
								e.printStackTrace();
							}
							
						}
						
						if(dayDiff>90) { // 비밀번호 변경한지 90일 초과한 경우
							
							msg = "비밀번호를 변경한 지 90일이 초과되었습니다.";
							
						}else { // 비밀번호 변경한지 90일 이내인 경우
							
							String auto = request.getParameter("auto");
							
							if("auto".equalsIgnoreCase(auto)) {
								super.setRedirect(true);
								super.setViewPage(request.getContextPath()+"/index.wine");
								return;
							}
							
							super.setRedirect(false);
							super.setViewPage("/WEB-INF/index.jsp");
							return;
						}
						
					}else { // Log 입력 비정상
						msg = "로그인 오류발생 관리자에게 문의해주세요.";
					}
				}else { // id 없음
					msg = "없는 아이디거나 틀린 비밀번호입니다.";
				}
				
			}
			
			request.setAttribute("msg",msg);
			request.setAttribute("loc", location);
				
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
