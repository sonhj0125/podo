package member.model;

import java.sql.SQLException;
import java.util.Map;

import member.domain.MemberDTO;

public interface MemberDAO {

	// 회원가입
	int doRegister(MemberDTO mdto) throws SQLException;

	// 로그인
	MemberDTO signin(Map<String, String> paraMap) throws SQLException;
	
	// 아이디 중복 확인
	boolean idDuplicateCheck(String userid) throws SQLException;

	// 이메일 중복 확인
	boolean emailDuplicateCheck(String email) throws SQLException;
	
	// 아이디 찾기
	String finduserid(Map<String, String> paraMap) throws SQLException;

	// 비밀번호 찾기(성명, 아이디, (휴대폰 or 이메일)을 입력받아 해당 사용자가 존재하는지 여부 알아오기)
	boolean isUserExist(Map<String, String> paraMap) throws SQLException;

	// 비밀번호 변경
	int pwdUpdate(Map<String, String> paraMap) throws SQLException;

	// 로그기록
	int logwrite(Map<String, String> paraMap) throws SQLException;
	
	// 관리자 회원관리 - 한명 조회
	MemberDTO selectOneMember(String userid) throws SQLException;

	
	// 회원의 개인 정보 변경하기 
	int updateMember(MemberDTO member) throws SQLException;

	
}
