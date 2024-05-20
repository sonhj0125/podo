package shop.model;

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

import oracle.net.aso.c;
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

	
	
	// 제품 리스트 뽑아오기 DESC
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

	
	
	// 제품 하나 뽑아오기
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

			String sql = " select pname, pengname, ptype, phometown, pprice, pdetail, pimg, pindex "
					   + " from product "
					   + " where pname like '%'|| ? || '%' ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPindex(rs.getInt("pindex"));

				wineList.add(pdto);

			}

		} finally {
			close();
		}

		return wineList;

	} // end of public List<ProductDTO> searchWineName(String searchWord)

	
	
	// 페이징 처리를 위해 상품에 대한 총 페이지 수 알아오기 (검색 X)
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {

		int totalPage = 0;

		try {

			conn = ds.getConnection();

			String sql = " select ceil(count(*)/?) "
					   + " from product ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")) );

			rs = pstmt.executeQuery();
			rs.next();

			totalPage = rs.getInt(1);

		} finally {
			close();
		}

		return totalPage;

	} // end of public int getTotalPage(Map<String, String> paraMap) throws SQLException -------

	
	
	// **** 페이징 처리를 한 모든 상품 목록 보여주기 ****
	@Override
	public List<ProductDTO> selectProductPaging(Map<String, String> paraMap) throws SQLException {

		List<ProductDTO> pdtList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " SELECT RNO, PNAME, PENGNAME, PTYPE, PHOMETOWN, PPRICE, "
						+ "            PPOINT, PBODY, PACID, PTANNIN, PACL, PDETAIL, PIMG, PSTOCK, PINDEX "
						+ "FROM "
						+ "(  "
						+ "    select rownum AS RNO, pname, pengname, ptype, phometown, pprice, "
						+ "           ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex "
						+ "    from "
						+ "    ( "
						+ "        select pname, pengname, ptype, phometown, to_number(pprice) as pprice, "
						+ "			      ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex "
						+ "        from product"
						+ "		   where ptype like '%' ||  ? || '%' and ";
			
			String ptype = paraMap.get("ptype");
			String pprice = paraMap.get("pprice");
			String phometown = paraMap.get("phometown");
			String pbody = paraMap.get("pbody");
			String pacid = paraMap.get("pacid");
			String ptannin = paraMap.get("ptannin");
			
			if(pprice != null) {
				
				switch (pprice) {
				case "1":
					sql += " to_number(pprice) < 10000 and ";
					break;
					
				case "2":
					sql += " 10000 <= to_number(pprice) and to_number(pprice) < 50000 and ";
					break;
					
				case "3":
					sql += " 50000 <= to_number(pprice) and to_number(pprice) < 150000 and ";
					break;
					
				case "4":
					sql += " 150000 <= to_number(pprice) and to_number(pprice) < 300000 and ";
					break;
					
				case "5":
					sql += " to_number(pprice) >= 300000 and ";
					break;
					
				default:
					break;
				}
			}
			
			sql += " phometown like '%' ||  ? || '%' and "
				 + " pbody like '%' ||  ? || '%' and "
				 + " pacid like '%' ||  ? || '%' and "
				 + " ptannin like '%' ||  ? || '%' ";
			
			String sortType = paraMap.get("sortType");
			
			switch (sortType) {
			case "latest":
				sql += " order by pindex desc ";
				break;
				
			case "popular":
				
				break;
				
			case "highPrice":
				sql += " order by pprice desc ";
				break;
				
			case "lowPrice":				
				sql += " order by pprice asc ";
				break;
				
			default:
				sql += " order by pindex desc ";
				break;
				
			} // end of switch(sortType) ---------------
			
						
			sql += "    ) V "
			+ " ) T "
			+ " WHERE T.rno BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ptype);
			pstmt.setString(2, phometown);
			pstmt.setString(3, pbody);
			pstmt.setString(4, pacid);
			pstmt.setString(5, ptannin);
			
			/*
			=== 페이징처리의 공식 ===
			where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			*/

			int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			int sizePerPage =Integer.parseInt(paraMap.get("sizePerPage"));

			pstmt.setInt(6, (currentShowPageNo * sizePerPage - (sizePerPage - 1)));
			pstmt.setInt(7, (currentShowPageNo * sizePerPage));

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));

				String price = df.format(rs.getInt("pprice"));
				pdto.setPprice(price);

				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("pacl"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));

				pdtList.add(pdto);

			} // end of while(rs.next()) ------------------------------------

		}
//		catch (GeneralSecurityException | UnsupportedEncodingException e) {
//			e.printStackTrace();
//			
//		}
		finally {
			close();
		}

		return pdtList;
	} // end of public List<ProductDTO> selectProductPaging(Map<String, String> paraMap) throws SQLException -----------

	@Override
	public int setLike(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO Likeit (USERID, PINDEX) "
					+ "VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close();
		}
		
		return result;
	}

	@Override
	public boolean isLike(Map<String, String> paraMap) throws SQLException {
		
		boolean isLike = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from likeit where userid = ? and pindex = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isLike = true;
			}
						
		}finally {
			close();
		}
		
		return isLike;
	}

	@Override
	public int setunlike(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " DELETE "
					+ " FROM LIKEIT "
					+ " WHERE USERID = ? AND PINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close();
		}
		
		return result;
	}
	
	@Override
	public String getProductDetailImg(int pindex) throws SQLException {
		
		String pdImgName = "";
		
		try {

			conn = ds.getConnection();

			String sql = " select pdImg "
					   + " from ProductDetailImg "
					   + " where pIndex = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pindex);

			rs = pstmt.executeQuery();
			
			rs.next();
			
			pdImgName = rs.getString("pdImg");

		} finally {
			close();
		}	
		
		return pdImgName;
	}

	@Override
	public List<ProductDTO> listPopReadDesc() throws SQLException {
		
		List<ProductDTO> pdto_list = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select * "
					+ " from "
					+ " (select pindex,count(pindex)as coun from LIKEIT group by pindex order by coun desc) l join PRODUCT on l.PINDEX=PRODUCT.PINDEX "
					+ " order by coun desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
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
				
				pdto_list.add(pdto);
				
			}
			
			
		}finally {
			close();
		}
		
		return pdto_list;
	}

	


}
