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

	// 쿠폰 사용
	boolean delCoupon(Map<String, String> paraMap) throws SQLException;

	// 쿠폰등록 (쿠폰번호가 있으면 true 없으면 false 로 해서 값 반환한다.)
	boolean CouponRegistration(Map<String, String> paraMap) throws SQLException;

	// 총 쿠폰 발행 수,	사용 쿠폰 수,	가용 쿠폰 수
	List<MyCouponDTO> getMyCouponList(String userid) throws SQLException;

	
	// 쿠폰의 총개수 알아오기
	int getTotalMyCouponCount(String userid) throws SQLException;
	
	// 쿠폰의 총 페이지 수 알아오기
	int getTotalPage(String userid) throws SQLException;

	// **** 페이징 처리를 한 모든 쿠폰 목록 보여주기 **** //
	List<MyCouponDTO> selectMyCouponpaging(Map<String, String> paraMap) throws SQLException ;
	
}
