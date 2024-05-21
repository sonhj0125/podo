package coupon.model;

import java.sql.SQLException;

import coupon.domain.CouponDTO;

public interface CouponDAO {

	int register(CouponDTO codto) throws SQLException;

}
