package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class ShopList extends AbstractController {

	ProductDAO pdao = null;

	public ShopList() {
		pdao = new ProductDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sortType = request.getParameter("sortType");
		String sizePerPage = "8";
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(sortType == null) {
			sortType = "";
		}
		
		if(sizePerPage == null ||
		   !"8".equals(sizePerPage)) {

			sizePerPage = "8";
		}

		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("currentShowPageNo", currentShowPageNo);
		paraMap.put("sizePerPage", sizePerPage); // 한 페이지 당 보여줄 상품의 개수

		// 페이징 처리를 위해 상품에 대한 총 페이지 수 알아오기 (검색 X)
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

		// *** ==== 페이지바 만들기 시작 ==== *** //
		String pageBar = "";

		int blockSize = 10;
		// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.

		int loop = 1;
		// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.

		// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
		int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1;
		// pageNo 는 페이지바에서 보여지는 첫 번째 번호이다.


		// *** [맨처음][이전] 만들기 *** //
		pageBar += "<li class='page-item'><a class='page-link' href='list.wine?sizePerPage="+sizePerPage+"&sortType="+sortType+"&currentShowPageNo=1'>◀◀</a></li>";

		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='list.wine?sizePerPage="+sizePerPage+"&sortType="+sortType+"&currentShowPageNo="+(pageNo-1)+"'>◀</a></li>";
		}


		while( !(loop > blockSize || pageNo > totalPage) ) {

			if( pageNo == Integer.parseInt(currentShowPageNo) ) {
				pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";

			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='list.wine?sortType="+sortType+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			}

			loop++;		// 1 2 3 4 5 6 7 8 9 10

			pageNo++;	//  1  2  3  4  5  6  7  8  9 10
						// 11 12 13 14 15 16 17 18 19 20
						// 21 22 23 24 25 26 27 28 29 30
						// 31 32 33 34 35 36 37 38 39 40
						// 41 42  마지막 페이지는 42페이지

		} // end of while -----------------------------------


		// *** [다음][마지막] 만들기 *** //

		if( pageNo <= totalPage ) {
			// 맨 마지막이 아닌 경우에만 다음을 넣어준다.
			pageBar += "<li class='page-item'><a class='page-link' href='list.wine?sizePerPage="+sizePerPage+"&sortType="+sortType+"&currentShowPageNo="+pageNo+"'>▶</a></li>";
		}

		pageBar += "<li class='page-item'><a class='page-link' href='list.wine?sizePerPage="+sizePerPage+"&sortType="+sortType+"&currentShowPageNo="+totalPage+"'>▶▶</a></li>";

		// *** ==== 페이지바 만들기 끝 ==== *** //

		paraMap.put("sortType", sortType);
		
		// **** 페이징 처리를 한 모든 상품 목록 보여주기 ****
		List<ProductDTO> pdtList = pdao.selectProductPaging(paraMap);

		request.setAttribute("pdtList", pdtList); // 상품 목록

		request.setAttribute("sortType", sortType); // 정렬 타입

		request.setAttribute("sizePerPage", sizePerPage); // 한 페이지당 보일 상품 개수 (8개)

		request.setAttribute("pageBar", pageBar); // 페이지바

		request.setAttribute("currentShowPageNo", currentShowPageNo); // 현재 페이지 번호

		/* 검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 끝 <<< */

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/shop/shopList.jsp");

	}

}