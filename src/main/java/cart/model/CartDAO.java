package cart.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cart.domain.CartDTO;
import cart.domain.DeliveryDTO;
import shop.domain.OrderDTO;

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

	// cindex로 제품정보 뽑아오기
	CartDTO getProuctinfo(String string) throws SQLException;

	// 주문기록 작성
	int setOrder(OrderDTO odto) throws SQLException;

	// 주문코드 받기
	int getOindex(OrderDTO odto) throws SQLException;

	// 배달정보 등록하기
	boolean setDelivery(DeliveryDTO ddto) throws SQLException;

	// 어드민 주문페이지 수 가져오기
	int getTotalPage(Map<String, String> paraMap) throws SQLException;

	// 주문접수 페이징 셀랙
	List<OrderDTO> selectOrderPaging(Map<String, String> paraMap) throws SQLException;

	// 배송 정보 불러오기
	DeliveryDTO getOrderDetailAdmin(int oindex) throws SQLException;

	// 송장번호 등록하기
	boolean registerdnum(Map<String, String> paraMap) throws SQLException;

	// 상태변경 하기
	boolean registerostatus(Map<String, String> paraMap) throws SQLException;

	// 배송완료일 저장하기
	void setOardate(Map<String, String> paraMap) throws SQLException;



	

}
