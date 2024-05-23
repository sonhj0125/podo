package coupon.model;

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
import oracle.net.aso.c;

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


	// 할인 쿠폰 등록(admin 만 등록 가능)
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
	} // end of public int register(CouponDTO codto) throws SQLException -------


	// 쿠폰을 삽입하기 전에 쿠폰이 이미 존재하는지 중복 검사(coname이름PK)
	@Override
	public boolean isCouponExist(CouponDTO codto) throws SQLException {
		boolean isCouponExist = false;
		try {
			conn = ds.getConnection();

			String sql = " select CONAME " 
					   + " from COUPON " 
					   + " where CONAME = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, codto.getConame());

			rs = pstmt.executeQuery();

			isCouponExist = rs.next(); // 행이 있으면(중복된 userid) true,
									   // 행이 없으면(사용가능한 userid) false
		} finally {
			close();
		}
		return isCouponExist;
	} // end of public boolean isCouponExist(CouponDTO codto) throws SQLException ----


	@Override
	public List<MyCouponDTO> getMyList(String userid) throws SQLException {
		
		List<MyCouponDTO> mycodtoList = new ArrayList<>();
				
		try {
			
			conn = ds.getConnection();
			
			String sql = " select COUPON.CONAME as coname, COTYPE, CODISCOUNT, CODATE, COREGISTERDAY, COINDEX, costatus "
					+ " from COUPON join MYCOUPON on COUPON.CONAME = MYCOUPON.CONAME join MEMBER on MYCOUPON.USERID = MEMBER.USERID "
					+ " where MEMBER.USERID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getInt("costatus")==1) {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String codate = rs.getString("codate");
					
					Date now = new Date();
					Date finshLine = format.parse(codate);
				    now = format.parse(format.format(now));
				    
					if(now.compareTo(finshLine)>0) {
						
						int coindex = rs.getInt("COINDEX");
						
						sql = " UPDATE MYCOUPON SET COSTATUS = 3 WHERE COINDEX = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, coindex);
						
						if(1!=pstmt.executeUpdate()) {
							System.out.println("쿠폰기간 처리중 오류 발생");
						}
						
					}else {
						MyCouponDTO mycodto = new MyCouponDTO();
						
						CouponDTO codto = new CouponDTO();
						
						codto.setConame(rs.getString("coname"));
						codto.setCotype(rs.getInt("cotype"));
						codto.setCodiscount(rs.getInt("codiscount"));
						codto.setCodate(rs.getString("codate"));
						codto.setCoregisterday(rs.getString("coregisterday"));
						
						mycodto.setCodto(codto);
						mycodto.setCoindex(rs.getInt("COINDEX"));
						
						mycodtoList.add(mycodto);
					}
				
				}
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return mycodtoList;
	}


	@Override
	public CouponDTO getcouponinfo(String coname) throws SQLException {
		
		CouponDTO codto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select COTYPE,CODISCOUNT from COUPON where coname = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				codto = new CouponDTO();
				
				codto.setCotype(rs.getInt("COTYPE"));
				codto.setCodiscount(rs.getInt("CODISCOUNT"));
				
			}
			
			
		}finally {
			close();
		}
		
		return codto;
	}

	
	
	
}
