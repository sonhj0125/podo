package member.model;

import java.sql.SQLException;

import member.domain.MemberDTO;

public interface MemberDAO {

	// 회원가입
	int doRegister(MemberDTO mdto) throws SQLException;

	
	
}
