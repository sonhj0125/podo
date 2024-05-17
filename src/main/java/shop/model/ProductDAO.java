package shop.model;

import java.sql.SQLException;
import java.util.List;

import shop.domain.ProductDTO;

public interface ProductDAO {

	// 제품 리스트 뽑아오기 DESC
	List<ProductDTO> listReadDesc() throws SQLException;

	// 제품 하나 뽑아오기
	ProductDTO getproduct(int pindex) throws SQLException;

	// Search창 와인 검색
	List<ProductDTO> searchWineName(String searchWord) throws SQLException;
	
}
