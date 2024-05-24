package coupon.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import coupon.domain.CouponDTO;
import coupon.domain.MyCouponDTO;

public interface CouponDAO {

	// 할인 쿠폰 등록(admin 만 등록 가능)
	int register(CouponDTO codto) throws SQLException;

	// 쿠폰을 삽입하기 전에 쿠폰이 이미 존재하는지 중복 검사(coname이름PK)
	boolean isCouponExist(CouponDTO codto) throws SQLException;

	// 내 쿠폰 리스트 불러오기
	List<MyCouponDTO> getMyList(String userid) throws SQLException;

	// 쿠폰 정보 불러오기
	CouponDTO getcouponinfo(String coname) throws SQLException;

	// 쿠폰 사용
	int useCoupon(MyCouponDTO mycodto) throws SQLException;

	// 쿠폰 유효여부
	boolean isCoupon(Map<String, String> paraMap) throws SQLException;
	
}
