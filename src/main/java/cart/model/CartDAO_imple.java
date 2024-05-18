package cart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cart.domain.CartDTO;
import shop.domain.ProductDTO;

public class CartDAO_imple implements CartDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DecimalFormat df = new DecimalFormat("#,###");
	
	public CartDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// end of public CartDAO_imple()
	
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
	public int insertCart(CartDTO cdto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " INSERT INTO CART (CINDEX, USERID, CVOLUME, PINDEX) "
					+ " VALUES (SEQ_CIDX.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getMdto().getUserid());
			pstmt.setString(2, cdto.getCvolume());
			pstmt.setInt(3, cdto.getPdto().getPindex());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public boolean isProductCartIn(Map<String, String> paraMap) throws SQLException{
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from cart where userid = ? and pindex = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public int deleteCart(CartDTO cdto) throws SQLException {
		
int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " DELETE "
					+ " FROM CART "
					+ " where userid = ? and pindex = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getMdto().getUserid());
			pstmt.setInt(2, cdto.getPdto().getPindex());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public List<CartDTO> getCartList(String userid) throws SQLException {
		
		List<CartDTO> cdtoList = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select CVOLUME,PNAME,PTYPE,PHOMETOWN,PPRICE,PIMG "
					+ " from CART join PRODUCT on CART.PINDEX = PRODUCT.PINDEX "
					+ " where USERID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			boolean target = true;
			
			while(rs.next()) {
				
				if(target) {
					cdtoList = new ArrayList<>();
					target = false;
				}
				
				CartDTO cdto = new CartDTO();
				cdto.setCvolume(rs.getString("CVOLUME"));
				
				ProductDTO pdto = new ProductDTO();
				pdto.setPname(rs.getString("pname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				pdto.setPimg(rs.getString("pimg"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				
				cdto.setPdto(pdto);
				
				cdtoList.add(cdto);
			}
			
			
		}finally {
			close();
		}
		
		return cdtoList;
	}

}
