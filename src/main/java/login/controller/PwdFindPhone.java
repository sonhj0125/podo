package login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.controller.AbstractController;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import net.nurigo.java_sdk.api.Message;
import util.gmail.GoogleMail;

public class PwdFindPhone extends AbstractController {

	private MemberDAO mdao = null;
	
	public PwdFindPhone() {
		mdao = new MemberDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) { // "POST" 방식인 경우
			// 비밀번호 찾기 모달 창에서 "찾기" 버튼을 클릭했을 경우

			String name = request.getParameter("name");
			String userid = request.getParameter("userid");
			String phone = request.getParameter("phone");

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("name", name);
			paraMap.put("userid", userid);
			paraMap.put("phone", phone);

			// 데이터베이스에 사용자가 존재하는지 알아오기
			boolean isUserExist = mdao.isUserExist(paraMap);
			
			System.out.println("isUserExist => " + isUserExist);
			
			////////////////////////////////////////////////
			boolean sendSmsSuccess = false; // 문자가 정상적으로 전송되었는지 유무를 알아오기 위한 용도

			if(isUserExist) { // 회원이 존재하는 경우

				// 인증키를 랜덤하게 생성하도록 한다.
				Random rnd = new Random();

				String certification_code = "";
				// 인증키는 영문 대문자 5글자로 생성

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

				System.out.println("certification_code => " + certification_code);

				// >> SMS발송 <<
				// HashMap 에 받는사람번호, 보내는사람번호, 문자내용 등 을 저장한뒤 Coolsms 클래스의 send를 이용해 보냅니다.

				// String api_key = "발급받은 본인의 API Key";  // 발급받은 본인 API Key
				String api_key = "NCS3R0D49U5CD3TD";

				// String api_secret = "발급받은 본인의 API Secret";  // 발급받은 본인 API Secret 
				String api_secret = "QP9YUCN7FERRQJRMAKUGKUMZSSYXTUXG";

				Message coolsms = new Message(api_key, api_secret);
				// net.nurigo.java_sdk.api.Message 임.
				// 먼저 다운 받은 javaSDK-2.2.jar 와 json-simple-1.1.1.jar 를
				// /MyMVC/src/main/webapp/WEB-INF/lib/ 안에 넣어서 build 시켜야 함.

				String smsContent = "비밀번호찾기 인증코드는 [" + certification_code + "] 입니다.";

				// == 4개 파라미터(to, from, type, text)는 필수사항이다. ==
				HashMap<String, String> smsMap = new HashMap<>();
				smsMap.put("to", phone); // 수신번호
				smsMap.put("from", "01042642722"); // 발신번호
				// 2020년 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
				smsMap.put("type", "SMS"); // Message type ( SMS(단문), LMS(장문), MMS, ATA )
				smsMap.put("text", smsContent); // 문자내용

				smsMap.put("app_version", "JAVA SDK v2.2"); // application name and version

				// == 아래의 파라미터는 필요에 따라 사용하는 선택사항이다. ==
				// paraMap.put("mode", "test"); // 'test' 모드. 실제로 발송되지 않으며 전송내역에 60 오류코드로 뜹니다.
				// 차감된 캐쉬는 다음날 새벽에 충전 됩니다.
				// paraMap.put("image", "desert.jpg"); // image for MMS. type must be set as
				// "MMS"
				// paraMap.put("image_encoding", "binary"); // image encoding binary(default),
				// base64
				// paraMap.put("delay", "10"); // 0~20사이의 값으로 전송지연 시간을 줄 수 있습니다.
				// paraMap.put("force_sms", "true"); // 푸시 및 알림톡 이용시에도 강제로 SMS로 발송되도록 할 수 있습니다.
				// paraMap.put("refname", ""); // Reference name
				// paraMap.put("country", "KR"); // Korea(KR) Japan(JP) America(USA) China(CN)
				// Default is Korea
				// paraMap.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // 알림톡
				// 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
				// paraMap.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은
				// http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요.
				// paraMap.put("datetime", "20210106153000"); // Format must be(YYYYMMDDHHMISS)
				// 2021 01 06 15 30 00 (2021 Jan 06th 3pm 30 00)
				// paraMap.put("mid", "mymsgid01"); // set message id. Server creates
				// automatically if empty
				// paraMap.put("gid", "mymsg_group_id01"); // set group id. Server creates
				// automatically if empty
				// paraMap.put("subject", "Message Title"); // set msg title for LMS and MMS
				// paraMap.put("charset", "euckr"); // For Korean language, set euckr or utf-8
				// paraMap.put("app_version", "Purplebook 4.1") // 어플리케이션 버전
				
				JSONObject jsobj = (JSONObject) coolsms.send(smsMap);
				/*
				 * org.json.JSONObject 이 아니라 org.json.simple.JSONObject 이어야 한다.
				 */
				
				System.out.println("json => " + jsobj.toString());
				
				if(jsobj.containsKey("success_count")) {
					int successCount = ((Long)jsobj.get("success_count")).intValue();
					
				    if (successCount == 1) {
				    	
				        sendSmsSuccess = true;
				        
				        // 세션 불러오기
						HttpSession session = request.getSession();
						session.setAttribute("certification_code", certification_code);
				    }
				}
				
				System.out.println("sendSmsSuccess => " + sendSmsSuccess);
				
			} // end of if(isUserExist) ---------------

			request.setAttribute("isUserExist", isUserExist);
			request.setAttribute("sendSmsSuccess", sendSmsSuccess);
			request.setAttribute("name", name);
			request.setAttribute("userid", userid);
			request.setAttribute("phone", phone);
			
		} // end of if("POST".equalsIgnoreCase(method)) ----------- 
		
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/login/pwdFindPhone.jsp");
	}

}
