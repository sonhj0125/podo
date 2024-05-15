package product.model;

import java.sql.SQLException;
import java.util.List;

import product.domain.ProductDTO;

public interface ProductDAO {

	// 제품 리스트 뽑아오기 DESC
	List<ProductDTO> listReadDesc() throws SQLException;

	// 제품 하나 뽑아오기
	ProductDTO getproduct(int pindex) throws SQLException;


}
