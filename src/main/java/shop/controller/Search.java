package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import common.controller.AbstractController;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;
import shop.domain.ProductDTO;

public class Search extends AbstractController {

	ProductDAO pdao = null;

	public Search() {
		pdao = new ProductDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchWord = request.getParameter("searchWord");
		
		List<ProductDTO> wineList = new ArrayList<>();
		
	    if (searchWord.matches("^[a-zA-Z]+$")) { // 검색어가 영어일 때
	        wineList = pdao.searchWineEngName(searchWord);
	    } else {
	        wineList = pdao.searchWineName(searchWord);
	    }
		
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("wineList", wineList);
		request.setAttribute("count", wineList.size());

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/shop/wineSearchList.jsp");
		
	}

}