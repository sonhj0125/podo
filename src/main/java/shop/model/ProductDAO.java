package shop.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import shop.domain.ProductDTO;

public interface ProductDAO {

	// 제품 리스트 뽑아오기 DESC
	List<ProductDTO> listReadDesc() throws SQLException;

	// 제품 하나 뽑아오기
	ProductDTO getproduct(int pindex) throws SQLException;

	// Search창 와인 검색
	List<ProductDTO> searchWineName(String searchWord) throws SQLException;
	
	// 페이징 처리를 위해 검색이 있는 또는 검색이 없는 상품에 대한 총 페이지 수 알아오기
	int getTotalPage(String[] ptype_arr, Map<String, String> paraMap) throws SQLException;

	// **** 페이징 처리를 한 검색 포함 상품 목록 보여주기 ****
	List<ProductDTO> selectProductPaging(String[] ptype_arr, Map<String, String> paraMap) throws SQLException;
	
}
