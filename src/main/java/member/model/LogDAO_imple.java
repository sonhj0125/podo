package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import coupon.domain.CouponDTO;
import coupon.domain.MyCouponDTO;
import member.domain.LogDTO;
import member.domain.MemberDTO;
import util.security.AES256;
import util.security.SecretMykey;
import util.security.Sha256;

public class LogDAO_imple implements LogDAO {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private AES256 aes;
	
	public LogDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
			
			aes = new AES256(SecretMykey.KEY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// end of public LogDAO_imple()

	
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

	
	
	// 내 로그 기록 가져오기
	@Override
	public List<LogDTO> getMyLog(String userid) throws SQLException {
		
	List<LogDTO> ldtoList = new ArrayList<>();
	
	try {
			
		conn = ds.getConnection();
		
		
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	
	return ldtoList;
		
	} // end of public LogDTO getMyLog(String userid) throws SQLException
	
	
	
	
	
	
	
	
}
