package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import shop.domain.ProductDTO;

public class ProductDAO_imple implements ProductDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DecimalFormat df = new DecimalFormat("#,###");
	
	public ProductDAO_imple() {
		
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
	public List<ProductDTO> listReadDesc() throws SQLException {
		
		List<ProductDTO> resultList = null;
		boolean isFSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT * "
					   + " FROM PRODUCT "
					   + " ORDER BY PINDEX DESC ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isFSearch) {
					resultList = new ArrayList<>();
					isFSearch = false;
				}
				
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("ptannin"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPimg(rs.getString("pimg"));
				
				resultList.add(pdto);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return resultList;
		
	}// end of public Map<Integer, ProductDTO> listReadDesc()

	@Override
	public ProductDTO getproduct(int pindex) throws SQLException {
		
		ProductDTO pdto = null;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = "select * "
					+ " from PRODUCT "
					+ " where PINDEX=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pindex);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("ptannin"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPimg(rs.getString("pimg"));
				
			}
			
		}finally {
			close();
		}
		
		return pdto;
	}// end of public ProductDTO getproduct(int pindex)
	
	// Search창 와인 검색
	@Override
	public List<ProductDTO> searchWineName(String searchWord) throws SQLException {

		List<ProductDTO> wineList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " select pname, pengname, ptype, phometown, pprice, pdetail, pimg "
					   + " from product "
					   + " where pname like '%'|| ? || '%' ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pvo = new ProductDTO();

				pvo.setPname(rs.getString("pname"));
				pvo.setPengname(rs.getString("pengname"));
				pvo.setPtype(rs.getString("ptype"));
				pvo.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pvo.setPprice(price);
				pvo.setPdetail(rs.getString("pdetail"));
				pvo.setPimg(rs.getString("pimg"));

				wineList.add(pvo);

			}

		} finally {
			close();
		}

		return wineList;

	} // end of public List<ProductVO> searchWineName(String searchWord)

}
