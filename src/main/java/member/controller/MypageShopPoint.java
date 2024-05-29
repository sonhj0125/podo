package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.domain.PointDTO;
import member.model.PointDAO;
import member.model.PointDAO_imple;
import my.util.MyUtil;

public class MypageShopPoint extends AbstractController {

	PointDAO podao = null;

	public MypageShopPoint() {
		podao = new PointDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		
		if (loginUser != null) {
			// 로그인을 했을 경우

			request.setAttribute("loginUser", loginUser);
			
			String userid = loginUser.getUserid();

            // 현재 페이지 번호
            String currentShowPageNo = request.getParameter("currentShowPageNo");
            // 페이지당 보여줄 쿠폰 개수
            String sizePerPage = "10";

            // 총 페이지 수
            int totalPage = podao.getTotalPage(userid);
			if(currentShowPageNo == null) {
				currentShowPageNo = "1";
			}
			
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("userid", userid);
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수 
			
			try {
				 if( Integer.parseInt(currentShowPageNo) > totalPage || 
					 Integer.parseInt(currentShowPageNo) <= 0 ) {
					 currentShowPageNo = "1";
					 paraMap.put("currentShowPageNo", currentShowPageNo);
				 }
			} catch(NumberFormatException e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}
			
			
			String pageBar = "";  
			
			int blockSize = 10;
			// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.
			
			int loop = 1;
			// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다. 
			
			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
			int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1; 
			// pageNo 는 페이지바에서 보여지는 첫번째 번호이다.
			
			// *** [맨처음][이전] 만들기 *** //
			pageBar += "<li class='page-item'><a class='page-link' href='mypageShopPoint.wine?sizePerPage="+sizePerPage+"&currentShowPageNo=1'>[맨처음]</a></li>"; 
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='mypageShopPoint.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>"; 
			}
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if(pageNo == Integer.parseInt(currentShowPageNo)) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>"; 
				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='mypageShopPoint.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>"; 
				}
				
				loop++;    // 1 2 3 4 5 6 7 8 9 10
				
				pageNo++;  //  1  2  3  4  5  6  7  8  9 10
				           // 11 12 13 14 15 16 17 18 19 20
				           // 21 22 23 24 25 26 27 28 29 30
				           // 31 32 33 34 35 36 37 38 39 40
				           // 41 42 
				            
			}// end of while( !( ) )--------
			
			// *** [다음][마지막] 만들기 *** //
			// pageNo ==> 11
			
			if(pageNo <= totalPage) { 
				pageBar += "<li class='page-item'><a class='page-link' href='mypageShopPoint.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			}
			pageBar += "<li class='page-item'><a class='page-link' href='mypageShopPoint.wine?sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
			
			
			// *** ======= 페이지바 만들기 끝 ======= *** //	        
	        
			
			
			// *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
			String currentURL = MyUtil.getCurrentURL(request);
			// 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.
			
            // **** 페이징 처리를 한 모든 포인트 목록 보여주기 **** //
            List<PointDTO> myPointpaging = podao.selectMyPointpaging(paraMap);
			
            
			request.setAttribute("myPointpaging", myPointpaging);
			request.setAttribute("sizePerPage", sizePerPage);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("currentURL", currentURL);
			
            // 총 포인트 개수
            int totalMyPointCount = podao.getTotalMyPointCount(userid);
            
            request.setAttribute("totalMyPointCount", totalMyPointCount);
            request.setAttribute("currentShowPageNo", currentShowPageNo);
            
            // 지금 포인트 사용할 때 어떤 값이 오는지 몰라서 주석처리해뒀습니다.
	        
            
            
			// 사용가능포인트, 사용한포인트 , 총포인트
            PointDTO userpointdto = podao.getUserPointDetails(userid);
            if(userpointdto.getAvailablePoints() == null) {
            	userpointdto.setAvailablePoints("0");
            }
            if(userpointdto.getUsedPoints() == null) {
            	userpointdto.setUsedPoints("0");
            }
            if(userpointdto.getTotalPoints() == null && userpointdto.getAvailablePoints() != null && userpointdto.getUsedPoints() != null) {
            	int AvailablePoints = Integer.parseInt(userpointdto.getAvailablePoints());
            	int UsedPoints = Integer.parseInt(userpointdto.getUsedPoints());
            	int TotalPoints = AvailablePoints + UsedPoints;
            	userpointdto.setTotalPoints(Integer.toString(TotalPoints));
            }
			
            request.setAttribute("userpointdto", userpointdto);
            
            
			/*
			 * int totalCoupon = 0; int usedCoupon = 0; int availableCoupons = 0;
			 * if(pointHistoryList.size() != 0) { totalCoupon = pointHistoryList.size(); //
			 * 총 쿠폰 발행 수
			 * 
			 * for (PointDTO podto : pointHistoryList) { if(podto.getCostatus() == 2) {
			 * usedCoupon++; // 사용한 쿠폰 수 } if(podto.getCostatus() == 1) {
			 * availableCoupons++; // 가용 쿠폰 수 } } }
			 * 
			 * 
			 * request.setAttribute("totalCoupon", totalCoupon);
			 * request.setAttribute("usedCoupon", usedCoupon);
			 * request.setAttribute("availableCoupons", availableCoupons);
			 */

			
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