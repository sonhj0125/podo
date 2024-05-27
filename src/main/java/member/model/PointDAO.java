package member.model;

import java.sql.SQLException;
import java.util.List;

import member.domain.PointDTO;

public interface PointDAO {

	/*
	// 소멸예정 적립금 리스트 가져오기 (소멸예정 적립금, 날짜, 남은일자)
	List<PointDTO> getExpiryDetailsList(String userid) throws SQLException;
	
	// 유저가 사용한 포인트 로그 가져오기
	List<PointDTO> getUserPointUsedHistoryList(String userid);
	*/
	

	// 사용가능 적립금, 누적 적립금, 사용한 적립금(합쳐서 한개)
	PointDTO getUserPointDetails(String userid) throws SQLException;
	
	// 유저가 적립한 포인트 로그 가져오기
	List<PointDTO> getUserPointHistoryList(String userid) throws SQLException;



	
}
