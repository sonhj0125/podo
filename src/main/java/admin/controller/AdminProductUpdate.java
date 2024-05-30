package admin.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class AdminProductUpdate extends AbstractController {

    private ProductDAO pdao = null;
      
    public AdminProductUpdate() {
       pdao = new ProductDAO_imple();
    }
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(super.checkLogin(request) && "9".equals(loginUser.getMemberIdx())) {
			// 관리자로 로그인 한 경우
			

			String sortType = request.getParameter("sortType");		
			
			String[] ptype_arr = request.getParameterValues("ptype");
			String pprice = request.getParameter("pprice");
			String[] phometown_arr = request.getParameterValues("phometown");
			String pbody = request.getParameter("pbody");
			String pacid = request.getParameter("pacid");
			String ptannin = request.getParameter("ptannin");
			
			String sizePerPage = "8";
			String currentShowPageNo = request.getParameter("currentShowPageNo");
			
			if(sortType == null) {
				sortType = "";
			}
			
			if(sizePerPage == null ||
			   !"8".equals(sizePerPage)) {

				sizePerPage = "8";
			}

			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한 페이지 당 보여줄 상품의 개수

			paraMap.put("sortType", sortType);
			
			paraMap.put("ptype_arr", ptype_arr);
			paraMap.put("pprice", pprice);
			paraMap.put("phometown_arr", phometown_arr);
			paraMap.put("pbody", pbody);
			paraMap.put("pacid", pacid);
			paraMap.put("ptannin", ptannin);
			
			// 페이징 처리를 위해 검색이 있는 또는 검색이 없는 상품에 대한 총 페이지 수 알아오기
			int totalPage = pdao.getTotalPage(paraMap);

			// === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 totalPage 값보다 더 큰 값을 입력하여 장난친 경우
	        // === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 0 또는 음수를 입력하여 장난친 경우
	        // === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 숫자가 아닌 문자열을 입력하여 장난친 경우 
	        // 아래처럼 막아주도록 하겠다.
			try {
				if(Integer.parseInt(currentShowPageNo) > totalPage ||
				   Integer.parseInt(currentShowPageNo) <= 0 ) {
				   currentShowPageNo = "1";
				   paraMap.put("currentShowPageNo", currentShowPageNo);
				}
			}catch(NumberFormatException e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}

			// ptype_arr 파라미터를 URL에 포함시키기 위해 변환
			String ptypeParam = "";
			if (ptype_arr != null) {
			    for (String ptype : ptype_arr) {
			        ptypeParam += "&ptype=" + URLEncoder.encode(ptype, "UTF-8");
			    }
			}
			
			// phometown_arr 파라미터를 URL에 포함시키기 위해 변환
			String phometownParam = "";
			if (phometown_arr != null) {
				for (String phometown : phometown_arr) {
					phometownParam += "&phometown=" + URLEncoder.encode(phometown, "UTF-8");
				}
			}
			
			// *** ==== 페이지바 만들기 시작 ==== *** //
			StringBuilder pageBar = new StringBuilder();

			int blockSize = 10;
			// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.

			int loop = 1;
			// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.

			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
			int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1;
			// pageNo 는 페이지바에서 보여지는 첫 번째 번호이다.


			String baseUrl = "list.wine?sizePerPage=" + sizePerPage + "&sortType=" + sortType + ptypeParam;
			if (pprice != null) baseUrl += "&pprice=" + pprice;
			
			baseUrl += phometownParam;
			
			if (pbody != null) baseUrl += "&pbody=" + pbody;
			if (pacid != null) baseUrl += "&pacid=" + pacid;
			if (ptannin != null) baseUrl += "&ptannin=" + ptannin;

			// *** [맨처음][이전] 만들기 *** //
			pageBar.append("<li class='page-item'><a class='page-link' href='").append(baseUrl).append("&currentShowPageNo=1'>◀◀</a></li>");

			if (pageNo != 1) {
			    pageBar.append("<li class='page-item'><a class='page-link' href='").append(baseUrl).append("&currentShowPageNo=").append(pageNo - 1).append("'>◀</a></li>");
			}

			while (!(loop > blockSize || pageNo > totalPage)) {
			    if (pageNo == Integer.parseInt(currentShowPageNo)) {
			        pageBar.append("<li class='page-item active'><a class='page-link' href='#'>").append(pageNo).append("</a></li>");
			    } else {
			        pageBar.append("<li class='page-item'><a class='page-link' href='").append(baseUrl).append("&currentShowPageNo=").append(pageNo).append("'>").append(pageNo).append("</a></li>");
			    }
			    loop++;
			    pageNo++;
			}

			// *** [다음][마지막] 만들기 *** //
			if (pageNo <= totalPage) {
			    pageBar.append("<li class='page-item'><a class='page-link' href='").append(baseUrl).append("&currentShowPageNo=").append(pageNo).append("'>▶</a></li>");
			}

			pageBar.append("<li class='page-item'><a class='page-link' href='").append(baseUrl).append("&currentShowPageNo=").append(totalPage).append("'>▶▶</a></li>");

			// *** ==== 페이지바 만들기 끝 ==== *** //

			
			// **** 페이징 처리를 한 검색 포함 상품 목록 보여주기 ****
			List<ProductDTO> pdtList = new ArrayList<>();
			
			if("popular".equals(sortType)) { // 인기순 정렬일 때
				pdtList = pdao.selectProductPagingPopular(paraMap);
				
			} else {
				pdtList = pdao.selectProductPaging(paraMap);
			}

			request.setAttribute("pdtList", pdtList); // 상품 목록

			request.setAttribute("sortType", sortType); // 정렬 타입
			
			String ptype_arr_join = "";
			String phometown_arr_join = "";
			
			if(ptype_arr != null) ptype_arr_join = String.join(",", ptype_arr);
			if(phometown_arr != null)  phometown_arr_join = String.join(",", phometown_arr);
			
			request.setAttribute("ptype_arr_join", ptype_arr_join);
			request.setAttribute("pprice", pprice);
			request.setAttribute("phometown_arr_join", phometown_arr_join);
			request.setAttribute("pbody", pbody);
			request.setAttribute("pacid", pacid);
			request.setAttribute("ptannin", ptannin);
			
			request.setAttribute("sizePerPage", sizePerPage); // 한 페이지당 보일 상품 개수 (8개)

			request.setAttribute("pageBar", pageBar); // 페이지바

			request.setAttribute("currentShowPageNo", currentShowPageNo); // 현재 페이지 번호

			/* 검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 끝 <<< */

			
//			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/adminProductUpdate.jsp");
			
		} else {
			// 로그인을 하지 않았거나 관리자가 아니라면
			String msg = "관리자만 접근 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";

			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);

			super.setViewPage("/WEB-INF/msg.jsp");
		}
	}

}
