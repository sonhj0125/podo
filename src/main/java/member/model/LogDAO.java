package member.model;

import java.sql.SQLException;
import java.util.List;

import member.domain.LogDTO;

public interface LogDAO {

	// 내 로그 기록 가져오기
	List<LogDTO> getMyLog(String userid) throws SQLException;

}
