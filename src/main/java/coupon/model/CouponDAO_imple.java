package coupon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import coupon.domain.CouponDTO;

public class CouponDAO_imple implements CouponDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public CouponDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
			
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


	@Override
	public int register(CouponDTO codto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO COUPON (CODISCOUNT, CONAME, CODETAIL, COTYPE, COMIN, CODATE) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, codto.getCodiscount());
			pstmt.setString(2, codto.getConame());
			pstmt.setString(3, codto.getCodetail());
			pstmt.setInt(4, codto.getCotype());
			pstmt.setInt(5, codto.getComin());
			pstmt.setString(6, codto.getCodate());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}
	
	
}
