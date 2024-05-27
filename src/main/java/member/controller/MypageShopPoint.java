package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.domain.PointDTO;
import member.model.PointDAO;
import member.model.PointDAO_imple;

public class MypageShopPoint extends AbstractController {

	PointDAO podao = null;

	public MypageShopPoint() {
		podao = new PointDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();

		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		
		if (loginUser != null) {
			// 로그인을 했을 경우

			request.setAttribute("loginUser", loginUser);
			
			String userid = loginUser.getUserid();
			request.setAttribute("userid", userid);

			// 사용가능 적립금, 누적 적립금, 사용한 적립금(합쳐서 한개)
			PointDTO podto = podao.getUserPointDetails(userid);
			
			// 유저가 적립한 포인트 로그 가져오기
			List<PointDTO> pointHistoryList = podao.getUserPointHistoryList(userid);
			
			/*
			// 유저가 적립한 포인트 로그 가져오기
			List<PointDTO> pointHistoryList = podao.getUserPointHistoryList(userid);

			// 유저가 사용한 포인트 로그 가져오기
			List<PointDTO> pointUsedHistoryList = podao.getUserPointUsedHistoryList(userid);
			*/
			
			/*
			// 소멸예정 적립금 리스트 가져오기 (소멸예정 적립금, 날짜, 남은일자)
			List<PointDTO> expiryList = podao.getExpiryDetailsList(userid);
			*/
			
			/*
			// 적립내역 리스트 가져오기(poStatus(0,1)) + 사용내역 리스트 가져오기(2)
			List<PointDTO> pointHistoryList = podao.getUserPointHistoryList(userid);
			
			int expiringPoints = 0;
			if(expiryList != null) {
				for (PointDTO expiringPointDTO : expiryList) {
					expiringPoints += Integer.parseInt(expiringPointDTO.getExpiringPoints());
				}
			}
			
			boolean hasPoStatus2 = false; // 사용기록이 있는지 없는지 확인하기 위해 만듦
			if(pointHistoryList != null) {
				for(PointDTO PoStatus2 : pointHistoryList) {
					if(PoStatus2.getPoStatus().equals("2")) {
						// PoStatus2 값이 있을 경우
						hasPoStatus2 = true;
						break;
					}
				}
			}
			request.setAttribute("expiryList", expiryList);
			request.setAttribute("expiringPoints", expiringPoints);
			request.setAttribute("pointHistoryList", pointHistoryList);
			request.setAttribute("hasPoStatus2", hasPoStatus2);
			
			request.setAttribute("pointUsedHistoryList", pointUsedHistoryList);
			*/
			
			request.setAttribute("podto", podto);
			request.setAttribute("pointHistoryList", pointHistoryList);
			

			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/mypageShopPoint.jsp");
			
		} else {
			// 마이페이지에서 로그아웃했을 경우 아닐경우 홈페이지로 이동
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/index.wine");
			return;
		}

	}

}