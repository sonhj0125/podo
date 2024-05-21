package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.domain.MemberDTO;
import util.security.AES256;
import util.security.SecretMykey;
import util.security.Sha256;

public class MemberDAO_imple implements MemberDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private AES256 aes;
	
	public MemberDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
			
			aes = new AES256(SecretMykey.KEY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// end of public MemberDAO_imple()
	
	
	
	// 자원 반납
	private void close() {
		
		try {
			if(rs != null)    {rs.close();    rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn != null)  {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// end of private void close()

	
	
	// 회원가입
	@Override
	public int doRegister(MemberDTO mdto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO MEMBER "
					+ " (USERID, PWD, NAME, EMAIL, PHONE, ADDRESS, ADDRESSDETAIL, GENDER, BIRTHDAY) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, Sha256.encrypt(mdto.getPwd()));
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, aes.encrypt(mdto.getEmail()) );
			pstmt.setString(5, aes.encrypt(mdto.getPhone()));
			pstmt.setString(6, mdto.getAddress());
			pstmt.setString(7, mdto.getAddressDetail());
			pstmt.setString(8, mdto.getGender());
			pstmt.setString(9, mdto.getBirthday());

			result = pstmt.executeUpdate();
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
		
	}// end of public int doRegister(MemberDTO mdto)

	
	@Override
	public MemberDTO signin(Map<String, String> paraMap) throws SQLException {
		
		MemberDTO mdto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select userid, name, email, phone, address, addressdetail, gender, birthday, point, registerday, pwdupdateday, memberidx "
					+ " from MEMBER where userid = ? and pwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, Sha256.encrypt(paraMap.get("pwd")));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mdto = new MemberDTO();
				
				mdto.setUserid(rs.getString(1));
				mdto.setName(rs.getString(2));
				mdto.setEmail(aes.decrypt(rs.getString(3)));
				mdto.setPhone(aes.decrypt(rs.getString(4)));
				mdto.setAddress(rs.getString(5));
				mdto.setAddressDetail(rs.getString(6));
				mdto.setGender(rs.getString(7));
				mdto.setBirthday(rs.getString(8));
				mdto.setPoint(rs.getString(9));
				mdto.setRegisterDay(rs.getString(10));
				mdto.setPwdUpdateDay(rs.getString(11));
				mdto.setMemberIdx(rs.getString(12));
				
			}
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return mdto;
		
	} // end of public MemberDTO signin(Map<String, String> paraMap)
	
	
	// 아이디 중복 확인
	@Override
	public boolean idDuplicateCheck(String userid) throws SQLException {
		
		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select userid "
					   + " from member "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			isExist = rs.next();
			
		} finally {
			close();
		}
		
		return isExist;
		
	} // end of public boolean idDuplicateCheck(String userid)



	// 이메일 중복 확인
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select email "
					   + " from member "
					   + " where email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aes.encrypt(email));
			rs = pstmt.executeQuery();
			
			isExist = rs.next(); 
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return isExist;
		
	} // end of public boolean emailDuplicateCheck(String email)



	// 아이디 찾기
	@Override
	public String finduserid(Map<String, String> paraMap) throws SQLException {
		
		String userid = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select userid from member where name = ? and email = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, aes.encrypt(paraMap.get("email")));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userid = rs.getString("userid");
			}
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return userid;
	} // end of public String finduserid(Map<String, String> paraMap) throws SQLException



	// 비밀번호 찾기(성명, 아이디, (휴대폰 or 이메일)을 입력받아 해당 사용자가 존재하는지 여부 알아오기)
	@Override
	public boolean isUserExist(Map<String, String> paraMap) throws SQLException {

		boolean isUserExist = false;
		
		try {
			conn = ds.getConnection();

			String sql = " select userid "
					   + " from member "
					   + " where memberidx = 1 and name = ? and userid = ? ";

			if(paraMap.containsKey("email")) { // '이메일 인증'일 때
				sql += " and email = ? ";
				
			} else { // '휴대폰 인증'일 때
				sql += " and phone = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, paraMap.get("userid"));
			
			if(paraMap.containsKey("email")) { // '이메일 인증'일 때
				pstmt.setString(3, aes.encrypt(paraMap.get("email")));
				
			} else { // '휴대폰 인증'일 때
				pstmt.setString(3, aes.encrypt(paraMap.get("phone")));
			}
			
			rs = pstmt.executeQuery();

			isUserExist = rs.next(); // 행이 있으면(사용자가 존재하면) true,
			 						 // 행이 없으면(사용자가 존재하지 않으면) false

		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
			
		} finally {
			close();
		}

		return isUserExist;
		
	} // end of public boolean isUserExist(Map<String, String> paraMap) throws SQLException { -----------



	// 비밀번호 변경
	@Override
	public int pwdUpdate(Map<String, String> paraMap) throws SQLException {

		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update member set pwd = ?, pwdupdateday = to_char(sysdate, 'yyyy-mm-dd') "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Sha256.encrypt(paraMap.get("new_pwd"))); // 암호를 SHA256 알고리즘으로 단방향 암호화 시킨다.
			pstmt.setString(2, paraMap.get("userid"));
	        
	        result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int pwdUpdate(Map<String, String> paraMap) throws SQLException ------------



	// 로그 기록
	@Override
	public int logwrite(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = "insert into LOG(LOGINDEX, userid, ipaddress) VALUES (SEQ_LOGIDX.nextval, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("clientip"));
			
			result = pstmt.executeUpdate();
			
		}finally {
			
			close();
		
		}
		
		return result;
	}


	
	// 관리자 회원관리 - 한명 조회
	@Override
	public MemberDTO selectOneMember(String userid) throws SQLException {
		
		return null;
	}


	
	
	
}
