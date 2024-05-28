package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.domain.PointDTO;

public class PointDAO_imple implements PointDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public PointDAO_imple() {
		
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


	/*
	// 아이디, 이름, 사용가능 적립금, 누적 적립금, 사용한 적립금(합쳐서 한개)
	@Override
	public PointDTO getUserPointDetails(String userid) throws SQLException {
		
		PointDTO podto = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT M.UserID "
					   + "      , M.Name "
					   + "      , TO_CHAR(NVL(A.AvailablePoints, 0)) AS AvailablePoints "
				       + "      , TO_CHAR(NVL(B.TotalPoints, 0)) AS TotalPoints "
					   + "      , TO_CHAR(NVL(C.UsedPoints, 0)) AS UsedPoints "
					   + " FROM ( "
					   + "    SELECT UserID, SUM(TO_NUMBER(Poincome)) AS AvailablePoints "
					   + "    FROM POINT "
					   + "    WHERE PoStatus = '1' "
					   + "    GROUP BY UserID "
					   + " ) A "
					   + " JOIN ( "
					   + "    SELECT UserID, SUM(TO_NUMBER(Poincome)) AS TotalPoints "
					   + "    FROM POINT "
					   + "    GROUP BY UserID "
					   + " ) B "
					   + " ON A.UserID = B.UserID "
					   + " LEFT JOIN ( "
					   + "    SELECT UserID, SUM(TO_NUMBER(Poincome)) AS UsedPoints "
					   + "    FROM POINT "
					   + "    WHERE PoStatus = '2' "
					   + "    GROUP BY UserID "
					   + " ) C "
					   + " ON A.UserID = C.UserID "
					   + " INNER JOIN member M "
					   + " ON A.UserID = M.UserID "
					   + " WHERE M.UserID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
            if (rs.next()) {
                podto = new PointDTO();
                podto.setUserID(rs.getString("UserID"));
                
                MemberDTO mdto = new MemberDTO();
                mdto.setName(rs.getString("Name"));
                podto.setMdto(mdto);
                
                podto.setAvailablePoints(rs.getString("AvailablePoints"));
                podto.setTotalPoints(rs.getString("TotalPoints"));
                podto.setUsedPoints(rs.getString("UsedPoints"));
            }
			
		} finally {
			close();
		}
		
		return podto;
	} // end of public PointDTO getUserPointDetails(String userid) throws SQLException
	*/

	/*
	// 소멸예정 적립금 리스트 가져오기 (소멸예정 적립금, 날짜, 남은일자)
	@Override
	public List<PointDTO> getExpiryDetailsList(String userid) throws SQLException {
		
		List<PointDTO> expiryList = null;
		boolean isSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT NVL(POINCOME, '0') AS ExpiringPoints "
					   + "      , poExpiryDate "
					   + "      , TO_CHAR(TRUNC(TO_DATE(poExpiryDate, 'YYYY-MM-DD HH24:MI:SS') - SYSDATE)) AS DaysUntilExpiry "
					   + " FROM POINT "
					   + " WHERE PoStatus = '1' AND TRUNC(TO_DATE(poExpiryDate, 'YYYY-MM-DD HH24:MI:SS') - SYSDATE) <= 7 "
					   + " AND UserID = ? "
				   	   + " ORDER BY DaysUntilExpiry ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isSearch) {
					expiryList = new ArrayList<>();
					isSearch = false;
				}
				
				PointDTO podto = new PointDTO();
				
				podto.setExpiringPoints(rs.getString("ExpiringPoints"));   // 소멸예정포인트
				podto.setPoExpiryDate(rs.getString("poExpiryDate")); 	   // 만료일
				podto.setDaysUntilExpiry(rs.getString("DaysUntilExpiry")); // 만료까지 남을일수
				
				expiryList.add(podto);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return expiryList;
	} // end of public List<PointDTO> getExpiryDetailsList(String userid) throws SQLException ------
	*/

	

	
	/*
	// 유저가 적립한 포인트 로그 가져오기
	@Override
	public List<PointDTO> getUserPointHistoryList(String userid) throws SQLException {
		List<PointDTO> pointHistoryList = null;
		boolean isSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT POINCOME, PODETAIL, PODATE "
					   + " FROM POINT "
					   + " WHERE PODETAIL LIKE '%주문' and USERID = ? "
				   	   + " ORDER BY PODATE DESC ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isSearch) {
					pointHistoryList = new ArrayList<>();
					isSearch = false;
				}
				
				PointDTO podto = new PointDTO();
				
				podto.setPoIncome(rs.getString("POINCOME"));   	// 변동 포인트
				podto.setPoDetail(rs.getString("PODETAIL")); 	// 적립내용
				podto.setPoDate(rs.getString("PODATE"));		// 적립날짜
				
				pointHistoryList.add(podto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return pointHistoryList;
	} // end of public List<PointDTO> getUserPointDetailsList(String userid) throws SQLException ---


	// 유저가 사용한 포인트 로그 가져오기
	@Override
	public List<PointDTO> getUserPointUsedHistoryList(String userid) {
		List<PointDTO> pointUsedHistoryList = null;
		boolean isSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT POINCOME, PODETAIL, PODATE "
					   + " FROM POINT "
					   + " WHERE PODETAIL LIKE '%상품구입' and USERID = ? "
				   	   + " ORDER BY PODATE DESC ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isSearch) {
					pointUsedHistoryList = new ArrayList<>();
					isSearch = false;
				}
				
				PointDTO podto = new PointDTO();
				
				podto.setPoIncome(rs.getString("POINCOME"));   	// 변동 포인트
				podto.setPoDetail(rs.getString("PODETAIL")); 	// 적립내용
				podto.setPoDate(rs.getString("PODATE"));		// 적립날짜
				
				pointUsedHistoryList.add(podto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return pointUsedHistoryList;
	} // end of public List<PointDTO> getUserPointUsedHistoryList(String userid) ------
	*/


	
	// 사용가능 적립금, 누적 적립금, 사용한 적립금(합쳐서 한개)
	@Override
	public PointDTO getUserPointDetails(String userid) throws SQLException {
		
		PointDTO podto = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT NVL(SUM(TO_NUMBER(POINCOME)), '0') AS AvailablePoints "
					   + "      , NVL(SUM(TO_NUMBER(POINCOME)), '0') AS UsedPoints "
					   + "      , NVL(SUM(TO_NUMBER(POINCOME)), '0') + NVL(SUM(TO_NUMBER(POINCOME)), '0') AS TotalPoints "
					   + " FROM point "
					   + " WHERE USERID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
            if (rs.next()) {
                podto = new PointDTO();
                
                podto.setAvailablePoints(rs.getString("AvailablePoints")); // 사용가능포인트
                podto.setUsedPoints(rs.getString("UsedPoints"));           // 사용한포인트
                podto.setTotalPoints(rs.getString("TotalPoints"));         // 누적포인트
            }
			
		} finally {
			close();
		}
		
