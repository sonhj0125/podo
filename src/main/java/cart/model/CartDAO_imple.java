package cart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cart.domain.CartDTO;
import cart.domain.DeliveryDTO;
import member.domain.MemberDTO;
import shop.domain.OrderDTO;
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
			
			String sql = "select CVOLUME,PNAME,PTYPE,PHOMETOWN,PPRICE,PIMG,cart.PINDEX as pindex,cindex "
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
				cdto.setCindex(rs.getInt("cindex"));;
				
				ProductDTO pdto = new ProductDTO();
				pdto.setPname(rs.getString("pname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPindex(rs.getInt("pindex"));
				
				int sumpriceInt = Integer.parseInt(rs.getString("pprice"))*Integer.parseInt(rs.getString("CVOLUME"));
				
				String sumprice = df.format(sumpriceInt);
				
				cdto.setSumprice(sumprice);
				
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

	@Override
	public int deleteCartfromindex(String cindex) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " DELETE "
					+ " FROM CART "
					+ " where cindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cindex);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public int modifyVolume(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "UPDATE CART SET CVOLUME = ? WHERE CINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("cvolume"));
			pstmt.setString(2, paraMap.get("cindex"));
			
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public List<CartDTO> getList(String cindexArr) throws SQLException {
		
		List<CartDTO> cdtoList = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select CVOLUME,PNAME,PTYPE,PHOMETOWN,PPRICE,PIMG,cart.PINDEX as pindex,cindex "
					+ " from CART join PRODUCT on CART.PINDEX = PRODUCT.PINDEX "
					+ " where CINDEX IN ("+ cindexArr +")";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			boolean target = true;
			
			while(rs.next()) {
				
				if(target) {
					cdtoList = new ArrayList<>();
					target = false;
				}
				
				CartDTO cdto = new CartDTO();
				cdto.setCvolume(rs.getString("CVOLUME"));
				cdto.setCindex(rs.getInt("cindex"));;
				
				ProductDTO pdto = new ProductDTO();
				pdto.setPname(rs.getString("pname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPindex(rs.getInt("pindex"));
				
				int sumpriceInt = Integer.parseInt(rs.getString("pprice"))*Integer.parseInt(rs.getString("CVOLUME"));
				
				String sumprice = df.format(sumpriceInt);
				
				cdto.setSumprice(sumprice);
				
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

	@Override
	public CartDTO getProuctinfo(String string) throws SQLException {
		
		CartDTO cdto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select CVOLUME, PPOINT, PPRICE, USERID, cart.PINDEX as pindex, pname "
					+ " from cart join PRODUCT on cart.PINDEX = PRODUCT.PINDEX "
					+ " where CINDEX = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, string);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				cdto = new CartDTO();
				
				cdto.setCvolume(rs.getString("CVOLUME"));
				
				ProductDTO pdto = new ProductDTO();
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPprice(rs.getString("pprice"));
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPname(rs.getString("pname"));
				
				MemberDTO mdto = new MemberDTO();
				mdto.setUserid(rs.getString("userid"));
				
				cdto.setMdto(mdto);
				cdto.setPdto(pdto);
				
			}
			
		}finally {
			close();
		}
		
		return cdto;
	}

	@Override
	public int setOrder(OrderDTO odto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "INSERT INTO ORDERS (OINDEX, OTOTALPRICE, OPOINT, OVOLUME, USERID, PINDEX) "
					+ "VALUES (SEQ_OINDEX.nextval, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odto.getOtotalprice());
			pstmt.setString(2, odto.getOpoint());
			pstmt.setString(3, odto.getOvolume());
			pstmt.setString(4, odto.getUserid());
			pstmt.setInt(5, odto.getPindex());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public int getOindex(OrderDTO odto) throws SQLException {
		
		int oindex = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select oindex from orders where USERID = ? and PINDEX = ? order by oindex desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odto.getUserid());
			pstmt.setInt(2, odto.getPindex());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				oindex = rs.getInt("oindex");
			}
			
		}finally {
			close();
		}
		
		return oindex;
	}

	@Override
	public boolean setDelivery(DeliveryDTO ddto) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO DELIVERY (DINDEX, DNAME, DEMAIL, DPHONE, DMSG, OINDEX, DADDRESS, DADDRESSDETAIL) "
					+ " VALUES (SEQ_DINDEX.nextval, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ddto.getDname());
			pstmt.setString(2, ddto.getDemail());
			pstmt.setString(3, ddto.getDphone());
			pstmt.setString(4, ddto.getDmsg());
			pstmt.setInt(5, ddto.getOindex());
			pstmt.setString(6, ddto.getDaddress());
			pstmt.setString(7, ddto.getDaddressdetail());
			
			if(1==pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
		
	}

	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select ceil(count(*)/10) as cnt from ORDERS ";
			
			String userid = paraMap.get("searchWord");
			String status = paraMap.get("searchType");
			
			int step = 0;
			
			try {
				if(!userid.isBlank()) {
					sql += "where userid = ? ";
					step = 1;
				}
			}catch (Exception e) {
			}
			
			if(step == 0 && !"0".equals(status)) {
				sql += "where ostatus = ? ";
				step = 2;
			}
			
			if(step == 1 && !"0".equals(status)) {
				sql += "and ostatus = ? ";
				step = 3;
			}
			
			pstmt = conn.prepareStatement(sql);
			
			switch (step) {
				case 1:
					pstmt.setString(1, userid);
					break;
				case 2:
					pstmt.setString(1, status);
					break;
				case 3:
					pstmt.setString(1, userid);
					pstmt.setString(2, status);
					break;
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public List<OrderDTO> selectOrderPaging(Map<String, String> paraMap) throws SQLException {
		
		List<OrderDTO> odtolist = new ArrayList<OrderDTO>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select * from "
					+ " ( "
					+ " select ROWNUM as rno,OINDEX, PNAME, OTOTALPRICE, OVOLUME, odate, ostatus, userid, OARDATE "
					+ " from "
					+ " (select ROWNUM,OINDEX, PNAME, OTOTALPRICE, OVOLUME, odate, ostatus, userid, OARDATE "
					+ " from ORDERS join PRODUCT on ORDERS.PINDEX = PRODUCT.PINDEX order by ROWNUM desc) )"
					+ " where rno between ? and ? " ;
			
			String userid = paraMap.get("searchWord");
			String status = paraMap.get("searchType");
			int navNum = Integer.parseInt(paraMap.get("navNum"));
			int sizePerPage = 10;
			
			int step = 0;
			
			try {
				if(!userid.isBlank()) {
					sql += "and userid = ? ";
					step = 1;
				}
			}catch (Exception e) {
			}
			
			if(step == 0 && !"0".equals(status)) {
				sql += "and ostatus = ? ";
				step = 2;
			}
			
			if(step == 1 && !"0".equals(status)) {
				sql += "and ostatus = ? ";
				step = 3;
			}
			
			pstmt = conn.prepareStatement(sql);
			
			switch (step) {
				case 0:
					pstmt.setInt(1, (navNum * sizePerPage - (sizePerPage - 1)));
					pstmt.setInt(2, (navNum * sizePerPage));
					break;
				case 1:
					pstmt.setInt(1, (navNum * sizePerPage - (sizePerPage - 1)));
					pstmt.setInt(2, (navNum * sizePerPage));
					pstmt.setString(3, userid);
					break;
				case 2:
					pstmt.setInt(1, (navNum * sizePerPage - (sizePerPage - 1)));
					pstmt.setInt(2, (navNum * sizePerPage));
					pstmt.setString(3, status);
					break;
				case 3:
					pstmt.setInt(1, (navNum * sizePerPage - (sizePerPage - 1)));
					pstmt.setInt(2, (navNum * sizePerPage));
					pstmt.setString(3, userid);
					pstmt.setString(4, status);
					break;
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO odto = new OrderDTO();
				
				odto.setOindex(rs.getInt("oindex"));
				
				ProductDTO pdto = new ProductDTO();
				pdto.setPname(rs.getString("pname"));
				odto.setPdto(pdto);
				
				odto.setOtotalprice(rs.getString("ototalprice"));
				odto.setOvolume(rs.getString("ovolume"));
				odto.setOdate(rs.getString("odate"));
				odto.setOstatus(rs.getInt("ostatus"));
				odto.setUserid(rs.getString("userid"));
				
				String arrDate = rs.getString("OARDATE");
				if(arrDate == null || arrDate.isBlank()) {
					arrDate = "없음";
				}
				
				odto.setOardate(arrDate);
				
				odtolist.add(odto);
			}
			
		}finally {
			close();
		}
		
		return odtolist;
		
	}

	@Override
	public DeliveryDTO getOrderDetailAdmin(int oindex) throws SQLException {
		
		DeliveryDTO ddto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select dindex, dname,DPHONE,demail,DMSG,DADDRESS,DADDRESSDETAIL,DNUMBER,OSTATUS,OARDATE "
					+ "from DELIVERY join ORDERS on DELIVERY.OINDEX = ORDERS.OINDEX where DELIVERY.OINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oindex);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ddto = new DeliveryDTO();
				
				ddto.setDindex(rs.getInt("dindex"));
				ddto.setDname(rs.getString("dname"));
				ddto.setDphone(rs.getString("dphone"));
				ddto.setDemail(rs.getString("demail"));
				ddto.setDmsg(rs.getString("dmsg"));
				ddto.setDaddress(rs.getString("daddress"));
				ddto.setDaddressdetail(rs.getString("DADDRESSDETAIL"));
				
				String dnumber = rs.getString("dnumber");
				
				try {
					if(dnumber == null || dnumber.isBlank()) {
						dnumber = "미등록";
					}
				}catch (Exception e) {
					dnumber = "미등록";
				}
				
				ddto.setDnumber(dnumber);
				
				OrderDTO odto = new OrderDTO();
				
				odto.setOstatus(rs.getInt("OSTATUS"));
				odto.setOardate(rs.getString("OARDATE"));
				
				ddto.setOdto(odto);
			}
			
		}finally {
			close();
		}
		
		return ddto;
	}

	@Override
	public boolean registerdnum(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "UPDATE DELIVERY "
					+ " SET DNUMBER = ? "
					+ " WHERE DINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("dnumber"));
			pstmt.setString(2, paraMap.get("dindex"));
			
			if(1 == pstmt.executeUpdate()) {
				result=true;
			}
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public boolean registerostatus(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "update ORDERS "
					+ "set OSTATUS = ? "
					+ "where OINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("ostatus"));
			pstmt.setString(2, paraMap.get("oindex"));
			
			if(1 == pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public void setOardate(Map<String, String> paraMap) throws SQLException {
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "UPDATE ORDERS "
					+ "SET OARDATE = ? "
					+ "WHERE OINDEX = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String nowStr = df.format(now);
			
			pstmt.setString(1, nowStr);
			pstmt.setString(2, paraMap.get("oindex"));
			
			pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
	}

	@Override
	public String directselectCidx(String userid) throws SQLException {
		
		String result = "";
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select CINDEX from CART where USERID = ? order by CINDEX desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("CINDEX");
			}
			
		}finally {
			close();
		}
		
		return result;
	}

	
}
