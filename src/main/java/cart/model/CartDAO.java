package cart.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cart.domain.CartDTO;

public interface CartDAO {

	// 장바구니 추가하기
	int insertCart(CartDTO cdto) throws SQLException;

	// 해당번호의 제품을 유저가 장바구니를 추가했는지에 대한 여부 보기
	boolean isProductCartIn(Map<String, String> paraMap) throws SQLException;

	// 장바구니 삭제하기
	int deleteCart(CartDTO cdto) throws SQLException;

	// 유저의 모든 장바구니 내역 산출
	List<CartDTO> getCartList(String userid) throws SQLException;

	// 장바구니 삭제하기 (cindex)
	int deleteCartfromindex(String cindex) throws SQLException;

	// 장바구니 갯수 수정하기
	int modifyVolume(Map<String, String> paraMap) throws SQLException;

	// 유저의 선택된 index 장바구니 내역 산출
	List<CartDTO> getList(String cindexArr) throws SQLException;

	

}