		return podto;
	} // end of public PointDTO getUserPointDetails(String userid) throws SQLException
	
	
	
	// 유저가 적립한 포인트 로그 가져오기
	@Override
	public List<PointDTO> getUserPointHistoryList(String userid) throws SQLException {
		List<PointDTO> pointHistoryList = null;
		boolean isSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT POINCOME, PODETAIL, PODATE "
					   + " FROM POINT "
					   + " WHERE USERID = ? "
				   	   + " ORDER BY PODATE DESC ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isSearch) {
					pointHistoryList = new ArrayList<>();
					isSearch = false;
				}
				
				PointDTO podto = new PointDTO();
				
				podto.setPoIncome(rs.getString("POINCOME"));   	// 변동 포인트
				podto.setPoDetail(rs.getString("PODETAIL")); 	// 적립내용
				podto.setPoDate(rs.getString("PODATE"));		// 적립날짜
				
				pointHistoryList.add(podto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return pointHistoryList;
	} // end of public List<PointDTO> getUserPointDetailsList(String userid) throws SQLException ---


	
	// 총 페이지 수
	@Override
	public int getTotalPage(String userid) throws SQLException {
		int totalPage = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql =  " select ceil(count(*)/10) "
						+ " from point "
						+ " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			rs.next();
			
			totalPage = rs.getInt(1);
			
		} finally {
			close();
		}
		
		return totalPage;	
	}

	// **** 페이징 처리를 한 모든 포인트 목록 보여주기 **** //
	@Override
	public List<PointDTO> selectMyPointpaging(Map<String, String> paraMap) throws SQLException {

		List<PointDTO> myPointpaging = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql =  " SELECT rno, USERID, POINCOME, PODETAIL, PODATE "
						+ " FROM   "
						+ " (  "
						+ " select rownum as rno, USERID, POINCOME, PODETAIL, PODATE "
						+ " from    "
						+ " (   "
						+ " select USERID, POINCOME, PODETAIL, PODATE "
						+ " from point  "
						+ " where userid = ?  "
						+ " ) V  "
						+ " ) T   "
						+ " WHERE T.rno BETWEEN ? AND ? ";
				
			pstmt = conn.prepareStatement(sql);
			
			int currentShowPageNo = Integer.parseInt( paraMap.get("currentShowPageNo") ); 
			int sizePerPage = Integer.parseInt( paraMap.get("sizePerPage") );
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식
			pstmt.setInt(3, (currentShowPageNo * sizePerPage) ); // 공식
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PointDTO podto = new PointDTO();

				podto.setUserID(rs.getString("USERID"));
				podto.setPoIncome(rs.getString("POINCOME"));
				podto.setPoDetail(rs.getString("PODETAIL"));
				podto.setPoDate(rs.getString("PODATE"));
				
				myPointpaging.add(podto);
			}// end of while(rs.next())---------------------
		} finally {
			close();
		}
		return myPointpaging;

	} // end of public List<MyCouponDTO> selectMyPointpaging(Map<String, String> paraMap) throws SQLException ----


	// 총 포인트 개수
	@Override
	public int getTotalMyPointCount(String userid) throws SQLException {

		int totalMyPointCount = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql =  " select count(*) "
						+ " from point "
						+ " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
						
			rs = pstmt.executeQuery();
			
			rs.next();
			
			totalMyPointCount = rs.getInt(1);
			
		} finally {
			close();
		}
		
		return totalMyPointCount;
	} // end of public int getTotalMyPointCount(String userid) throws SQLException ----

	
	
}