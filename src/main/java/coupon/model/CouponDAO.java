package coupon.model;

import java.sql.SQLException;

import coupon.domain.CouponDTO;

public interface CouponDAO {

	// 할인 쿠폰 등록(admin 만 등록 가능)
	int register(CouponDTO codto) throws SQLException;

	// 쿠폰을 삽입하기 전에 쿠폰이 이미 존재하는지 중복 검사(coname이름PK)
	boolean isCouponExist(CouponDTO codto) throws SQLException;
}
