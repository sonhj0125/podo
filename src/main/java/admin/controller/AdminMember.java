package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;
import my.util.MyUtil;

public class AdminMember extends AbstractController {
	
	private MemberDAO mdao = null;
	
	public AdminMember() {
		mdao = new MemberDAO_imple();
	}	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.isDir(request.getSession())) {
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
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한 페이지 당 보여줄 행의 개수
			

			// **** 페이징 처리를 한 모든 회원 또는 검색한 회원 목록 보여주기 **** //
			
			
			// 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지 수 알아오기 //
			int totalPage = mdao.getTotalPage(paraMap);
			
			try {
				
				if(Integer.parseInt(currentShowPageNo) > totalPage ||
				   Integer.parseInt(currentShowPageNo) <= 0) {
					currentShowPageNo = "1";
					paraMap.put("currentShowPageNo", currentShowPageNo);
				}
				
			} catch(Exception e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
				
			} // end of try_catch
			
			String pageBar = "";
			
			int blockSize = 10;
			int loop = 1;
			int pageNo = ( (Integer.parseInt(currentShowPageNo) -1)/blockSize ) * blockSize + 1;
			
			// *** [맨처음][이전] 만들기 *** //
			pageBar += "<li class='page-item'><a class='page-link' href='adminMember.wine?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo=1'>◀◀</a></li>";
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='adminMember.wine?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>◀</a></li>";
			}
			
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if( pageNo == Integer.parseInt(currentShowPageNo) ) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
					
				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='adminMember.wine?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
				}
				
				loop++;		
				
				pageNo++;	
				
			} // end of while( !(loop > blockSize || pageNo > totalPage) )
			
			
			// *** [다음][마지막] 만들기 *** //
			
			if( pageNo <= totalPage ) {
				
				pageBar += "<li class='page-item'><a class='page-link' href='adminMember.wine?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>▶</a></li>";
			}
			
			pageBar += "<li class='page-item'><a class='page-link' href='adminMember.wine?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>▶▶</a></li>";
			// *** ==== 페이지바 만들기 끝 ==== *** //
			
			
			// *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
			String currentURL = MyUtil.getCurrentURL(request);
			// 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.
			
		
			List<MemberDTO> memberList = mdao.select_Member_paging(paraMap);
			
			request.setAttribute("memberList", memberList);
			
			if(searchType != null && 
			   ("name".equals(searchType) ||
				"userid".equals(searchType) ||
				"email".equals(searchType))) {
				
				request.setAttribute("searchType", searchType);
			}
			if(searchWord != null &&
			   !searchWord.trim().isEmpty()) {
					   
				request.setAttribute("searchWord", searchWord);
			}
					
			request.setAttribute("sizePerPage", sizePerPage);
			
			request.setAttribute("pageBar", pageBar);
			
			request.setAttribute("currentURL", currentURL);
			
			
			
			/* >>> 뷰단(memberList.jsp)에서 "페이징 처리 시 보여주는 순번 공식" 에서 사용하기 위해 
     	    검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 시작 <<< */
			int totalMemberCount = mdao.getTotalMemberCount(paraMap);

			request.setAttribute("totalMemberCount", totalMemberCount);
			request.setAttribute("currentShowPageNo", currentShowPageNo);
			
			/* 검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 끝 <<< */
			
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
