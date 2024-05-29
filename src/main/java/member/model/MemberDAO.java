package member.model;

import java.sql.SQLException;
import java.util.List;
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
	
	// 관리자 회원관리 - 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지 수 알아오기
	int getTotalPage(Map<String, String> paraMap) throws SQLException;
	
	// 관리자 회원관리 - 페이징 처리를 한 모든 회원 또는 검색한 회원 목록 보여주기
	List<MemberDTO> select_Member_paging(Map<String, String> paraMap) throws SQLException;
	
	/* >>> 뷰단(memberList.jsp)에서 "페이징 처리 시 보여주는 순번 공식" 에서 사용하기 위해 
	    검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 시작 <<< */
	int getTotalMemberCount(Map<String, String> paraMap) throws SQLException;

	// 관리자 회원관리 - 한명 조회
	MemberDTO selectOneMember(String userid) throws SQLException;
	
	// 회원의 개인 정보 변경하기 
	int updateMember(MemberDTO member) throws SQLException;

	// 회원정보 수정시 email 중복검사
	boolean emailDuplicateCheck2(Map<String, String> paraMap) throws SQLException;

	// 마이페이지 비밀번호 변경
	int pwdUpdate2(Map<String, String> paraMap) throws SQLException;

	// 비밀번호 변경시 현재 사용중인 비밀번호인지 아닌지 알아오기(현재 사용중인 비밀번호 이라면 true, 새로운 비밀번호이라면 false)
	boolean duplicatePwdCheck(Map<String, String> paraMap) throws SQLException;

	// 내 포인트 검색
	int pointread(String userid) throws SQLException;

	// [마이페이지] 작성할 리뷰 개수 알아오기
	int getReviewCnt(String userid) throws SQLException;

	// 포인트 차감
	boolean delPoint(Map<String, String> paraMap) throws SQLException;

	// 포인트 사용 로그 작성
	boolean writePointDown(Map<String, String> paraMap) throws SQLException;

	// 포인트 적립
	boolean addPoint(Map<String, String> paraMap) throws SQLException;

	// 포인트 적립 로그 작성
	boolean writePointUp(Map<String, String> paraMap) throws SQLException;

	// 회원가입 쿠폰 주기
	int insertRegisterCoupon(String userid) throws SQLException;

	// 내 포인트 셀랙트
	String getMyPoint(String userid) throws SQLException;


	
}
